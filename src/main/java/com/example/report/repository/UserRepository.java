package com.example.report.repository;

import com.example.report.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query(value = "SELECT user FROM User user " +
          "INNER JOIN user.roles role " +
          "WHERE role.name = 'ROLE_CUSTOMER' AND " +
          "(UPPER(user.name) LIKE CONCAT('%', UPPER(:search), '%') OR UPPER(user.username) LIKE CONCAT('%', UPPER(:search), '%'))")
  Page<User> findAllCustomerByPage(@Param("search") String search, Pageable pageable);
}
