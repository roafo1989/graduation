
DROP TABLE IF EXISTS lunches;
DROP TABLE IF EXISTS voices;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    registered DATE DEFAULT now(),
    enabled    BOOLEAN   DEFAULT TRUE,
    roles      INTEGER   DEFAULT 1
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurants
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE voices
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    dateTime      TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE lunches
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER      NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price         INTEGER      NOT NULL,
        date          DATE     NOT NULL,
    CHECK ( price >= 0 ),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX lunches_unique_name_restaurant_idx ON lunches (name, restaurant_id);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);