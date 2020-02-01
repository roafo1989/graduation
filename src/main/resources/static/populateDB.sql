-- noinspection SqlWithoutWhereForFile

-- noinspection SqlWithoutWhere
DELETE FROM voices;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM user_roles;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password,activation)
VALUES ('User', 'user@mail.ru', '{noop}password','987654'),
       ('Admin', 'admin@mail.ru', '{noop}password','123456'),
       ('User2', 'user2@mail.ru', '{noop}password','147852');

INSERT INTO restaurants (name)
VALUES ('Kharbin'),
       ('Ogon da myaso'),
       ('Panda');


INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001),
('ROLE_USER', 100002),
('ROLE_USER', 100001);

INSERT INTO lunches (name, date, restaurant_id, price)
VALUES ('Lunch1-Rest1', '2020-01-10', 100003, 100),
       ('Lunch2-Rest1', '2020-01-10', 100003, 200),
       ('Lunch3-Rest1', '2020-01-13', 100003, 300),
       ('Lunch4-Rest2', CURRENT_DATE, 100004, 30),
       ('Lunch5-Rest2', CURRENT_DATE, 100004, 200),
       ('Lunch6-Rest3', CURRENT_DATE, 100005, 120),
       ('Lunch7-Rest3', CURRENT_DATE, 100005, 10);

INSERT INTO voices (user_id, restaurant_id, dateTime)
VALUES (100000, 100004, current_date),
       (100001, 100004, '2020-01-14 10:00:00'),
       (100000, 100003, '2020-01-13 10:00:00'),
       (100001, 100003, '2020-01-12 10:00:00'),
       (100002, 100003, CURRENT_DATE);


