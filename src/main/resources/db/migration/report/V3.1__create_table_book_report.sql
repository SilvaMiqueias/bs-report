CREATE TABLE book (
                       id SERIAL  PRIMARY KEY,
                       price numeric,
                       title VARCHAR(255) UNIQUE NOT NULL,
                       description TEXT NOT NULL,
                       qtd integer,
                       page integer,
                       language VARCHAR(150),
                       code VARCHAR(200),
                       image BYTEA,
                       release_date TIMESTAMP,
                       active boolean,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);