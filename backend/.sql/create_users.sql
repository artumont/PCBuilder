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

CREATE TABLE Users.Configs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   user_id INT NOT NULL,
   config_data VARCHAR(1024) NOT NULL,
   creation_date DATETIME DEFAULT GETDATE(),
   FOREIGN KEY (user_id) REFERENCES Users.Accounts(id)
);