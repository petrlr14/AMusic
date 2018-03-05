-- Database: users

-- DROP DATABASE users;

CREATE DATABASE users
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Spain.1252'
       LC_CTYPE = 'Spanish_Spain.1252'
       CONNECTION LIMIT = -1;


create table roles(
	idRol int primary key,
	nombre varchar(10)
);
insert into roles values (0, 'ADMIN');
insert into roles values (1, 'STANDARD');

create table usuarios(
	pass varchar(35),
	username varchar(35),
	idRol int,
	primary key(pass, username),
	email varchar(30),
	directorio varchar(200);
);

insert into usuarios (pass, username, idrol, email) values('21232f297a57a5a743894a0e4a801fc3', 'admin', 0, 'admin@admin.com', 'C:\');

