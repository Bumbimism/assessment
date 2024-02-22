-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_profile CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery
(
    ticket_id VARCHAR(255) PRIMARY KEY,
    price     VARCHAR(255) NOT NULL,
    amount    VARCHAR(255) NOT NULL
);


CREATE TABLE user_profile
(
    user_id  SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);

CREATE TABLE user_ticket
(
    id        SERIAL PRIMARY KEY,
    user_id   VARCHAR(255),
    ticket_id VARCHAR(255) NOT NULL,
    amount    VARCHAR(255) NOT NULL
);