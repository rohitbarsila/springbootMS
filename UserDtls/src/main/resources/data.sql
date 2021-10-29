DROP table IF EXISTS user_roles;
DROP TABLE IF EXISTS role ;
create sequence IF NOT EXISTS role_seq ;
create table IF NOT EXISTS role (id bigint not null default nextval ('role_seq'), description varchar(255), name varchar(255), primary key (id)) ;
create table IF NOT EXISTS user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) ;
INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');
