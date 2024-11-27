CREATE TABLE book_author (
                            book_id BIGINT NOT NULL,
                            author_id BIGINT NOT NULL,
                            PRIMARY KEY (book_id, author_id),
                            FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
                            FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);