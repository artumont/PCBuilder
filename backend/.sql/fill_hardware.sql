-- Insert CPUs
INSERT INTO Hardware.CPUs (name, image_url, socket, cores, clock_speed, threads, price) VALUES
('Intel Core i9-13900K', 'https://example.com/i9-13900k.jpg', 'LGA1700', 24, 5.80, 32, 589.99),
('AMD Ryzen 9 7950X3D', 'https://example.com/7950x3d.jpg', 'AM5', 16, 5.70, 32, 699.99),
('AMD Ryzen 9 5950X', 'https://example.com/5950x.jpg', 'AM4', 16, 4.90, 32, 549.99),
('Intel Core i9-12900KS', 'https://example.com/i9-12900ks.jpg', 'LGA1700', 16, 5.50, 24, 739.99),
('AMD Ryzen 9 7900X', 'https://example.com/7900x.jpg', 'AM5', 12, 5.60, 24, 549.99),
('Intel Core i7-13700K', 'https://example.com/i7-13700k.jpg', 'LGA1700', 16, 5.40, 24, 419.99),
('AMD Ryzen 7 7800X3D', 'https://example.com/7800x3d.jpg', 'AM5', 8, 5.00, 16, 449.99),
('Intel Core i5-13600K', 'https://example.com/i5-13600k.jpg', 'LGA1700', 14, 5.10, 20, 319.99),
('AMD Ryzen 7 5800X3D', 'https://example.com/5800x3d.jpg', 'AM4', 8, 4.50, 16, 329.99),
('Intel Core i5-12600K', 'https://example.com/i5-12600k.jpg', 'LGA1700', 10, 4.90, 16, 279.99),
('AMD Ryzen 5 7600X', 'https://example.com/7600x.jpg', 'AM5', 6, 5.30, 12, 299.99),
('Intel Core i5-13400F', 'https://example.com/i5-13400f.jpg', 'LGA1700', 10, 4.60, 16, 209.99);

-- Insert GPUs
INSERT INTO Hardware.GPUs (name, image_url, chipset, vram, wattage, price) VALUES
('NVIDIA GeForce RTX 4090', 'https://example.com/rtx4090.jpg', 'AD102', 24576, 450, 1599.99),
('AMD Radeon RX 7900 XTX', 'https://example.com/7900xtx.jpg', 'Navi 31', 24576, 355, 999.99),
('NVIDIA GeForce RTX 4080', 'https://example.com/rtx4080.jpg', 'AD103', 16384, 320, 1199.99),
('AMD Radeon RX 7900 XT', 'https://example.com/7900xt.jpg', 'Navi 31', 20480, 315, 899.99),
('NVIDIA GeForce RTX 4070 Ti', 'https://example.com/rtx4070ti.jpg', 'AD104', 12288, 285, 799.99),
('AMD Radeon RX 7800 XT', 'https://example.com/7800xt.jpg', 'Navi 32', 16384, 263, 499.99),
('NVIDIA GeForce RTX 4070', 'https://example.com/rtx4070.jpg', 'AD104', 12288, 200, 599.99),
('AMD Radeon RX 6950 XT', 'https://example.com/6950xt.jpg', 'Navi 21', 16384, 335, 699.99),
('NVIDIA GeForce RTX 3080', 'https://example.com/rtx3080.jpg', 'GA102', 10240, 320, 699.99),
('AMD Radeon RX 6800 XT', 'https://example.com/6800xt.jpg', 'Navi 21', 16384, 300, 579.99),
('NVIDIA GeForce RTX 3070', 'https://example.com/rtx3070.jpg', 'GA104', 8192, 220, 499.99),
('AMD Radeon RX 6700 XT', 'https://example.com/6700xt.jpg', 'Navi 22', 12288, 230, 479.99);

-- Insert Motherboards
INSERT INTO Hardware.Motherboards (name, image_url, socket, sata_storage_slots, m_2_storage_slots, ram_slots, ram_type, size, chipset, price) VALUES
('ASUS ROG Maximus Z790 Hero', 'https://example.com/maximus-z790.jpg', 'LGA1700', 6, 5, 4, 'DDR5', 'ATX', 'Z790', 629.99),
('MSI MEG X670E ACE', 'https://example.com/x670e-ace.jpg', 'AM5', 6, 5, 4, 'DDR5', 'ATX', 'X670E', 699.99),
('Gigabyte X570S AORUS Master', 'https://example.com/x570s-master.jpg', 'AM4', 6, 4, 4, 'DDR4', 'ATX', 'X570S', 389.99),
('ASRock Z790 Taichi', 'https://example.com/z790-taichi.jpg', 'LGA1700', 8, 4, 4, 'DDR5', 'ATX', 'Z790', 479.99),
('ASUS ROG STRIX B650-E', 'https://example.com/b650e-strix.jpg', 'AM5', 4, 3, 4, 'DDR5', 'ATX', 'B650E', 289.99),
('MSI MPG B550 GAMING EDGE', 'https://example.com/b550-edge.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 'B550', 179.99),
('Gigabyte Z690 AORUS PRO', 'https://example.com/z690-pro.jpg', 'LGA1700', 6, 4, 4, 'DDR5', 'ATX', 'Z690', 329.99),
('ASUS TUF GAMING B760M-PLUS', 'https://example.com/b760m-plus.jpg', 'LGA1700', 4, 2, 4, 'DDR4', 'Micro-ATX', 'B760', 169.99),
('ASRock B550 Steel Legend', 'https://example.com/b550-legend.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 'B550', 169.99),
('MSI PRO Z690-A', 'https://example.com/z690-a.jpg', 'LGA1700', 6, 3, 4, 'DDR4', 'ATX', 'Z690', 219.99),
('Gigabyte B650 GAMING X', 'https://example.com/b650-gaming.jpg', 'AM5', 4, 2, 4, 'DDR5', 'ATX', 'B650', 199.99),
('ASUS PRIME X670E-PRO', 'https://example.com/x670e-pro.jpg', 'AM5', 4, 4, 4, 'DDR5', 'ATX', 'X670E', 449.99);

-- Insert RAMs
INSERT INTO Hardware.RAMs (name, image_url, speed, size, type, price) VALUES
('G.SKILL Trident Z5 RGB', 'https://example.com/trident-z5.jpg', 6400, 32768, 'DDR5', 299.99),
('Corsair Dominator Platinum RGB', 'https://example.com/dominator-rgb.jpg', 6200, 32768, 'DDR5', 319.99),
('Crucial DDR5', 'https://example.com/crucial-ddr5.jpg', 5600, 32768, 'DDR5', 239.99),
('Kingston FURY Beast RGB', 'https://example.com/fury-beast-rgb.jpg', 6000, 32768, 'DDR5', 279.99),
('TeamGroup T-Force Delta RGB', 'https://example.com/tforce-delta.jpg', 3600, 32768, 'DDR4', 129.99),
('G.SKILL Ripjaws V', 'https://example.com/ripjaws-v.jpg', 3600, 32768, 'DDR4', 119.99),
('Corsair Vengeance RGB Pro', 'https://example.com/vengeance-rgb-pro.jpg', 3600, 32768, 'DDR4', 139.99),
('Crucial Ballistix RGB', 'https://example.com/ballistix-rgb.jpg', 3600, 32768, 'DDR4', 134.99),
('Kingston FURY Renegade', 'https://example.com/fury-renegade.jpg', 6400, 32768, 'DDR5', 329.99),
('Thermaltake TOUGHRAM RGB', 'https://example.com/toughram-rgb.jpg', 3600, 32768, 'DDR4', 149.99),
('G.SKILL Trident Z RGB', 'https://example.com/trident-rgb.jpg', 3600, 32768, 'DDR4', 159.99),
('Corsair Vengeance LPX', 'https://example.com/vengeance-lpx.jpg', 3200, 16384, 'DDR4', 69.99);

-- Insert Storages
INSERT INTO Hardware.Storages (name, image_url, format, protocol, size, price) VALUES
('Samsung 990 PRO', 'https://example.com/990-pro.jpg', 'SSD', 'NVMe', 2097152, 289.99),
('WD Black SN850X', 'https://example.com/sn850x.jpg', 'SSD', 'NVMe', 2097152, 279.99),
('Seagate FireCuda 530', 'https://example.com/firecuda-530.jpg', 'SSD', 'NVMe', 2097152, 299.99),
('Corsair MP600 PRO XT', 'https://example.com/mp600-xt.jpg', 'SSD', 'NVMe', 2097152, 269.99),
('Samsung 870 EVO', 'https://example.com/870-evo.jpg', 'SSD', 'SATA', 2097152, 169.99),
('WD Red Pro', 'https://example.com/red-pro.jpg', 'HDD', 'SATA', 14680064, 399.99),
('Crucial P5 Plus', 'https://example.com/p5-plus.jpg', 'SSD', 'NVMe', 1048576, 149.99),
('Seagate IronWolf Pro', 'https://example.com/ironwolf-pro.jpg', 'HDD', 'SATA', 16777216, 459.99),
('SK hynix Gold P31', 'https://example.com/gold-p31.jpg', 'SSD', 'NVMe', 1048576, 129.99),
('WD Black', 'https://example.com/wd-black.jpg', 'HDD', 'SATA', 4194304, 149.99),
('Samsung 980', 'https://example.com/980.jpg', 'SSD', 'NVMe', 1048576, 119.99),
('Seagate Barracuda', 'https://example.com/barracuda.jpg', 'HDD', 'SATA', 2097152, 54.99);

-- Insert PSUs
INSERT INTO Hardware.PSUs (name, image_url, wattage, size, price) VALUES
('Corsair RM1000x', 'https://example.com/rm1000x.jpg', 1000, 'ATX', 189.99),
('EVGA SuperNOVA 850 G6', 'https://example.com/supernova-850.jpg', 850, 'ATX', 149.99),
('be quiet! Dark Power 12', 'https://example.com/dark-power-12.jpg', 1200, 'ATX', 299.99),
('Seasonic FOCUS GX-650', 'https://example.com/focus-gx.jpg', 650, 'ATX', 119.99),
('Corsair HX1200i', 'https://example.com/hx1200i.jpg', 1200, 'ATX', 299.99),
('EVGA SuperNOVA 1300 G+', 'https://example.com/supernova-1300.jpg', 1300, 'ATX', 249.99),
('be quiet! Straight Power 11', 'https://example.com/straight-power-11.jpg', 850, 'ATX', 169.99),
('Seasonic PRIME TX-1000', 'https://example.com/prime-tx.jpg', 1000, 'ATX', 299.99),
('Thermaltake Toughpower GF1', 'https://example.com/toughpower-gf1.jpg', 850, 'ATX', 149.99),
('Phanteks AMP', 'https://example.com/amp.jpg', 750, 'ATX', 129.99),
('MSI MPG A850GF', 'https://example.com/mpg-a850gf.jpg', 850, 'ATX', 139.99),
('Corsair SF750', 'https://example.com/sf750.jpg', 750, 'SFX', 184.99);

-- Insert Cases
INSERT INTO Hardware.Cases (name, image_url, size, price) VALUES
('Lian Li O11 Dynamic EVO', 'https://example.com/o11-evo.jpg', 'ATX', 169.99),
('Fractal Design Meshify 2', 'https://example.com/meshify-2.jpg', 'ATX', 149.99),
('NZXT H510 Flow', 'https://example.com/h510-flow.jpg', 'ATX', 89.99),
('Phanteks Eclipse P300A', 'https://example.com/p300a.jpg', 'ATX', 69.99),
('Corsair 5000D Airflow', 'https://example.com/5000d.jpg', 'ATX', 174.99),
('be quiet! Pure Base 500DX', 'https://example.com/500dx.jpg', 'ATX', 109.99),
('Cooler Master TD500 Mesh', 'https://example.com/td500.jpg', 'ATX', 99.99),
('Lian Li LANCOOL III', 'https://example.com/lancool-3.jpg', 'ATX', 159.99),
('Phanteks Eclipse G360A', 'https://example.com/g360a.jpg', 'ATX', 99.99),
('Fractal Design Pop Air', 'https://example.com/pop-air.jpg', 'ATX', 89.99),
('NZXT H7 Flow', 'https://example.com/h7-flow.jpg', 'ATX', 129.99),
('Corsair 4000D', 'https://example.com/4000d.jpg', 'ATX', 94.99);

-- Insert Coolers
INSERT INTO Hardware.Coolers (name, socket, image_url, price) VALUES
('NZXT Kraken X73', 'LGA1700', 'https://example.com/kraken-x73.jpg', 199.99),
('be quiet! Dark Rock Pro 4', 'AM5', 'https://example.com/dark-rock-pro-4.jpg', 89.99),
('Noctua NH-D15', 'AM4', 'https://example.com/nh-d15.jpg', 99.99),
('Corsair iCUE H150i ELITE', 'LGA1700', 'https://example.com/h150i-elite.jpg', 189.99),
('Arctic Liquid Freezer II 360', 'AM5', 'https://example.com/liquid-freezer-360.jpg', 139.99),
('Deepcool AK620', 'LGA1700', 'https://example.com/ak620.jpg', 69.99),
('NZXT Kraken Z73', 'AM4', 'https://example.com/kraken-z73.jpg', 299.99),
('Lian Li Galahad 360', 'AM5', 'https://example.com/galahad-360.jpg', 159.99),
('Scythe Fuma 2', 'LGA1700', 'https://example.com/fuma-2.jpg', 59.99),
('EK AIO Elite 360', 'AM5', 'https://example.com/ek-aio-360.jpg', 189.99),
('Noctua NH-U12A', 'AM4', 'https://example.com/nh-u12a.jpg', 109.99),
('be quiet! Pure Rock 2', 'LGA1700', 'https://example.com/pure-rock-2.jpg', 44.99);

-- Insert Monitors
INSERT INTO Hardware.Monitors (name, image_url, resolution, refresh_rate, size, panel_type, price) VALUES
('LG 27GP950-B', 'https://example.com/27gp950.jpg', '3840x2160', 144, 27.00, 'IPS', 799.99),
('Samsung Odyssey G7', 'https://example.com/odyssey-g7.jpg', '2560x1440', 240, 32.00, 'VA', 699.99),
('ASUS ROG Swift PG279QM', 'https://example.com/pg279qm.jpg', '2560x1440', 240, 27.00, 'IPS', 849.99),
('Dell S2721DGF', 'https://example.com/s2721dgf.jpg', '2560x1440', 165, 27.00, 'IPS', 499.99),
('Samsung Odyssey Neo G8', 'https://example.com/neo-g8.jpg', '3840x2160', 240, 32.00, 'VA', 1299.99),
('LG 34GP83A-B', 'https://example.com/34gp83a.jpg', '3440x1440', 160, 34.00, 'IPS', 799.99),
('ASUS TUF Gaming VG27AQ', 'https://example.com/vg27aq.jpg', '2560x1440', 165, 27.00, 'IPS', 449.99),
('Gigabyte M32U', 'https://example.com/m32u.jpg', '3840x2160', 144, 32.00, 'IPS', 699.99),
('MSI Optix MAG274QRF-QD', 'https://example.com/mag274qrf.jpg', '2560x1440', 165, 27.00, 'IPS', 449.99),
('Alienware AW3423DW', 'https://example.com/aw3423dw.jpg', '3440x1440', 175, 34.00, 'OLED', 1299.99),
('LG 27GL850-B', 'https://example.com/27gl850.jpg', '2560x1440', 144, 27.00, 'IPS', 399.99),
('Samsung Odyssey G5', 'https://example.com/odyssey-g5.jpg', '2560x1440', 165, 27.00, 'VA', 299.99);
