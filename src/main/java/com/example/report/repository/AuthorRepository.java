package com.example.report.repository;

import com.example.report.model.Author;
import com.example.report.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT CASE WHEN (count(aut) > 0 ) THEN TRUE ELSE FALSE END " +
            "FROM Author aut WHERE " +
            "EXISTS(SELECT 1 FROM Author aut2 WHERE UPPER(aut2.name) = UPPER(:name))")
    boolean   existsByNameNotIgnoreCase(@Param("name") String name);


    @Query(value = "SELECT aut FROM Author aut WHERE UPPER(aut.name) LIKE CONCAT('%', UPPER(:search), '%') OR UPPER(aut.description) LIKE CONCAT('%', UPPER(:search), '%')")
    Page<Author> findAllByPage(@Param("search") String search, Pageable pageable);
}
