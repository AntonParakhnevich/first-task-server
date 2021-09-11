package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.domain.api2.Category;
import com.balinasoft.firsttask.dto.CategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by .
 */
public interface CategoryService {
     CategoryDTO save(String name);


//     void addImageInCategory(Long categoryId, int imageId);


     void deleteCategory(Long categoryId);


     List<CategoryDTO> getAllCategories();

}
