from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from concurrent.futures import ThreadPoolExecutor
import asyncio
import re
import time
import random

def setup_driver():
    options = webdriver.ChromeOptions()
    options.add_argument('--disable-gpu')
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')
    options.add_argument('--window-size=1920,1080')
    options.add_argument('--disable-extensions')
    user_agents = [
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.1 Safari/605.1.15'
    ]
    options.add_argument(f'--user-agent={random.choice(user_agents)}')
    return webdriver.Chrome(options=options)

def parse_sql_file(file_path):
    """Parse SQL file and extract table data with proper structure handling."""
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()

    table_sections = {}
    current_table = None
    current_section = []
    
    for line in content.split('\n'):
        line = line.strip()
        if not line or line.startswith('--'):
            continue
            
        if 'INSERT INTO Hardware.' in line:
            if current_table and current_section:
                table_sections[current_table] = '\n'.join(current_section)
            current_table = re.search(r'Hardware\.(\w+)', line).group(1)
            current_section = [line]
        elif current_table:
            current_section.append(line)
            
    if current_table and current_section:
        table_sections[current_table] = '\n'.join(current_section)

    return table_sections

def extract_table_data(sql_section):
    """Extract column names and values from SQL insert statement."""
    columns_match = re.search(r'\((.*?)\)', sql_section)
    if not columns_match:
        return None, []
        
    columns = [col.strip() for col in columns_match.group(1).split(',')]
    
    name_idx = None
    image_idx = None
    for i, col in enumerate(columns):
        if col.lower() == 'name':
            name_idx = i
        elif col.lower() == 'image_url':
            image_idx = i
            
    if name_idx is None or image_idx is None:
        return None, []

    values_pattern = r"VALUES\s*(.+?);"
    values_match = re.search(values_pattern, sql_section, re.DOTALL)
    if not values_match:
        return None, []

    values_text = values_match.group(1)
    products = []
    
    current_value = ''
    in_quotes = False
    for char in values_text:
        if char == "'" and (not current_value or current_value[-1] != '\\'):
            in_quotes = not in_quotes
        current_value += char
        if char == ')' and not in_quotes:
            try:
                value_items = []
                current_item = ''
                in_item_quotes = False
                for c in current_value.strip('(),\n '):
                    if c == "'" and (not current_item or current_item[-1] != '\\'):
                        in_item_quotes = not in_item_quotes
                    elif c == ',' and not in_item_quotes:
                        value_items.append(current_item.strip())
                        current_item = ''
                        continue
                    current_item += c
                if current_item:
                    value_items.append(current_item.strip())
                
                value_items = [item.strip("' ") for item in value_items]
                if len(value_items) > max(name_idx, image_idx):
                    products.append({
                        'name': value_items[name_idx],
                        'image_url': value_items[image_idx]
                    })
            except Exception as e:
                print(f"Error parsing value: {current_value}\nError: {str(e)}")
            current_value = ''

    return columns, products

def get_amazon_image_url(product_name, driver, max_retries=3):
    """Scrape Amazon product image with improved reliability."""
    for attempt in range(max_retries):
        try:
            print(f"Processing {product_name}... (Attempt {attempt + 1}/{max_retries})")
            
            time.sleep(random.uniform(1, 3))
            
            driver.get(f"https://www.amazon.com/s?k={product_name.replace(' ', '+')}")
            
            WebDriverWait(driver, 15).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, "div.s-search-results"))
            )
            
            selectors = [
                "img.s-image[src*='images/I']",
                "img.s-image",
                "div.s-image img",
                "div[data-component-type='s-product-image'] img"
            ]
            
            for selector in selectors:
                try:
                    first_image = WebDriverWait(driver, 5).until(
                        EC.presence_of_element_located((By.CSS_SELECTOR, selector))
                    )
                    image_url = first_image.get_attribute('src')
                    
                    if image_url and ('images/I' in image_url or 'images/G' in image_url):
                        print(f"Found image for {product_name}")
                        return image_url
                except Exception:
                    continue
            
            print(f"No valid image found for {product_name} with current selectors, retrying...")
            
        except Exception as e:
            print(f"Error finding image for {product_name} (Attempt {attempt + 1}): {str(e)}")
            if attempt < max_retries - 1:
                print("Retrying after error...")
                continue
            
    print(f"Failed to find image for {product_name} after {max_retries} attempts")
    return None

def process_batch(products):
    """Process a batch of products with a single browser instance."""
    results = {}
    driver = setup_driver()
    try:
        for product in products:
            results[product['name']] = {
                'old_url': product['image_url'],
                'new_url': get_amazon_image_url(product['name'], driver)
            }
    finally:
        driver.quit()
    return results

async def update_sql_file():
    try:
        sql_file_path = input("Enter the path to the SQL file: ")
        
        table_sections = parse_sql_file(sql_file_path)
        
        if not table_sections:
            print("No valid table sections found in the SQL file")
            return
            
        print("\nAvailable tables:")
        tables = list(table_sections.keys())
        for i, table in enumerate(tables, 1):
            print(f"{i}. {table}")
        
        while True:
            try:
                choice = int(input("\nSelect a table number to update: ")) - 1
                if 0 <= choice < len(tables):
                    selected_table = tables[choice]
                    break
                print("Invalid selection. Please try again.")
            except ValueError:
                print("Please enter a valid number.")
        
        columns, products = extract_table_data(table_sections[selected_table])
        
        if not products:
            print(f"No products found in table {selected_table}")
            return
        
        print(f"\nProcessing {len(products)} products from table '{selected_table}'...")
        
        batch_size = 5  # Reduced batch size for better reliability
        max_workers = 2  # Reduced workers to avoid overwhelming
        all_results = {}
        
        with ThreadPoolExecutor(max_workers=max_workers) as executor:
            batches = [products[i:i + batch_size] for i in range(0, len(products), batch_size)]
            
            futures = [executor.submit(process_batch, batch) for batch in batches]
            
            for i, future in enumerate(futures):
                try:
                    batch_results = future.result()
                    all_results.update(batch_results)
                    print(f"Processed batch {i + 1}/{len(batches)}")
                except Exception as e:
                    print(f"Batch {i + 1} failed: {str(e)}")
        
        with open(sql_file_path, 'r', encoding='utf-8') as file:
            content = file.read()
        
        updated_count = 0
        for product_name, urls in all_results.items():
            if urls['new_url']:
                old_url = urls['old_url']
                new_url = urls['new_url']
                content = content.replace(old_url, new_url)
                updated_count += 1
        
        if updated_count > 0:
            with open(sql_file_path, 'w', encoding='utf-8') as file:
                file.write(content)
            print(f"\nUpdated {updated_count} products in table '{selected_table}'")
        else:
            print(f"\nNo products were updated in table '{selected_table}'")
            
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        raise

if __name__ == "__main__":
    asyncio.run(update_sql_file())
