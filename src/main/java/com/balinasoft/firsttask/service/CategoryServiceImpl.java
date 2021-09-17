package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.domain.api2.Category;
import com.balinasoft.firsttask.dto.CategoryDTO;
import com.balinasoft.firsttask.repository.CategoryRepository;
import com.balinasoft.firsttask.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by .
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO save(String name){
        Category category = Category.builder().name(name).build();
        category = categoryRepository.save(category);
        return toDto(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        ApiAssert.notFound(category == null);
        ApiAssert.forbidden(category.getUser().getId() != securityContextHolderWrapper.currentUserId());
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDtoOut> getCategories(int page) {
        return categoryRepository.findByUser(securityContextHolderWrapper.currentUserId(), new PageRequest(page, 20)).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CategoryDTO toDto(Category category) {
        return CategoryDTO
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
