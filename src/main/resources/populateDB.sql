-- noinspection SqlWithoutWhereForFile

-- noinspection SqlWithoutWhere
DELETE FROM voices;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM user_roles;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password, roles)
VALUES ('Вася Пупки', 'coolpupkin@mail.ru', '{noop}password', 1),
       ('Carl Cimmer', 'parasit@ya.ru', '{noop}qwerty', 1),
       ('S. King', 'christina_miss_you@gmail.com', '{noop}admin', 3);

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
VALUES (100000, 100003, '2020-01-09 10:00:00'),
       (100001, 100005, '2020-01-09 10:00:00'),
       (100001, 100003, '2020-01-07 10:00:00'),
       (100002, 100004, '2020-01-09 10:00:00'),
       (100000, 100005, '2020-01-07 10:00:00'),
       (100000, 100004, '2020-01-03 10:00:00');

INSERT INTO lunches (date, name, restaurant_id,price)
VALUES ('2020-01-09','Салат с тунцом', 100003,100),
       ('2020-01-09','Лапша со свининой', 100003,200),
       ('2020-01-09','Матча латте', 100003,300),
       ('2020-01-09','Салат цезарь', 100003,400),
       ('2020-01-09','Паста с брокколи', 100003,500),
       ('2020-01-09','Раф', 100003,100),
       ('2020-01-09','Тёмный бургер', 100004,100),
       ('2020-01-09','Капучино', 100004,1),
       ('2020-01-09','Твистер острый', 100004,5),
       ('2020-01-09','Чай в ассортименте', 100004,500),
       ('2020-01-09','Капустный салат', 100005,100),
       ('2020-01-09','Борщ', 100005,200),
       ('2020-01-09','Квас', 100005,100),
       ('2020-01-09','Винегрет', 100005,1000),
       ('2020-01-09','Домашняя котлета с картофельным пюре', 100005,15666),
       ('2020-01-07','Квас Лидский', 100005,100),
       ('2020-01-08','Салат Лапи', 100005,1000),
       ('2020-01-09','Американо со сливками', 100005,10000);
