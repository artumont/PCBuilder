CREATE SCHEMA Hardware;
GO

CREATE TABLE Hardware.CPUs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   socket VARCHAR(255) NOT NULL,
   clock_speed DECIMAL(4,2) NOT NULL, -- @note: This is in GHz
   cores INT NOT NULL,
   threads INT NOT NULL,
   price MONEY,
   INDEX idx_socket (socket)
);

CREATE TABLE Hardware.Chipsets (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    INDEX idx_chipset_name (name)
);

CREATE TABLE Hardware.CPUChipsetCompatibility (
    cpu_id INT NOT NULL,
    chipset_id INT NOT NULL,
    PRIMARY KEY (cpu_id, chipset_id),
    FOREIGN KEY (cpu_id) REFERENCES Hardware.CPUs(id),
    FOREIGN KEY (chipset_id) REFERENCES Hardware.Chipsets(id)
);

CREATE TABLE Hardware.GPUs (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   chipset VARCHAR(255) NOT NULL, -- @note: This is the GPU chipset, e.g. GTX 1080, RX 580, etc.
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
   chipset_id INT NOT NULL, -- @note: This is a foreign key to the Chipsets table
   FOREIGN KEY (chipset_id) REFERENCES Hardware.Chipsets(id),
   price MONEY,
   INDEX idx_socket (socket)
);

CREATE TABLE Hardware.RAM (
   id INT IDENTITY(1,1) PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(2048),
   speed INT NOT NULL, -- @note: This is in MHz
   size INT NOT NULL, -- @note: This is in MB
   type VARCHAR(255) NOT NULL, -- @note: This is DDR3, DDR4, etc.
   price MONEY,
   INDEX idx_type (type)
);

CREATE TABLE Hardware.Storage (
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

CREATE TABLE Hardware.PSU (
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

CREATE TABLE Hardware.Sockets (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE, -- @note: This is the name of the socket, e.g. LGA1151, AM4, etc.
    INDEX idx_socket_name (name)
);

CREATE TABLE Hardware.Coolers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(2048),
    price MONEY
);

CREATE TABLE Hardware.CoolerSocketCompatibility (
    cooler_id INT NOT NULL,
    socket_id INT NOT NULL,
    PRIMARY KEY (cooler_id, socket_id),
    FOREIGN KEY (cooler_id) REFERENCES Hardware.Coolers(id),
    FOREIGN KEY (socket_id) REFERENCES Hardware.Sockets(id)
);

CREATE TABLE Hardware.Monitor (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(2048),
    resolution VARCHAR(255) NOT NULL,
    refresh_rate INT NOT NULL, -- @note: This is in Hz
    size DECIMAL(4,2) NOT NULL, -- @note: This is in inches
    panel_type VARCHAR(255) NOT NULL, -- @note: This is TN, IPS, etc
    price MONEY
);