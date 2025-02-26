-- First, insert sockets since they are referenced by CPUs and coolers
INSERT INTO Hardware.Sockets (name) VALUES
('LGA1700'),
('AM5'),
('AM4'),
('LGA1200'),
('LGA1151'),
('TR4'),
('sTRX4'),
('LGA2066'),
('sWRX8');

-- Insert Chipsets as they are referenced by motherboards
INSERT INTO Hardware.Chipsets (name) VALUES
('Z690'),
('X570'),
('B550'),
('H610'),
('Z790'),
('B650'),
('X670'),
('Z590'),
('B560'),
('X399'),
('TRX40'),
('Z490'),
('H670'),
('B660'),
('WRX80'),
('X299');

-- Insert CPUs
INSERT INTO Hardware.CPUs (name, image_url, socket, clock_speed, cores, threads, price) VALUES
('Intel Core i9-13900K', 'https://example.com/i9-13900k.jpg', 'LGA1700', 5.80, 24, 32, 589.99),
('AMD Ryzen 9 7950X3D', 'https://example.com/7950x3d.jpg', 'AM5', 5.70, 16, 32, 699.99),
('AMD Ryzen 9 5950X', 'https://example.com/5950x.jpg', 'AM4', 4.90, 16, 32, 549.99),
('Intel Core i9-12900KS', 'https://example.com/i9-12900ks.jpg', 'LGA1700', 5.50, 16, 24, 739.99),
('AMD Ryzen 9 7900X', 'https://example.com/7900x.jpg', 'AM5', 5.60, 12, 24, 549.99),
('Intel Core i7-13700K', 'https://example.com/i7-13700k.jpg', 'LGA1700', 5.40, 16, 24, 419.99),
('AMD Ryzen 7 7800X3D', 'https://example.com/7800x3d.jpg', 'AM5', 5.00, 8, 16, 449.99),
('Intel Core i5-13600K', 'https://example.com/i5-13600k.jpg', 'LGA1700', 5.10, 14, 20, 319.99),
('AMD Ryzen 7 5800X3D', 'https://example.com/5800x3d.jpg', 'AM4', 4.50, 8, 16, 329.99),
('Intel Core i9-11900K', 'https://example.com/i9-11900k.jpg', 'LGA1200', 5.30, 8, 16, 399.99),
('AMD Threadripper PRO 5995WX', 'https://example.com/5995wx.jpg', 'sWRX8', 4.50, 64, 128, 6499.99),
('Intel Core i7-12700K', 'https://example.com/i7-12700k.jpg', 'LGA1700', 5.00, 12, 20, 349.99),
('AMD Ryzen 5 7600X', 'https://example.com/7600x.jpg', 'AM5', 5.30, 6, 12, 299.99),
('Intel Core i5-12600K', 'https://example.com/i5-12600k.jpg', 'LGA1700', 4.90, 10, 16, 279.99),
('AMD Threadripper 3990X', 'https://example.com/3990x.jpg', 'sTRX4', 4.30, 64, 128, 3990.99),
('Intel Core i9-10900K', 'https://example.com/i9-10900k.jpg', 'LGA1200', 5.30, 10, 20, 399.99),
('AMD Ryzen 5 5600X', 'https://example.com/5600x.jpg', 'AM4', 4.60, 6, 12, 199.99),
('Intel Core i7-11700K', 'https://example.com/i7-11700k.jpg', 'LGA1200', 5.00, 8, 16, 349.99),
('AMD Threadripper 3970X', 'https://example.com/3970x.jpg', 'sTRX4', 4.50, 32, 64, 1999.99),
('Intel Core i5-11600K', 'https://example.com/i5-11600k.jpg', 'LGA1200', 4.90, 6, 12, 249.99);

-- CPU-Chipset compatibility
INSERT INTO Hardware.CPUChipsetCompatibility (cpu_id, chipset_id) VALUES
(1, 5), -- i9-13900K with Z790
(2, 7), -- 7950X3D with X670
(3, 2), -- 5950X with X570
(4, 1), -- i9-12900KS with Z690
(5, 6), -- 7900X with B650
(6, 5), -- i7-13700K with Z790
(7, 7), -- 7800X3D with X670
(8, 5), -- i5-13600K with Z790
(9, 3), -- 5800X3D with B550
(10, 8), -- i9-11900K with Z590
(11, 15), -- 5995WX with WRX80
(12, 1), -- i7-12700K with Z690
(13, 6), -- 7600X with B650
(14, 1), -- i5-12600K with Z690
(15, 11), -- 3990X with TRX40
(16, 12), -- i9-10900K with Z490
(17, 3), -- 5600X with B550
(18, 9), -- i7-11700K with B560
(19, 11), -- 3970X with TRX40
(20, 9); -- i5-11600K with B560

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
('NVIDIA GeForce RTX 3090 Ti', 'https://example.com/rtx3090ti.jpg', 'GA102', 24576, 450, 999.99),
('AMD Radeon RX 6900 XT', 'https://example.com/6900xt.jpg', 'Navi 21', 16384, 300, 699.99),
('NVIDIA GeForce RTX 3090', 'https://example.com/rtx3090.jpg', 'GA102', 24576, 350, 899.99),
('AMD Radeon RX 6800 XT', 'https://example.com/6800xt.jpg', 'Navi 21', 16384, 300, 579.99),
('NVIDIA GeForce RTX 3080 Ti', 'https://example.com/rtx3080ti.jpg', 'GA102', 12288, 350, 799.99),
('AMD Radeon RX 6800', 'https://example.com/6800.jpg', 'Navi 21', 16384, 250, 549.99),
('NVIDIA GeForce RTX 3080', 'https://example.com/rtx3080.jpg', 'GA102', 10240, 320, 699.99),
('AMD Radeon RX 6750 XT', 'https://example.com/6750xt.jpg', 'Navi 22', 12288, 250, 449.99),
('NVIDIA GeForce RTX 3070 Ti', 'https://example.com/rtx3070ti.jpg', 'GA104', 8192, 290, 599.99),
('AMD Radeon RX 6700 XT', 'https://example.com/6700xt.jpg', 'Navi 22', 12288, 230, 479.99),
('NVIDIA GeForce RTX 3070', 'https://example.com/rtx3070.jpg', 'GA104', 8192, 220, 499.99),
('AMD Radeon RX 6650 XT', 'https://example.com/6650xt.jpg', 'Navi 23', 8192, 180, 399.99);

-- Insert Motherboards
INSERT INTO Hardware.Motherboards (name, image_url, socket, sata_storage_slots, m_2_storage_slots, ram_slots, ram_type, size, chipset_id, price) VALUES
('ASUS ROG Maximus Z790 Hero', 'https://example.com/maximus-z790.jpg', 'LGA1700', 6, 5, 4, 'DDR5', 'ATX', 5, 629.99),
('MSI MEG X670E ACE', 'https://example.com/x670e-ace.jpg', 'AM5', 6, 5, 4, 'DDR5', 'ATX', 7, 699.99),
('Gigabyte X570S AORUS Master', 'https://example.com/x570s-master.jpg', 'AM4', 6, 4, 4, 'DDR4', 'ATX', 2, 389.99),
('ASRock Z790 Taichi', 'https://example.com/z790-taichi.jpg', 'LGA1700', 8, 4, 4, 'DDR5', 'ATX', 5, 479.99),
('ASUS ROG STRIX B650-E', 'https://example.com/b650e-strix.jpg', 'AM5', 4, 3, 4, 'DDR5', 'ATX', 6, 289.99),
('MSI MPG B550 GAMING EDGE', 'https://example.com/b550-edge.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 3, 179.99),
('Gigabyte Z690 AORUS PRO', 'https://example.com/z690-pro.jpg', 'LGA1700', 6, 4, 4, 'DDR5', 'ATX', 1, 329.99),
('ASUS TUF GAMING H670-PRO', 'https://example.com/h670-pro.jpg', 'LGA1700', 4, 2, 4, 'DDR4', 'ATX', 13, 199.99),
('ASRock B550 Steel Legend', 'https://example.com/b550-legend.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 3, 169.99),
('MSI PRO Z690-A', 'https://example.com/z690-a.jpg', 'LGA1700', 6, 3, 4, 'DDR4', 'ATX', 1, 219.99),
('ASUS WRX80 SAGE SE WIFI', 'https://example.com/wrx80-sage.jpg', 'sWRX8', 8, 4, 8, 'DDR4', 'ATX', 15, 999.99),
('Gigabyte TRX40 AORUS PRO', 'https://example.com/trx40-pro.jpg', 'sTRX4', 8, 3, 8, 'DDR4', 'ATX', 11, 499.99),
('MSI MAG B660M MORTAR', 'https://example.com/b660m-mortar.jpg', 'LGA1700', 4, 2, 4, 'DDR4', 'Micro-ATX', 14, 159.99),
('ASUS PRIME X570-P', 'https://example.com/x570-p.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 2, 169.99),
('ASRock B650E PG Riptide', 'https://example.com/b650e-riptide.jpg', 'AM5', 4, 3, 4, 'DDR5', 'ATX', 6, 239.99),
('Gigabyte B660 GAMING X', 'https://example.com/b660-gaming.jpg', 'LGA1700', 4, 2, 4, 'DDR4', 'ATX', 14, 149.99),
('MSI PRO B550M-A', 'https://example.com/b550m-a.jpg', 'AM4', 4, 2, 4, 'DDR4', 'Micro-ATX', 3, 99.99),
('ASUS ROG STRIX B550-F', 'https://example.com/b550-f.jpg', 'AM4', 6, 2, 4, 'DDR4', 'ATX', 3, 184.99),
('ASRock X299 Taichi CLX', 'https://example.com/x299-taichi.jpg', 'LGA2066', 8, 3, 8, 'DDR4', 'ATX', 16, 399.99),
('Gigabyte Z590 VISION G', 'https://example.com/z590-vision.jpg', 'LGA1200', 6, 3, 4, 'DDR4', 'ATX', 8, 199.99);

-- Insert RAM
INSERT INTO Hardware.RAM (name, image_url, speed, size, type, price) VALUES
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
('Corsair Vengeance LPX', 'https://example.com/vengeance-lpx.jpg', 3200, 16384, 'DDR4', 69.99),
('Crucial Ballistix MAX', 'https://example.com/ballistix-max.jpg', 4000, 32768, 'DDR4', 169.99),
('TeamGroup Elite Plus', 'https://example.com/elite-plus.jpg', 3200, 16384, 'DDR4', 54.99),
('G.SKILL Trident Z Royal', 'https://example.com/trident-royal.jpg', 3600, 32768, 'DDR4', 189.99),
('Kingston FURY Beast', 'https://example.com/fury-beast.jpg', 5600, 32768, 'DDR5', 259.99),
('Corsair Dominator DDR5', 'https://example.com/dominator-ddr5.jpg', 5200, 32768, 'DDR5', 289.99),
('Crucial Gaming Memory', 'https://example.com/crucial-gaming.jpg', 3200, 16384, 'DDR4', 64.99),
('TeamGroup T-Create Classic', 'https://example.com/tcreate-classic.jpg', 3200, 32768, 'DDR4', 109.99),
('G.SKILL Aegis', 'https://example.com/aegis.jpg', 3200, 16384, 'DDR4', 59.99);

-- Insert Storage devices
INSERT INTO Hardware.Storage (name, image_url, format, protocol, size, price) VALUES
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
('Seagate Barracuda', 'https://example.com/barracuda.jpg', 'HDD', 'SATA', 2097152, 54.99),
('Crucial MX500', 'https://example.com/mx500.jpg', 'SSD', 'SATA', 1048576, 89.99),
('WD Blue SN570', 'https://example.com/sn570.jpg', 'SSD', 'NVMe', 1048576, 89.99),
('Toshiba X300', 'https://example.com/x300.jpg', 'HDD', 'SATA', 8388608, 209.99),
('ADATA XPG SX8200 Pro', 'https://example.com/sx8200-pro.jpg', 'SSD', 'NVMe', 1048576, 109.99),
('Seagate Exos', 'https://example.com/exos.jpg', 'HDD', 'SATA', 16777216, 389.99),
('Kingston KC3000', 'https://example.com/kc3000.jpg', 'SSD', 'NVMe', 2097152, 249.99),
('WD Purple', 'https://example.com/purple.jpg', 'HDD', 'SATA', 4194304, 119.99),
('Sabrent Rocket 4 Plus', 'https://example.com/rocket-4-plus.jpg', 'SSD', 'NVMe', 2097152, 259.99);

-- Insert Power Supply Units
INSERT INTO Hardware.PSU (name, image_url, wattage, size, price) VALUES
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
('Corsair SF750', 'https://example.com/sf750.jpg', 750, 'SFX', 184.99),
('EVGA SuperNOVA 650 GM', 'https://example.com/supernova-650gm.jpg', 650, 'SFX', 129.99),
('Silverstone SX700-PT', 'https://example.com/sx700-pt.jpg', 700, 'SFX', 159.99),
('FSP Dagger Pro', 'https://example.com/dagger-pro.jpg', 850, 'SFX', 179.99),
('Cooler Master V850 SFX', 'https://example.com/v850-sfx.jpg', 850, 'SFX', 189.99),
('ASUS ROG LOKI', 'https://example.com/rog-loki.jpg', 850, 'SFX-L', 219.99),
('Fractal Design Ion+ 2', 'https://example.com/ion-2.jpg', 860, 'ATX', 159.99),
('XPG Core Reactor', 'https://example.com/core-reactor.jpg', 750, 'ATX', 139.99),
('Cooler Master MWE Gold', 'https://example.com/mwe-gold.jpg', 750, 'ATX', 109.99);

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
('Corsair 4000D', 'https://example.com/4000d.jpg', 'ATX', 94.99),
('Lian Li O11 Air Mini', 'https://example.com/o11-air-mini.jpg', 'Micro-ATX', 129.99),
('Fractal Design Define 7', 'https://example.com/define-7.jpg', 'ATX', 189.99),
('be quiet! Silent Base 802', 'https://example.com/silent-base-802.jpg', 'ATX', 179.99),
('Phanteks Enthoo Pro 2', 'https://example.com/enthoo-pro-2.jpg', 'ATX', 199.99),
('NZXT H210', 'https://example.com/h210.jpg', 'Mini-ITX', 79.99),
('Cooler Master NR200P', 'https://example.com/nr200p.jpg', 'Mini-ITX', 99.99),
('Thermaltake Core P3', 'https://example.com/core-p3.jpg', 'ATX', 159.99),
('Fractal Design Torrent', 'https://example.com/torrent.jpg', 'ATX', 189.99);

-- Insert Coolers
INSERT INTO Hardware.Coolers (name, image_url, price) VALUES
('NZXT Kraken X73', 'https://example.com/kraken-x73.jpg', 199.99),
('be quiet! Dark Rock Pro 4', 'https://example.com/dark-rock-pro-4.jpg', 89.99),
('Noctua NH-D15', 'https://example.com/nh-d15.jpg', 99.99),
('Corsair iCUE H150i ELITE', 'https://example.com/h150i-elite.jpg', 189.99),
('Arctic Liquid Freezer II 360', 'https://example.com/liquid-freezer-360.jpg', 139.99),
('Deepcool AK620', 'https://example.com/ak620.jpg', 69.99),
('NZXT Kraken Z73', 'https://example.com/kraken-z73.jpg', 299.99),
('Lian Li Galahad 360', 'https://example.com/galahad-360.jpg', 159.99),
('Scythe Fuma 2', 'https://example.com/fuma-2.jpg', 59.99),
('EK AIO Elite 360', 'https://example.com/ek-aio-360.jpg', 189.99),
('Noctua NH-U12A', 'https://example.com/nh-u12a.jpg', 109.99),
('be quiet! Pure Rock 2', 'https://example.com/pure-rock-2.jpg', 44.99),
('MSI MEG CORELIQUID S360', 'https://example.com/coreliquid-s360.jpg', 279.99),
('Thermalright Peerless Assassin', 'https://example.com/peerless-assassin.jpg', 49.99),
('Corsair iCUE H100i', 'https://example.com/h100i.jpg', 159.99),
('Arctic Freezer 34 eSports', 'https://example.com/freezer-34.jpg', 49.99),
('Phanteks Glacier One 240', 'https://example.com/glacier-one.jpg', 149.99),
('Deepcool CASTLE 360EX', 'https://example.com/castle-360ex.jpg', 169.99),
('ID-COOLING SE-224-XT', 'https://example.com/se-224-xt.jpg', 34.99),
('Alphacool Eisbaer Pro', 'https://example.com/eisbaer-pro.jpg', 219.99);

-- Insert Cooler-Socket compatibility
INSERT INTO Hardware.CoolerSocketCompatibility (cooler_id, socket_id) VALUES
(1, 1), -- Kraken X73 with LGA1700
(1, 2), -- Kraken X73 with AM5
(1, 3), -- Kraken X73 with AM4
(2, 1), -- Dark Rock Pro 4 with LGA1700
(2, 3), -- Dark Rock Pro 4 with AM4
(2, 4), -- Dark Rock Pro 4 with LGA1200
(3, 1), -- NH-D15 with LGA1700
(3, 2), -- NH-D15 with AM5
(3, 3), -- NH-D15 with AM4
(4, 1), -- H150i ELITE with LGA1700
(4, 2), -- H150i ELITE with AM5
(4, 3), -- H150i ELITE with AM4
(5, 1), -- Arctic Liquid Freezer II with LGA1700
(5, 2), -- Arctic Liquid Freezer II with AM5
(6, 3), -- Deepcool AK620 with AM4
(6, 4), -- Deepcool AK620 with LGA1200
(7, 1), -- Kraken Z73 with LGA1700
(7, 2), -- Kraken Z73 with AM5
(8, 3), -- Galahad 360 with AM4
(8, 4), -- Galahad 360 with LGA1200
(9, 1), -- Scythe Fuma 2 with LGA1700
(9, 3), -- Scythe Fuma 2 with AM4
(10, 1), -- EK AIO Elite with LGA1700
(10, 2); -- EK AIO Elite with AM5

-- Insert Monitors
INSERT INTO Hardware.Monitor (name, image_url, resolution, refresh_rate, size, panel_type, price) VALUES
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
('Samsung Odyssey G5', 'https://example.com/odyssey-g5.jpg', '2560x1440', 165, 27.00, 'VA', 299.99),
('ASUS ProArt PA278CV', 'https://example.com/pa278cv.jpg', '2560x1440', 75, 27.00, 'IPS', 449.99),
('BenQ MOBIUZ EX2710S', 'https://example.com/ex2710s.jpg', '1920x1080', 165, 27.00, 'IPS', 299.99),
('Acer Predator X28', 'https://example.com/x28.jpg', '3840x2160', 152, 28.00, 'IPS', 649.99),
('ViewSonic Elite XG270QG', 'https://example.com/xg270qg.jpg', '2560x1440', 165, 27.00, 'IPS', 599.99),
('Gigabyte G27Q', 'https://example.com/g27q.jpg', '2560x1440', 144, 27.00, 'IPS', 329.99),
('HP OMEN 27i', 'https://example.com/omen-27i.jpg', '2560x1440', 165, 27.00, 'IPS', 499.99),
('LG 38GL950G-B', 'https://example.com/38gl950g.jpg', '3840x1600', 175, 38.00, 'IPS', 1499.99),
('MSI MPG ARTYMIS 273CQR', 'https://example.com/273cqr.jpg', '2560x1440', 165, 27.00, 'VA', 449.99);
