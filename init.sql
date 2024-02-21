-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_profile CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery
(
    ticketid VARCHAR(255) PRIMARY KEY,
    price VARCHAR(255) NOT NULL,
    amount VARCHAR(255) NOT NULL
);


CREATE TABLE user_profile
(
    userId SERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);

CREATE TABLE user_ticket
(
    id       SERIAL PRIMARY KEY,
    userId   VARCHAR(255) ,
    ticketid VARCHAR(255) NOT NULL,
    amount   VARCHAR(255) NOT NULL
);