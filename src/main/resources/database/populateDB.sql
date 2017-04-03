USE productmanagementdb;

-- products
INSERT INTO products VALUES
  (1, 'i6', 'Apple', 'Very popular phone', 1000),
  (2, 'Galaxy', 'Samsung', 'Phone with big screen', 900),
  (3, 'X-power', 'LG', 'This phone has battery with big capacity', 500),
  (4, 'Xperia XA', 'Sony', 'Phone with dual sim support', 650),
  (5, 'Sony KD-65XD7505', 'Sony', 'Smart-TV', 9000),
  (6, 'Philips 24PHS4031', 'Philips', 'TV with small screen', 800),
  (7, 'LG 86UH955V', 'LG', 'Wary big TV', 900),
  (8, 'Kindle', 'Amazon', 'Very popular bookreader', 200),
  (9, 'PocketBook 630', 'PocketBook', 'A new bookreader', 150),
  (10, 'Canon PowerShot', 'Canon', 'A good camera for amateurs', 95),
  (11, 'Nikon D610', 'Nikon', 'A good professional camera', 1500),
  (12, 'Lumix DMC-GX8', 'Panasonic', 'Water resist camera', 370),
  (13, 'Philips AZ1837/12', 'Philips', 'Stereo system', 800),
  (14, 'SIEMENS WS 12T440 BY', 'SIEMENS', 'washing machine', 550),
  (15, 'SATURN ST-EK8440 ', 'SATURN', 'My favorite kettle', 900);

-- users
INSERT INTO users VALUES
  (1, 'Joe', '111', 'ADMIN', FALSE),
  (2, 'Bill', '222', 'ADMIN', FALSE),
  (3, 'Nata', '333', 'ADMIN', FALSE),
  (4, 'Drew', '444', 'USER', FALSE),
  (5, 'Ann', '555', 'USER', FALSE),
  (6, 'Bob', '666', 'USER', FALSE),
  (7, 'Sam', '777', 'USER', FALSE),
  (8, 'Kate', '888', 'USER', TRUE),
  (9, 'Ron', '999', 'USER', TRUE);
