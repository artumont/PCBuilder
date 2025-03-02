from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from concurrent.futures import ThreadPoolExecutor
import asyncio
import re

def setup_driver():
    options = webdriver.ChromeOptions()
    options.add_argument('--disable-gpu')
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-dev-shm-usage')
    options.add_argument('--disable-extensions')
    options.page_load_strategy = 'eager'
    prefs = {
        'profile.managed_default_content_settings.images': 2, 
        'disk-cache-size': 4096
    }
    options.add_experimental_option('prefs', prefs)
    return webdriver.Chrome(options=options)

def get_amazon_image_url(product_name, driver):
    try:
        driver.get(f"https://www.amazon.com/s?k={product_name.replace(' ', '+')}")
        first_image = WebDriverWait(driver, 5).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, "img.s-image"))
        )
        return first_image.get_attribute('src')
    except Exception as e:
        print(f"Error finding image for {product_name}: {str(e)}")
        return None

def process_batch(products):
    results = {}
    driver = setup_driver()
    try:
        for product in products:
            results[product] = get_amazon_image_url(product, driver)
    finally:
        driver.quit()
    return results

async def update_sql_file():
    sql_file_path = input("Enter the path to the SQL file: ")
    
    with open(sql_file_path, 'r', encoding='utf-8') as file:
        content = file.read()
    
    pattern = r"'([^']+)',\s+'https://[^']+'"
    matches = list(re.finditer(pattern, content))
    products = [match.group(1) for match in matches]
    
    batch_size = 10
    max_workers = 4
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
    
    for match in matches:
        product_name = match.group(1)
        if product_name in all_results and all_results[product_name]:
            old_entry = match.group(0)
            new_entry = f"'{product_name}', '{all_results[product_name]}'"
            content = content.replace(old_entry, new_entry)
    
    with open(sql_file_path, 'w', encoding='utf-8') as file:
        file.write(content)

if __name__ == "__main__":
    asyncio.run(update_sql_file())