CREATE TABLE category (
                       id SERIAL  PRIMARY KEY,
                       name VARCHAR(255) UNIQUE NOT NULL,
                       description VARCHAR(900) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);