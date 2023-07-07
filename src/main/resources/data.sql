insert into user (id, username, password) values (1, "demouser", "123");
insert into user (id, username, password) values (2, "user1", "123");

INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (1,'Panasonic',DATE_ADD(CURDATE(), INTERVAL -388 DAY),'PN0001','00001',  'Active', 1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (2,'Panasonic',DATE_ADD(CURDATE(), INTERVAL -1832 DAY),'PN0002','00002',  'Old', 1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (3,'Panasonic',DATE_ADD(CURDATE(), INTERVAL -66 DAY),'PN0003','00003',  'Active', 2);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (4,'Panasonic',DATE_ADD(CURDATE(), INTERVAL -562 DAY),'PN0004','00004',  'Unused', 1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (5, 'Panasonic',DATE_ADD(CURDATE(), INTERVAL -296 DAY),'PN0005','00005', 'Active', 1);

INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (6,'Samsung',DATE_ADD(CURDATE(), INTERVAL -440 DAY),'SM0001','00006',  'Unused', 1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (7, 'Samsung',DATE_ADD(CURDATE(), INTERVAL -1970 DAY),'SM0002','00007', 'Active', 2);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (8,'Samsung', DATE_ADD(CURDATE(), INTERVAL -36 DAY),'SM0003','00008', 'Old',1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (9, 'Samsung',DATE_ADD(CURDATE(), INTERVAL -323 DAY),'SM0004','00009', 'Active',2);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (10,'Samsung',DATE_ADD(CURDATE(), INTERVAL -258 DAY),'SM0005','00010',  'Active',2);

INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (11,'Xiaomi',DATE_ADD(CURDATE(), INTERVAL -101 DAY), 'XM0001','00011', 'Unused',1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (12, 'Xiaomi',DATE_ADD(CURDATE(), INTERVAL -405 DAY),'XM0002','00012', 'Active',1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (13,'Xiaomi',DATE_ADD(CURDATE(), INTERVAL -557 DAY),'XM0003','00013',  'Unused',2);

INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (14, 'LG',DATE_ADD(CURDATE(), INTERVAL -1095 DAY),'LG0001','00014', 'Active',1);
INSERT INTO appliance (id, brand, date_bought, model, serial_number, status, user_id) VALUES (15, 'LG',DATE_ADD(CURDATE(), INTERVAL -1564 DAY),'LG0002','00015', 'Active',2);

INSERT INTO appliance (id, serial_number, brand, model, date_bought, status) VALUES (16, 'Philips',DATE_ADD(CURDATE(), INTERVAL -890 DAY),'PH0001','00016', 'Active');
INSERT INTO appliance (id, serial_number, brand, model, date_bought, status) VALUES (17, 'Philips',DATE_ADD(CURDATE(), INTERVAL -40 DAY),'PH0002','00017', 'Sold');
INSERT INTO appliance (id, serial_number, brand, model, date_bought, status) VALUES (18, 'Philips',DATE_ADD(CURDATE(), INTERVAL -345 DAY),'PH0003','00018', 'Active');