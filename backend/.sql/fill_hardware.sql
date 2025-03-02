-- Insert CPUs
INSERT INTO Hardware.CPUs (name, image_url, socket, cores, clock_speed, threads, price) VALUES
('Intel Core i9-13900K', 'https://m.media-amazon.com/images/I/618lSbUOHTL._AC_UL320_.jpg', 'LGA1700', 24, 5.80, 32, 589.99),
('AMD Ryzen 9 7950X3D', 'https://m.media-amazon.com/images/I/5116zdA9uyL._AC_UY218_.jpg', 'AM5', 16, 5.70, 32, 699.99),
('AMD Ryzen 9 5950X', 'https://m.media-amazon.com/images/I/61ISSSA+x+L._AC_UY218_.jpg', 'AM4', 16, 4.90, 32, 549.99),
('Intel Core i9-12900KS', 'https://m.media-amazon.com/images/I/51klBAsxGHL._AC_UY218_.jpg', 'LGA1700', 16, 5.50, 24, 739.99),
('AMD Ryzen 9 7900X', 'https://m.media-amazon.com/images/I/51OEiWrUtqL._AC_UL320_.jpg', 'AM5', 12, 5.60, 24, 549.99),
('Intel Core i7-13700K', 'https://m.media-amazon.com/images/I/51iNzatVxLL._AC_UY218_.jpg', 'LGA1700', 16, 5.40, 24, 419.99),
('AMD Ryzen 7 7800X3D', 'https://m.media-amazon.com/images/I/51HqC0rU9HL._AC_UY218_.jpg', 'AM5', 8, 5.00, 16, 449.99),
('Intel Core i5-13600K', 'https://m.media-amazon.com/images/I/61My4F2-XUL._AC_UY218_.jpg', 'LGA1700', 14, 5.10, 20, 319.99),
('AMD Ryzen 7 5800X3D', 'https://m.media-amazon.com/images/I/61TXaMf2WyL._AC_UY218_.jpg', 'AM4', 8, 4.50, 16, 329.99),
('Intel Core i5-12600K', 'https://m.media-amazon.com/images/I/51ldM0r7NML._AC_UY218_.jpg', 'LGA1700', 10, 4.90, 16, 279.99),
('AMD Ryzen 5 7600X', 'https://m.media-amazon.com/images/I/41j61j9aZGL._AC_SL1500_.jpg', 'AM5', 6, 5.30, 12, 299.99),
('Intel Core i5-13400F', 'https://m.media-amazon.com/images/I/6138mFBll6L._AC_UY218_.jpg', 'LGA1700', 10, 4.60, 16, 209.99);

-- Insert GPUs
INSERT INTO Hardware.GPUs (name, image_url, chipset, vram, wattage, price) VALUES
('NVIDIA GeForce RTX 4090', 'https://m.media-amazon.com/images/I/71hoPufXoDL._AC_UY218_.jpg', 'AD102', 24576, 450, 1599.99),
('AMD Radeon RX 7900 XTX', 'https://m.media-amazon.com/images/I/81ba+dsNlUL._AC_UY218_.jpg', 'Navi 31', 24576, 355, 999.99),
('NVIDIA GeForce RTX 4080', 'https://m.media-amazon.com/images/I/81dwV4r1VaL._AC_UY218_.jpg', 'AD103', 16384, 320, 1199.99),
('AMD Radeon RX 7900 XT', 'https://m.media-amazon.com/images/I/81ba+dsNlUL._AC_UY218_.jpg', 'Navi 31', 20480, 315, 899.99),
('NVIDIA GeForce RTX 4070 Ti', 'https://m.media-amazon.com/images/I/81dwV4r1VaL._AC_UY218_.jpg', 'AD104', 12288, 285, 799.99),
('AMD Radeon RX 7800 XT', 'https://m.media-amazon.com/images/I/81ba+dsNlUL._AC_UY218_.jpg', 'Navi 32', 16384, 263, 499.99),
('NVIDIA GeForce RTX 4070', 'https://m.media-amazon.com/images/I/81dwV4r1VaL._AC_UY218_.jpg', 'AD104', 12288, 200, 599.99),
('AMD Radeon RX 6950 XT', 'https://m.media-amazon.com/images/I/71B1xVx4D4L._AC_UY218_.jpg', 'Navi 21', 16384, 335, 699.99),
('NVIDIA GeForce RTX 3080', 'https://m.media-amazon.com/images/I/81cgUKnQK2L._AC_UY218_.jpg', 'GA102', 10240, 320, 699.99),
('AMD Radeon RX 6800 XT', 'https://m.media-amazon.com/images/I/81L7-qPS0ZL._AC_UY218_.jpg', 'Navi 21', 16384, 300, 579.99),
('NVIDIA GeForce RTX 3070', 'https://m.media-amazon.com/images/I/71hoPufXoDL._AC_UY218_.jpg', 'GA104', 8192, 220, 499.99),
('AMD Radeon RX 6700 XT', 'https://m.media-amazon.com/images/I/81ba+dsNlUL._AC_UY218_.jpg', 'Navi 22', 12288, 230, 479.99);

-- Insert Motherboards
INSERT INTO Hardware.Motherboards (name, image_url, socket, sata_storage_slots, m2_storage_slots, ram_slots, ram_type, size, chipset, price) VALUES
('ASUS ROG Maximus Z790 Hero', 'https://m.media-amazon.com/images/I/81CpgF-+P4L._AC_UY218_.jpg', 'LGA1700', 6, 5, 4, 'DDR5', 'ATX', 'Z790', 629.99),
('MSI MEG X670E ACE', 'https://m.media-amazon.com/images/I/811eMHgYpdL._AC_UY218_.jpg', 'AM5', 6, 5, 4, 'DDR5', 'ATX', 'X670E', 699.99),
('Gigabyte X570S AORUS Master', 'https://m.media-amazon.com/images/I/71G9DjmRntL._AC_UY218_.jpg', 'AM4', 6, 4, 4, 'DDR4', 'ATX', 'X570S', 389.99),
('ASRock Z790 Taichi', 'https://m.media-amazon.com/images/I/81rX0VhoStL._AC_UY218_.jpg', 'LGA1700', 8, 4, 4, 'DDR5', 'ATX', 'Z790', 479.99),
('ASUS ROG STRIX B650-E', 'https://m.media-amazon.com/images/I/81MH+nx+shL._AC_UY218_.jpg', 'AM5', 4, 3, 4, 'DDR5', 'ATX', 'B650E', 289.99),
('MSI MPG B550 GAMING EDGE', 'https://m.media-amazon.com/images/I/61hPyxPlRjL._AC_UY218_.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 'B550', 179.99),
('Gigabyte Z690 AORUS PRO', 'https://m.media-amazon.com/images/I/71jwye+xDLL._AC_UY218_.jpg', 'LGA1700', 6, 4, 4, 'DDR5', 'ATX', 'Z690', 329.99),
('ASUS TUF GAMING B760M-PLUS', 'https://m.media-amazon.com/images/I/81mtJaH+lnL._AC_UY218_.jpg', 'LGA1700', 4, 2, 4, 'DDR4', 'Micro-ATX', 'B760', 169.99),
('ASRock B550 Steel Legend', 'https://m.media-amazon.com/images/I/81S9D7bqEzL._AC_UY218_.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 'B550', 169.99),
('MSI PRO Z690-A', 'https://m.media-amazon.com/images/I/71xcIxpGPEL._AC_UY218_.jpg', 'LGA1700', 6, 3, 4, 'DDR4', 'ATX', 'Z690', 219.99),
('Gigabyte B650 GAMING X', 'https://m.media-amazon.com/images/I/81vtcmUUsPL._AC_UY218_.jpg', 'AM5', 4, 2, 4, 'DDR5', 'ATX', 'B650', 199.99),
('ASUS PRIME X670E-PRO', 'https://m.media-amazon.com/images/I/81ohPDfik0L._AC_UY218_.jpg', 'AM5', 4, 4, 4, 'DDR5', 'ATX', 'X670E', 449.99);

-- Insert RAMs
INSERT INTO Hardware.RAMs (name, image_url, speed, size, type, price) VALUES
('G.SKILL Trident Z5 RGB', 'https://m.media-amazon.com/images/I/71DiVTefKBL._AC_UY218_.jpg', 6400, 32768, 'DDR5', 299.99),
('Corsair Dominator Platinum RGB', 'https://m.media-amazon.com/images/I/61phjjKKrmL._AC_UY218_.jpg', 6200, 32768, 'DDR5', 319.99),
('Crucial DDR5', 'https://m.media-amazon.com/images/I/51EJt5geVEL._AC_UY218_.jpg', 5600, 32768, 'DDR5', 239.99),
('Kingston FURY Beast RGB', 'https://m.media-amazon.com/images/I/61QjHcl1ISL._AC_UY218_.jpg', 6000, 32768, 'DDR5', 279.99),
('TeamGroup T-Force Delta RGB', 'https://m.media-amazon.com/images/I/81ov4cFmdaL._AC_UY218_.jpg', 3600, 32768, 'DDR4', 129.99),
('G.SKILL Ripjaws V', 'https://m.media-amazon.com/images/I/618SEnJR1nL._AC_UY218_.jpg', 3600, 32768, 'DDR4', 119.99),
('Corsair Vengeance RGB Pro', 'https://m.media-amazon.com/images/I/71e6YWJio-L._AC_UY218_.jpg', 3600, 32768, 'DDR4', 139.99),
('Crucial Ballistix RGB', 'https://m.media-amazon.com/images/I/51Niqgy0cHL._AC_UY218_.jpg', 3600, 32768, 'DDR4', 134.99),
('Kingston FURY Renegade', 'https://m.media-amazon.com/images/I/7176NXYFJzL._AC_UY218_.jpg', 6400, 32768, 'DDR5', 329.99),
('Thermaltake TOUGHRAM RGB', 'https://m.media-amazon.com/images/I/710M8ghjtcL._AC_UY218_.jpg', 3600, 32768, 'DDR4', 149.99),
('G.SKILL Trident Z RGB', 'https://m.media-amazon.com/images/I/61l4EStxhnL._AC_UY218_.jpg', 3600, 32768, 'DDR4', 159.99),
('Corsair Vengeance LPX', 'https://m.media-amazon.com/images/I/61wCOVcyvFL._AC_UY218_.jpg', 3200, 16384, 'DDR4', 69.99);

-- Insert Storages
INSERT INTO Hardware.Storages (name, image_url, format, protocol, size, price) VALUES
('Samsung 990 PRO', 'https://m.media-amazon.com/images/I/81WuG6lQuDL._AC_UY218_.jpg', 'SSD', 'NVMe', 2097152, 289.99),
('WD Black SN850X', 'https://m.media-amazon.com/images/I/61KeSQhDm4L._AC_UY218_.jpg', 'SSD', 'NVMe', 2097152, 279.99),
('Seagate FireCuda 530', 'https://m.media-amazon.com/images/I/61ZL9Qpo1-L._AC_UY218_.jpg', 'SSD', 'NVMe', 2097152, 299.99),
('Corsair MP600 PRO XT', 'https://m.media-amazon.com/images/I/71RCyId7QIL._AC_UY218_.jpg', 'SSD', 'NVMe', 2097152, 269.99),
('Samsung 870 EVO', 'https://m.media-amazon.com/images/I/711qZ63B-BL._AC_UY218_.jpg', 'SSD', 'SATA', 2097152, 169.99),
('WD Red Pro', 'https://m.media-amazon.com/images/I/61LfBBllx7L._AC_UY218_.jpg', 'HDD', 'SATA', 14680064, 399.99),
('Crucial P5 Plus', 'https://m.media-amazon.com/images/I/51Niqgy0cHL._AC_UY218_.jpg', 'SSD', 'NVMe', 1048576, 149.99),
('Seagate IronWolf Pro', 'https://m.media-amazon.com/images/I/71yHXh6J6iL._AC_UY218_.jpg', 'HDD', 'SATA', 16777216, 459.99),
('SK hynix Gold P31', 'https://m.media-amazon.com/images/I/71sgX4axm2L._AC_UY218_.jpg', 'SSD', 'NVMe', 1048576, 129.99),
('WD Black', 'https://m.media-amazon.com/images/I/61KeSQhDm4L._AC_UY218_.jpg', 'HDD', 'SATA', 4194304, 149.99),
('Samsung 980', 'https://m.media-amazon.com/images/I/61ZL9Qpo1-L._AC_UY218_.jpg', 'SSD', 'NVMe', 1048576, 119.99),
('Seagate Barracuda', 'https://m.media-amazon.com/images/I/71AR7Ch248L._AC_UY218_.jpg', 'HDD', 'SATA', 2097152, 54.99);

-- Insert PSUs
INSERT INTO Hardware.PSUs (name, image_url, wattage, size, price) VALUES
('Corsair RM1000x', 'https://m.media-amazon.com/images/I/81dwGXVwpgL._AC_UY218_.jpg', 1000, 'ATX', 189.99),
('EVGA SuperNOVA 850 G6', 'https://m.media-amazon.com/images/I/71EJ7ZoorJS._AC_UY218_.jpg', 850, 'ATX', 149.99),
('be quiet! Dark Power 12', 'https://m.media-amazon.com/images/I/81HxCfuGyeL._AC_UY218_.jpg', 1200, 'ATX', 299.99),
('Seasonic FOCUS GX-650', 'https://m.media-amazon.com/images/I/7162Q2lR7sL._AC_UY218_.jpg', 650, 'ATX', 119.99),
('Corsair HX1200i', 'https://m.media-amazon.com/images/I/61p2oIuPU-L._AC_UY218_.jpg', 1200, 'ATX', 299.99),
('EVGA SuperNOVA 1300 G+', 'https://m.media-amazon.com/images/I/71TBlXU8+mL._AC_UY218_.jpg', 1300, 'ATX', 249.99),
('be quiet! Straight Power 11', 'https://m.media-amazon.com/images/I/81EU0ntJC-L._AC_UY218_.jpg', 850, 'ATX', 169.99),
('Seasonic PRIME TX-1000', 'https://m.media-amazon.com/images/I/81+8zyHQFWL._AC_UY218_.jpg', 1000, 'ATX', 299.99),
('Thermaltake Toughpower GF1', 'https://m.media-amazon.com/images/I/71V6+frcgQL._AC_UY218_.jpg', 850, 'ATX', 149.99),
('Phanteks AMP', 'https://m.media-amazon.com/images/I/81k55rfk1iL._AC_UY218_.jpg', 750, 'ATX', 129.99),
('MSI MPG A850GF', 'https://m.media-amazon.com/images/I/81RQJMpmjoL._AC_UY218_.jpg', 850, 'ATX', 139.99),
('Corsair SF750', 'https://m.media-amazon.com/images/I/71AOzZif9VL._AC_UY218_.jpg', 750, 'SFX', 184.99);

-- Insert Cases
INSERT INTO Hardware.Cases (name, image_url, size, price) VALUES
('Lian Li O11 Dynamic EVO', 'https://m.media-amazon.com/images/I/61OeOaBlAIL._AC_UY218_.jpg', 'ATX', 169.99),
('Fractal Design Meshify 2', 'https://m.media-amazon.com/images/I/91JfXda95sL._AC_UY218_.jpg', 'ATX', 149.99),
('NZXT H510 Flow', 'https://m.media-amazon.com/images/I/71SIs5kxpYL._AC_UL320_.jpg', 'ATX', 89.99),
('Phanteks Eclipse P300A', 'https://m.media-amazon.com/images/I/71J4iohAlaL._AC_UY218_.jpg', 'ATX', 69.99),
('Corsair 5000D Airflow', 'https://m.media-amazon.com/images/I/71XO-m7qldL._AC_UY218_.jpg', 'ATX', 174.99),
('be quiet! Pure Base 500DX', 'https://m.media-amazon.com/images/I/61YQafwg6gL._AC_UY218_.jpg', 'ATX', 109.99),
('Cooler Master TD500 Mesh', 'https://m.media-amazon.com/images/I/81mlDtLolEL._AC_UY218_.jpg', 'ATX', 99.99),
('Lian Li LANCOOL III', 'https://m.media-amazon.com/images/I/51sVCkaP4QL._AC_UY218_.jpg', 'ATX', 159.99),
('Phanteks Eclipse G360A', 'https://m.media-amazon.com/images/I/813M1+AB6kL._AC_UY218_.jpg', 'ATX', 99.99),
('Fractal Design Pop Air', 'https://m.media-amazon.com/images/I/71J4iohAlaL._AC_UY218_.jpg', 'ATX', 89.99),
('NZXT H7 Flow', 'https://m.media-amazon.com/images/I/51rOo9MITKL._AC_UY218_.jpg', 'ATX', 129.99),
('Corsair 4000D', 'https://m.media-amazon.com/images/I/71J4iohAlaL._AC_UY218_.jpg', 'ATX', 94.99);

-- Insert Coolers
INSERT INTO Hardware.Coolers (name, socket, image_url, price) VALUES
('NZXT Kraken X73', 'LGA1700', 'https://m.media-amazon.com/images/I/71RVYwj2r-L._AC_UY218_.jpg', 199.99),
('be quiet! Dark Rock Pro 4', 'AM5', 'https://m.media-amazon.com/images/I/81ogi-krqkL._AC_UY218_.jpg', 89.99),
('Noctua NH-D15', 'AM4', 'https://m.media-amazon.com/images/I/81Ni+xrVXeL._AC_UY218_.jpg', 99.99),
('Corsair iCUE H150i ELITE', 'LGA1700', 'https://m.media-amazon.com/images/I/71RVYwj2r-L._AC_UY218_.jpg', 189.99),
('Arctic Liquid Freezer II 360', 'AM5', 'https://m.media-amazon.com/images/I/81ogi-krqkL._AC_UY218_.jpg', 139.99),
('Deepcool AK620', 'LGA1700', 'https://m.media-amazon.com/images/I/71RVYwj2r-L._AC_UY218_.jpg', 69.99),
('NZXT Kraken Z73', 'AM4', 'https://m.media-amazon.com/images/I/81Ni+xrVXeL._AC_UY218_.jpg', 299.99),
('Lian Li Galahad 360', 'AM5', 'https://m.media-amazon.com/images/I/81ogi-krqkL._AC_UY218_.jpg', 159.99),
('Scythe Fuma 2', 'LGA1700', 'https://m.media-amazon.com/images/I/71RVYwj2r-L._AC_UY218_.jpg', 59.99),
('EK AIO Elite 360', 'AM5', 'https://m.media-amazon.com/images/I/81ogi-krqkL._AC_UY218_.jpg', 189.99),
('Noctua NH-U12A', 'AM4', 'https://m.media-amazon.com/images/I/81Ni+xrVXeL._AC_UY218_.jpg', 109.99),
('be quiet! Pure Rock 2', 'LGA1700', 'https://m.media-amazon.com/images/I/71RVYwj2r-L._AC_UY218_.jpg', 44.99);

-- Insert Monitors
INSERT INTO Hardware.Monitors (name, image_url, resolution, refresh_rate, size, panel_type, price) VALUES
('LG 27GP950-B', 'https://m.media-amazon.com/images/I/71pT6MAfXmS._AC_UY218_.jpg', '3840x2160', 144, 27.00, 'IPS', 799.99),
('Samsung Odyssey G7', 'https://m.media-amazon.com/images/I/81F6CeKEkzL._AC_UY218_.jpg', '2560x1440', 240, 32.00, 'VA', 699.99),
('ASUS ROG Swift PG279QM', 'https://m.media-amazon.com/images/I/81SmgyBTqPL._AC_UY218_.jpg', '2560x1440', 240, 27.00, 'IPS', 849.99),
('Dell S2721DGF', 'https://m.media-amazon.com/images/I/71b+UNcbNzL._AC_UY218_.jpg', '2560x1440', 165, 27.00, 'IPS', 499.99),
('Samsung Odyssey Neo G8', 'https://m.media-amazon.com/images/I/81dDR+bGO3L._AC_UY218_.jpg', '3840x2160', 240, 32.00, 'VA', 1299.99),
('LG 34GP83A-B', 'https://m.media-amazon.com/images/I/81soN3bwVFL._AC_UY218_.jpg', '3440x1440', 160, 34.00, 'IPS', 799.99),
('ASUS TUF Gaming VG27AQ', 'https://m.media-amazon.com/images/I/71L4fB4r8rL._AC_UY218_.jpg', '2560x1440', 165, 27.00, 'IPS', 449.99),
('Gigabyte M32U', 'https://m.media-amazon.com/images/I/71PqY4-jbHL._AC_UY218_.jpg', '3840x2160', 144, 32.00, 'IPS', 699.99),
('MSI Optix MAG274QRF-QD', 'https://m.media-amazon.com/images/I/81h0w75BgqL._AC_UY218_.jpg', '2560x1440', 165, 27.00, 'IPS', 449.99),
('Alienware AW3423DW', 'https://m.media-amazon.com/images/I/81Pm4yGtiYL._AC_UY218_.jpg', '3440x1440', 175, 34.00, 'OLED', 1299.99),
('LG 27GL850-B', 'https://m.media-amazon.com/images/I/616ZOS5bnnL._AC_UY218_.jpg', '2560x1440', 144, 27.00, 'IPS', 399.99),
('Samsung Odyssey G5', 'https://m.media-amazon.com/images/I/81Pm4yGtiYL._AC_UY218_.jpg', '2560x1440', 165, 27.00, 'VA', 299.99);
