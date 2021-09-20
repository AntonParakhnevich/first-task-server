package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.User;
import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;
import com.balinasoft.firsttask.repository.CategoryRepository;
import com.balinasoft.firsttask.repository.UserRepository;
import com.balinasoft.firsttask.system.error.ApiAssert;
import com.balinasoft.firsttask.util.SecurityContextHolderWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final SecurityContextHolderWrapper securityContextHolderWrapper;

    @Override
    public CategoryDtoOut addCategory(CategoryDtoIn categoryDtoIn) {
        User user = userRepository.findOne(securityContextHolderWrapper.currentUserId());
        Category category = new Category();
        category.setName(categoryDtoIn.getName());
        category.setUser(user);
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

    @Override
    public CategoryDtoOut getCategory(int id) {
        Category category = categoryRepository.findOne(id);
        ApiAssert.notFound(category == null);
        ApiAssert.forbidden(category.getUser().getId() != securityContextHolderWrapper.currentUserId());
        return toDto(category);
    }

    private CategoryDtoOut toDto(Category category) {
        return CategoryDtoOut
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
