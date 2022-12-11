drop database IF EXISTS stihDB;
create DATABASE stihDB;
USE stihDB;
SET NAMES 'utf8';


create TABLE permissions (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
name varchar (32) NOT NULL,
role_id bigint REFERENCES roles(id)
);


create TABLE roles (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
name varchar (32) NOT NULL
);


create TABLE users (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
username varchar (64) NOT NULL,
password varchar (64) NOT NULL,
email varchar (64) NOT NULL,
date_of_birth date NOT NULL,
gender_type varchar (64) NOT NULL,
role_id bigint REFERENCES roles(id)
);

create TABLE authors (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name varchar (64) NOT NULL,
last_name varchar (64) NOT NULL,
date_of_birth date NOT NULL,
date_of_death date NOT NULL,
gender_type varchar (64) NOT NULL,
info varchar (255) NOT NULL
);

create TABLE poems (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
text varchar (255) NOT NULL,
publish_date date NOT NULL,
author_id bigint,
CONSTRAINT author_fk
        FOREIGN KEY (author_id)
        REFERENCES authors(id) ON DELETE CASCADE
);


create TABLE likes (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
user_id bigint REFERENCES users(id),
sending_time TIMESTAMP NOT NULL,
author_id bigint,
poem_id bigint,
CONSTRAINT author_fk_likes
        FOREIGN KEY (author_id)
        REFERENCES authors(id) ON DELETE CASCADE,
CONSTRAINT poem_fk_likes
        FOREIGN KEY (poem_id)
        REFERENCES poems(id) ON DELETE CASCADE
);

create TABLE comments (
id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
text varchar (255) NOT NULL,
user_id bigint REFERENCES users(id),
sending_time TIMESTAMP NOT NULL,
poem_id bigint,
CONSTRAINT poem_fk_comments
        FOREIGN KEY (poem_id)
        REFERENCES poems(id) ON DELETE CASCADE
);

insert into permissions (id, name, role_id) values
(1, 'WRITING', 1),
(2, 'WRITING', 2),
(3, 'ADMIN_ACTIONS', 1);

insert into roles (id, name) values
(1, 'ADMIN'),
(2, 'USER');

insert into users (id, username, password, email, date_of_birth, gender_type, role_id) values
(1, 'artibel', '$2a$10$dCKE0qv1SW3dKBTXkauFburkrCGOznBAhdXaV3Km9yre7qysphk1u', 'artibel@mail.ru', STR_TO_DATE('05.05.2002',"%d.%m.%Y"), 'MALE',1), -- Adminus0
(2, 'guestone', '$2a$10$Uimw7bv5iTa.5miRSn4M4uGosxfyh1d89aVEHIkSNsPFkz6NmgOLq', 'guest@mail.ru', STR_TO_DATE('23.02.2006',"%d.%m.%Y"), 'MALE',2); -- Roflan54

insert into authors (id, first_name, last_name, date_of_birth, date_of_death, gender_type, info) values
(1, 'Author', 'Anon', STR_TO_DATE('11.05.1786',"%d.%m.%Y"), STR_TO_DATE('08.01.1808',"%d.%m.%Y"), 'MALE', 'Info based'),
(2, 'Amogus', 'Two', STR_TO_DATE('11.05.1865',"%d.%m.%Y"), STR_TO_DATE('08.01.1965',"%d.%m.%Y"), 'FEMALE', 'Omega lul');

insert into poems(id, text, publish_date, author_id) values
(1, 'poema1', STR_TO_DATE('11.05.1805',"%d.%m.%Y"),1),
(2, 'poema2', STR_TO_DATE('02.02.1905', "%d.%m.%Y"),2),
(3, 'poema3', STR_TO_DATE('08.06.1926', "%d.%m.%Y"), 2);


insert into likes (id, user_id, sending_time, author_id, poem_id) values
(1, 2, "2022-12-08 18:35:00", null, 1),
(2, 2, "2022-12-08 18:36:00", null, 2),
(3, 2, "2022-12-08 18:37:00", null, 3),
(4, 2, "2022-12-08 18:37:00", 1, null),
(5, 2, "2022-12-08 18:37:00", 2, null);

insert into comments (id, text, user_id, sending_time, poem_id) values
(1, 'comment text one', 2, '2022-12-08 18:42:00', 1),
(2, 'comment text two', 2, '2022-12-08 18:43:00', 2),
(3, 'comment text three', 2, '2022-12-08 18:44:00', 3);
