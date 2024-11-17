CREATE TABLE users (
                       id SERIAL  PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       name varchar(255) NOT NULL

);