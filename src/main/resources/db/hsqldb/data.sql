INSERT INTO roles(id,nombre) VALUES(1,'ROLE_ADMIN');
INSERT INTO roles(id,nombre) VALUES(2,'ROLE_JEFE');
INSERT INTO roles(id,nombre) VALUES(3,'ROLE_USER');

INSERT INTO users(nombre,username,password,dni,direccion,telefono,email) VALUES('Pedro Pablo','pedcarmor','password','53772897R','Severo Ochoa','603635550','email@email.com');

INSERT INTO users_roles(username,rol_id) VALUES('pedcarmor',3);