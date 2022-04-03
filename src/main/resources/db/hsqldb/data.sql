INSERT INTO roles(id,nombre) VALUES(1,'ROLE_ADMIN');
INSERT INTO roles(id,nombre) VALUES(2,'ROLE_JEFE');
INSERT INTO roles(id,nombre) VALUES(3,'ROLE_USER');

INSERT INTO users(nombre,username,password,dni,direccion,telefono,email) VALUES('Pedro Pablo','pedcarmor','Password1234','53772897R','Severo Ochoa','603635550','email@email.com');
INSERT INTO users(nombre,username,password,dni,direccion,telefono,email) VALUES('Pedro Pablo','pedrito','Password1234','53772897Y','Severo Ochoa','603635550','email@email.com');
INSERT INTO users(nombre,username,password,dni,direccion,telefono,email) VALUES('Pedro Pablo','padreando','Password1234','53772897E','Severo Ochoa','603635550','email@email.com');

INSERT INTO users_roles(username,rol_id) VALUES('pedcarmor',3);
INSERT INTO users_roles(username,rol_id) VALUES('pedrito',1);
INSERT INTO users_roles(username,rol_id) VALUES('padreando',2);

INSERT INTO clientes(id,nombre,dni,direccion,telefono,email,tieneiva) VALUES(1, 'Pedro Pablo','53772897R','Severo Ochoa','603635550','email@email.com',false);
INSERT INTO clientes(id,nombre,dni,direccion,telefono,email,tieneiva) VALUES(2, 'Pedro Pablo','53772897G','Severo Ochoa','603635550','email@email.com',true);
INSERT INTO clientes(id,nombre,dni,direccion,telefono,email,tieneiva) VALUES(3, 'Pedro Pablo','53772897T','Severo Ochoa','603635550','email@email.com',false);

INSERT INTO empleados(id,nombre,dni,direccion,telefono,email,horas_trabajadas,salario,faltas,horario) VALUES(1, 'Pedro Pablo','53772897D','Severo Ochoa','603635550','email@email.com',8.0,1050.90,"10 faltas","Lunes a Viernes de 9:00 a 18:00");
INSERT INTO empleados(id,nombre,dni,direccion,telefono,email,horas_trabajadas,salario,faltas,horario) VALUES(2, 'Pedro Pablo','53772897B','Severo Ochoa','603635550','email@email.com',6.0,1130.60,"25 faltas","Lunes a Viernes de 9:00 a 16:00");
INSERT INTO empleados(id,nombre,dni,direccion,telefono,email,horas_trabajadas,salario,faltas,horario) VALUES(3, 'Pedro Pablo','53772897V','Severo Ochoa','603635550','email@email.com',5.0,1560.10,"76 faltas","Lunes a Viernes de 9:00 a 15:00");


