package com.balinasoft.firsttask.repository;

import com.balinasoft.firsttask.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("from Category c where c.user.id = :userId order by c.name")
    List<Category> findByUser(@Param("userId") int userId, Pageable pageable);
}
