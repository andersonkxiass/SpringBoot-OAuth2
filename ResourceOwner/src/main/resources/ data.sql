INSERT INTO user (login,name,password) VALUES ("anderson", "anderson", "123456");
INSERT INTO role (name) VALUES ("USER");
INSERT INTO role (name) VALUES ("ADMIN");
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
