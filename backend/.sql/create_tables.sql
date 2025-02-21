CREATE TABLE Users (
   id INT PRIMARY KEY,
   username VARCHAR(200),
   email VARCHAR(200),
   hash_password VARCHAR(200),
   phone_number VARCHAR(10),
   preferences VARCHAR(300),
   INDEX idx_email (email),
   INDEX idx_username (username)
);

CREATE TABLE Payment_Methods (
   id INT PRIMARY KEY,
   user_id INT,
   payment_method VARCHAR(100),
   FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Hardware (
   id INT PRIMARY KEY,
   title VARCHAR(200),
   class VARCHAR(100),
   specs VARCHAR(3000),
   price FLOAT,
   stock INT,
   compatibility VARCHAR(300),
   update_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   INDEX idx_title (title)
);

CREATE TABLE Orders (
   id INT PRIMARY KEY,
   id_client INT,
   order_status VARCHAR(50),
   order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   delivery_address VARCHAR(200),
   payment_method_id INT,
   price FLOAT,
   INDEX idx_client_order_date (id_client, order_date),
   FOREIGN KEY (payment_method_id) REFERENCES Payment_Methods(id)
);

CREATE TABLE Purchase_History (
   id INT PRIMARY KEY,
   user_id INT,
   order_id INT,
   purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (user_id) REFERENCES Users(id),
   FOREIGN KEY (order_id) REFERENCES Orders(id)
);

CREATE TABLE Shippings (
   id INT PRIMARY KEY,
   id_order INT,
   tracking_number VARCHAR(200),
   shipping_status VARCHAR(200),
   shipping_price FLOAT,
   shipping_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (id_order) REFERENCES Orders(id),
   INDEX idx_tracking_number (tracking_number),
   INDEX idx_id_order (id_order)
);

CREATE TABLE Payments (
   id INT PRIMARY KEY,
   id_order INT,
   price FLOAT,
   payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   payment_method_id INT,
   payment_status VARCHAR(100),
   FOREIGN KEY (id_order) REFERENCES Orders(id),
   FOREIGN KEY (payment_method_id) REFERENCES Payment_Methods(id),
   INDEX idx_id_order (id_order)
);

CREATE TABLE Stock (
   SKU INT PRIMARY KEY,
   title VARCHAR(200),
   class VARCHAR(100),
   quantity INT,
   warehouse_location VARCHAR(200),
   lastest_update DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Stock_History (
   id INT PRIMARY KEY,
   SKU INT,
   quantity INT,
   change_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (SKU) REFERENCES Stock(SKU)
);
