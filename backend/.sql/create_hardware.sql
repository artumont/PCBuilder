CREATE SCHEMA Hardware;
GO

CREATE TABLE Hardware.CPUs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   socket VARCHAR(255) NOT NULL,
   cores INT NOT NULL,
   clock_speed DECIMAL(4,2) NOT NULL, -- @note: This is in GHz
   threads INT NOT NULL,
   price MONEY,
   INDEX idx_socket (socket)
);

CREATE TABLE Hardware.GPUs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   chipset VARCHAR(255) NOT NULL, -- @note: This is the GPU chipset, e.g. Navi 22, AD102, etc.
   vram INT NOT NULL, -- @note: This is in MB
   wattage INT NOT NULL, -- @note: This is in Watts
   price MONEY,
);

CREATE TABLE Hardware.Motherboards (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   socket VARCHAR(255) NOT NULL,
   sata_storage_slots INT NOT NULL,
   m_2_storage_slots INT NOT NULL,
   ram_slots INT NOT NULL,
   ram_type VARCHAR(255) NOT NULL, -- @note: This is DDR3, DDR4, etc.
   size VARCHAR(255) NOT NULL, -- @note: this is using the ATX, Micro-ATX, Mini-ITX, etc. standard
   chipset VARCHAR(255) NOT NULL, -- @note: This is the chipset of the motherboard, e.g. B450, Z590, etc.
   price MONEY,
   INDEX idx_socket (socket)
);

CREATE TABLE Hardware.RAMs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   speed INT NOT NULL, -- @note: This is in MHz
   size INT NOT NULL, -- @note: This is in MB
   type VARCHAR(255) NOT NULL, -- @note: This is DDR3, DDR4, etc.
   price MONEY,
   INDEX idx_type (type)
);

CREATE TABLE Hardware.Storages (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   format VARCHAR(255) NOT NULL, -- @note: This is HDD, SSD, etc.
   protocol VARCHAR(255) NOT NULL, -- @note: This is SATA, NVMe, etc.
   size INT NOT NULL, -- @note: This is in MB
   price MONEY,
   INDEX idx_format (format),
   INDEX idx_protocol (protocol)
);

CREATE TABLE Hardware.PSUs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   wattage INT NOT NULL, -- @note: This is in Watts
   size VARCHAR(255) NOT NULL, -- @note: This is using the ATX, SFX, etc. standard
   price MONEY
);

CREATE TABLE Hardware.Cases (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   size VARCHAR(255) NOT NULL, -- @note: This is using the ATX, Micro-ATX, Mini-ITX, etc. standard
   price MONEY,
   INDEX idx_size (size)
);

CREATE TABLE Hardware.Coolers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    socket VARCHAR(255) NOT NULL, -- @note: This is the socket that the cooler is compatible with (they are multiple but for the sake of my sanity I'm keeping it simple)
    image_url VARCHAR(2048),
    price MONEY
);

CREATE TABLE Hardware.Monitors (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(2048),
    resolution VARCHAR(255) NOT NULL,
    refresh_rate INT NOT NULL, -- @note: This is in Hz
    size DECIMAL(4,2) NOT NULL, -- @note: This is in inches
    panel_type VARCHAR(255) NOT NULL, -- @note: This is TN, IPS, etc
    price MONEY
);