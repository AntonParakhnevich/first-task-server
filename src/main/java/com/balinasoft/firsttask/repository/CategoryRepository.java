package com.balinasoft.firsttask.repository;

import com.balinasoft.firsttask.domain.api2.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by .
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
