-- noinspection SqlWithoutWhereForFile

-- noinspection SqlWithoutWhere
DELETE FROM voices;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM user_roles;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.ru', '{noop}password'),
       ('Admin', 'admin@mail.ru', '{noop}password'),
       ('User2', 'user2@mail.ru', '{noop}password');

INSERT INTO restaurants (name)
VALUES ('Kharbin'),
       ('Ogon da myaso'),
       ('Panda');


INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001),
('ROLE_USER', 100002),
('ROLE_USER', 100001);

INSERT INTO voices (user_id, restaurant_id, dateTime)
VALUES (100000, 100003, current_timestamp),
       (100001, 100005, current_timestamp),
       (100001, 100003, current_timestamp),
       (100002, 100004, current_timestamp),
       (100000, 100005, current_timestamp),
       (100000, 100004, current_timestamp);

INSERT INTO lunches (name, date, restaurant_id, price)
VALUES ('Dish1-Rest1', '2019-11-23', 100003, 100),
       ('Dish2-Rest1', '2019-11-23', 100003, 200),
       ('Dish3-Rest1', '2019-11-23', 100003, 300),
       ('Dish4-Rest2', CURRENT_DATE, 100004, 30),
       ('Dish5-Rest2', CURRENT_DATE, 100004, 200),
       ('Dish6-Rest3', CURRENT_DATE, 100005, 120),
       ('Dish7-Rest3', CURRENT_DATE, 100005, 10);
