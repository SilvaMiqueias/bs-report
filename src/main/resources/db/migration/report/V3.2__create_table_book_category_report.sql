CREATE TABLE book_category (
                            book_id BIGINT NOT NULL,
                            category_id BIGINT NOT NULL,
                            PRIMARY KEY (book_id, category_id),
                            FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
                            FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);