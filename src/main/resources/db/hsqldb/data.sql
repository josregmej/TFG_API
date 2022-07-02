INSERT INTO USER(nombre,username,password,dni,direccion,telefono,email) VALUES ('Pedro Pablo', 'pedcarmor','password','53773897R','Severo Ochoa','603635550','email@gmail.com');
INSERT INTO USER(nombre,username,password,dni,direccion,telefono,email) VALUES ('Fran', 'josregmej','password','20061859V','Severo Ochoa','603635550','email@gmail.com');
INSERT INTO ROL(nombre) VALUES('ROLE_ADMIN');
INSERT INTO ROL(nombre) VALUES('ROLE_JEFE');
INSERT INTO ROL(nombre) VALUES('ROLE_USER');

INSERT INTO PRODUCTO(nombre,precio, cantidadalmacen,stockseguridad,cerca_stock,user_id) VALUES ('Café', 17.50, 50, 10, false, 1);
INSERT INTO PRODUCTO(nombre,precio, cantidadalmacen,stockseguridad,cerca_stock,user_id) VALUES ('Cafelito', 21.50, 75, 10, false, 1);
INSERT INTO PRODUCTO(nombre,precio, cantidadalmacen,stockseguridad,cerca_stock,user_id) VALUES ('Miel', 10.00, 50, 10, false, 2);