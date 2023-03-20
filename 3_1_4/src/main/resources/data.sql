-- ROLE_ADMIN.id = 1
-- ROLE_USER.id = 2
INSERT INTO role (authority)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

-- admin.password == "password"
-- user.password == "password"
INSERT INTO user (first_name, last_name, age, email, password)
VALUES ('admin', 'admin', 35, 'admin@mail.ru', '$2a$12$wkPhXx.Fi1SKSP9yQyMrFOXmq9OM7.iceM92B7O0SB.nZJLysc5i6'),
       ('user', 'user', 30, 'user@mail.ru', '$2a$12$wkPhXx.Fi1SKSP9yQyMrFOXmq9OM7.iceM92B7O0SB.nZJLysc5i6');

-- admin.role == ROLE_ADMIN, ROLE_USER
-- user.role == ROLE_USER
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);

