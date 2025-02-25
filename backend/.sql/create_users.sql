CREATE SCHEMA Users;
GO

CREATE TABLE Users.Accounts (
   id INT IDENTITY(1,1) PRIMARY KEY,
   email VARCHAR(255) NOT NULL UNIQUE,
   username VARCHAR(255) NOT NULL UNIQUE,
   password CHAR(64) NOT NULL,
   INDEX idx_username (username),
   INDEX idx_email (email)
);

CREATE TABLE Users.Orders (
   id INT IDENTITY(1,1) PRIMARY KEY,
   user_id INT NOT NULL,
   order_date DATE NOT NULL,
   FOREIGN KEY (user_id) REFERENCES Users.Accounts(id)
);