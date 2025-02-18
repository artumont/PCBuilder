--poner lo de primary key

CREATE TABLE Component (
   id INT PRIMARY KEY,
   title VARCHAR(200),
   class VARCHAR(100),
   specs VARCHAR(3000),
   price FLOAT,
   stock INT,
   compatibility VARCHAR(300),
   update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
   INDEX idx_title (title)
);

CREATE TABLE Prebuilds (
   id INT PRIMARY KEY,
   name VARCHAR(200) NOT NULL,
   description VARCHAR(1000),
   price FLOAT,
   rating FLOAT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Prebuild_Components (
   id INT PRIMARY KEY,
   prebuild_id INT,
   component_id INT,
   quantity INT DEFAULT 1,
   FOREIGN KEY (prebuild_id) REFERENCES Prebuilds(id),
   FOREIGN KEY (component_id) REFERENCES Component(id)
);

CREATE TABLE Orders (
   id INT PRIMARY KEY,
   id_Prebuild INT,
   id_client INT,
   order_status VARCHAR(50),
   order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   delivery_address VARCHAR(200),
   payment_method VARCHAR(100),
   price FLOAT,
   FOREIGN KEY (id_Prebuild) REFERENCES Prebuilds(id)
   INDEX idx_client_order_date (id_client, order_date)
   FOREIGN KEY (payment_method) REFERENCES Payment_Methods(payment_method)

);

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

CREATE TABLE Purchase_History (
   id INT PRIMARY KEY,
   user_id INT,
   order_id INT,
   purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (user_id) REFERENCES Users(id),
   FOREIGN KEY (order_id) REFERENCES Orders(id)
);

CREATE TABLE Payment_Methods (
   id INT PRIMARY KEY,
   user_id INT,
   payment_method VARCHAR(100),
   FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Shippings (
   id INT PRIMARY KEY,
   id_order INT,
   tracking_number VARCHAR(200),
   shipping_status VARCHAR(200),
   shipping_price FLOAT,
   shipping_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (id_order) REFERENCES Orders(id),
   INDEX idx_tracking_number (tracking_number),
   INDEX idx_id_order (id_order)
);

CREATE TABLE Payments (
   id INT PRIMARY KEY,
   id_order INT,
   price FLOAT,
   payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   payment_method_id INT,
   payment_status VARCHAR(100),
   FOREIGN KEY (id_order) REFERENCES Orders(id)
   FOREIGN KEY (payment_method_id) REFERENCES Payment_Methods(id)
   INDEX idx_id_order (id_order)

);

CREATE TABLE Stock (
   SKU INT PRIMARY KEY,
   title VARCHAR(200),
   class VARCHAR(100),
   quantity INT,
   warehouse_location VARCHAR(200),
   lastest_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Stock_History (
   id INT PRIMARY KEY,
   SKU INT,
   quantity INT,
   change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (SKU) REFERENCES Stock(SKU)
);