INSERT INTO roles(id,nombre) VALUES(1,'ROLE_ADMIN');
INSERT INTO roles(id,nombre) VALUES(2,'ROLE_JEFE');
INSERT INTO roles(id,nombre) VALUES(3,'ROLE_USER');

INSERT INTO users(id,nombre,username,password,dni,direccion,telefono,email) VALUES(1,'Pedro Pablo','pedcarmor','password','53772897R','Severo Ochoa','603635550','email@email.com');

INSERT INTO users_roles(user_id,rol_id) VALUES(1,3);