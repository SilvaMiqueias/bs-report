package com.example.report.repository;

import com.example.report.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository  extends JpaRepository<Book, Long> {


    @Query(value = "SELECT book FROM Book book " +
            "LEFT JOIN book.categories cats " +
            "LEFT JOIN book.authors aut " +
            "WHERE UPPER(book.title) LIKE CONCAT('%', UPPER(:search), '%') OR UPPER(book.description) LIKE CONCAT('%', UPPER(:search), '%') OR UPPER(cats.name) LIKE CONCAT('%', UPPER(:search), '%') " +
            "OR UPPER(aut.name) LIKE CONCAT('%', UPPER(:search), '%') " )
    Page<Book> findAllByPage(@Param("search") String search, Pageable pageable);
}
