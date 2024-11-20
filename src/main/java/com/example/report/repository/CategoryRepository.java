package com.example.report.repository;

import com.example.report.dto.CategoryDTO;
import com.example.report.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query(value = "SELECT CASE WHEN (count(cat) > 0 ) THEN TRUE ELSE FALSE END " +
          "FROM Category cat WHERE " +
          "EXISTS(SELECT 1 FROM Category cat2 WHERE UPPER(cat2.name) = UPPER(:name))")
  boolean   existsByNameNotIgnoreCase(@Param("name") String name);


  @Query(value = "SELECT cat FROM Category cat WHERE UPPER(cat.name) LIKE CONCAT('%', UPPER(:search), '%') OR UPPER(cat.description) LIKE CONCAT('%', UPPER(:search), '%')")
  Page<Category> findAllByPage(@Param("search") String search, Pageable pageable);
}
