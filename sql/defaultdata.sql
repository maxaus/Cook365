INSERT INTO `user`(firstName, lastName, email, password, role) VALUES ('Максим','Баев','m.k.baev@gmail.com','a6865c3cb0f021994cda70160db89f76','ROLE_ADMIN');

INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('гр', 1, null);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('кг', 1000, 1);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('мл', 1, null);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('л', 1000, 3);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('ст. л', 15, 3);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('дес. л', 10, 3);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('ч. л', 5, 3);
INSERT INTO `measure_unit`(`name`,`ratio`,`parentId`) values ('шт', 0, null);