package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;

import java.util.List;

/**
 * Created by .
 */
public interface CategoryService {
    CategoryDtoOut addCategory(CategoryDtoIn categoryDtoIn);

    void deleteCategory(int categoryId);

    List<CategoryDtoOut> getCategories(int page);

    CategoryDtoOut getCategory(int id);
}
