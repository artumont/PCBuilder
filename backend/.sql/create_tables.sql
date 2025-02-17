--poner lo de primary key

CREATE TABLE Component (
   id INT,
   title VARCHAR(200),
   class VARCHAR(100),
   specs VARCHAR(3000),
   price FLOAT,
   stock INT,
   compatibility VARCHAR(300),
   update_date VARCHAR(100)
);

CREATE TABLE Orders (
   id INT,
   id_config INT,
   id_client INT,
   order_status VARCHAR(50),
   order_date VARCHAR(100),
   delivery_address VARCHAR(200),
   payment_method VARCHAR(100),
   price FLOAT,
);

CREATE TABLE Users (
   id INT,
   username VARCHAR(200),
   email VARCHAR(200),
   hash_password VARCHAR(200),
   phone_number VARCHAR(10),
   purchase_history VARCHAR(300),
   preferences VARCHAR(300),
   payment_methods VARCHAR(100),
);

CREATE TABLE Shippings (
   id INT,
   id_order INT,
   tracking_number VARCHAR(200),
   shipping_status VARCHAR(200),
   shipping_price FLOAT,
);

CREATE TABLE Payments (
   id INT,
   id_order INT,
   price FLOAT,
   payment_date VARCHAR(100),
   payment_method VARCHAR(200),
   payment_status VARCHAR(100),
);

CREATE TABLE Stock (
   SKU INT,
   title VARCHAR(200),
   class VARCHAR(100),
   quantity VARCHAR(100),
   warehouse_location VARCHAR(200),
   lastest_update VARCHAR(100),
);