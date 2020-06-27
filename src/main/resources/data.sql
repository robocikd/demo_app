INSERT INTO user_roles (id, role)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO user_roles (id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO users (id, enable, login, email, password)
VALUES (998, true, 'admin', 'damian@wp.pl', '$2a$10$iNWL3dCpWChiBQGU6HAUxOs6iq6ev2nOk6TqlG1T24rX7DFdDvpi.');

INSERT INTO users (id, enable, login, email, password)
VALUES (999, true, 'user', 'damian@damian.pl', '$2a$10$BH/oCbF0HZoJwxwSwo0BK..TogWU2fI7mEK5DBaAJnNK6WhfPwhRi');

INSERT INTO users_roles (users_id, roles_id)
VALUES (998, 1);

INSERT INTO users_roles (users_id, roles_id)
VALUES (999, 2);

INSERT INTO admins (employed_since, id)
VALUES ('2019.10.10', 999);

INSERT INTO customers(id, confirmation_token, customer_first_name, customer_surname, customer_address)
VALUES (999,'sdf', 'Damian', 'Damian', 'Zamosc');

INSERT INTO cottage(id, cottage_city, cottage_name, cottage_no_of_rooms, cottage_region, cottage_street_and_number)
VALUES (991, 'Jastarnia', 'Fala', 4, 'morze', 'Morska 22/1');
INSERT INTO cottage(id, cottage_city, cottage_name, cottage_no_of_rooms, cottage_region, cottage_street_and_number)
VALUES (992, 'Kołobrzeg', 'Albatros', 4, 'morze', 'Zatokowa 2/1');
INSERT INTO cottage(id, cottage_city, cottage_name, cottage_no_of_rooms, cottage_region, cottage_street_and_number)
VALUES (993, 'Zakopane', 'Szczyt', 3, 'gory', 'Kręta 1/1');
INSERT INTO cottage(id, cottage_city, cottage_name, cottage_no_of_rooms, cottage_region, cottage_street_and_number)
VALUES (994, 'Karpacz', 'Pod lasem', 3, 'gory', 'Morska 22/1');

INSERT INTO cottage_files (id, cottage_id, file_extension, file_name, modified_file_name) VALUES ('991','991','jpg', '1.jpg', '1.jpg');
INSERT INTO cottage_files (id, cottage_id, file_extension, file_name, modified_file_name) VALUES ('992','992','jpg', '2.jpg', '2.jpg');
INSERT INTO cottage_files (id, cottage_id, file_extension, file_name, modified_file_name) VALUES ('993','993','jpg', '3.jpg', '3.jpg');
INSERT INTO cottage_files (id, cottage_id, file_extension, file_name, modified_file_name) VALUES ('994','994','jpg', '4.jpg', '4.jpg');
