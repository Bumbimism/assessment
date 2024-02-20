-- Drop tables if they exist
DROP TABLE IF EXISTS wallet CASCADE;
DROP TABLE IF EXISTS profile CASCADE;

CREATE TABLE user_profile
(
    userId   SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(255)        NOT NULL
);


CREATE TABLE lottery
(
    ticketId SERIAL PRIMARY KEY,
    price    VARCHAR(255) NOT NULL,
    amount   VARCHAR(255) NOT NULL
);

CREATE TABLE user_ticket
(
    id       SERIAL PRIMARY KEY,
    userId   VARCHAR(255) REFERENCES profile (userId) ON DELETE CASCADE,
    ticketId VARCHAR(255) NOT NULL,
    amount   INT(255) NOT NULL
    --userId VARCHAR(255) REFERENCES profile(userId) ON DELETE CASCADE
);

-- Initial data
INSERT INTO user_profile(userId, username, password, role)
VALUES ('888', 'admin@domain.com', 'admin', 'password', 'admin');
INSERT INTO lottery(ticketId, price, amount)
VALUES ('933975', '80', '1');
INSERT INTO user_ticket(Id, ticketId, amount)
VALUES ('1', '888','933975', '1');
