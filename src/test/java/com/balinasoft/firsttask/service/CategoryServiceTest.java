package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.User;
import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;
import com.balinasoft.firsttask.repository.CategoryRepository;
import com.balinasoft.firsttask.repository.UserRepository;
import com.balinasoft.firsttask.util.SecurityContextHolderWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by .
 */
@RunWith(MockitoJUnitRunner.class)

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityContextHolderWrapper securityContextHolderWrapper;

    @InjectMocks
    public CategoryServiceImpl categoryService;


    @Test
    public void testAddCategory() {
        CategoryDtoOut first = CategoryDtoOut.builder().id(1).name("first").build();

        Category category = new Category();
        category.setId(1);
        category.setName("first");

        CategoryDtoIn categoryDtoIn = CategoryDtoIn.builder()
                .name("first")
                .build();

        when(userRepository.findOne(1)).thenReturn(new User());
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDtoOut categoryDtoOut = categoryService.addCategory(categoryDtoIn);
        Assert.assertEquals(first, categoryDtoOut);
    }

    @Test
    public void testGetCategory() {
        User user = new User();
        user.setId(1);

        CategoryDtoOut first = CategoryDtoOut.builder().name("first").id(1).build();

        Category category = new Category();
        category.setName("first");
        category.setId(1);
        category.setUser(user);

        when(categoryRepository.findOne(1)).thenReturn(category);
        when(securityContextHolderWrapper.currentUserId()).thenReturn(user.getId());

        CategoryDtoOut getCategory = categoryService.getCategory(1);

        assertEquals(getCategory, first);
    }

    @Test
    public void testGetCategories() {
        List<CategoryDtoOut> categoryDtoOuts = new ArrayList<>();

        CategoryDtoOut first = CategoryDtoOut.builder()
                .id(1)
                .name("first")
                .build();
        CategoryDtoOut second = CategoryDtoOut.builder()
                .id(2)
                .name("second")
                .build();
        categoryDtoOuts.add(first);
        categoryDtoOuts.add(second);

        List<Category> categories = new ArrayList<>();

        Category category1 = new Category();
        category1.setId(1);
        category1.setName("first");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("second");

        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findByUser(1, new PageRequest(1, 20))).thenReturn(categories);
        when(securityContextHolderWrapper.currentUserId()).thenReturn(1);

        List<CategoryDtoOut> getCategories = categoryService.getCategories(1);
        assertEquals(getCategories, categoryDtoOuts);

    }


    @Test
    public void testDeleteCategory() {
        User user = new User();
        user.setId(1);

        Category category = new Category();
        category.setName("first");
        category.setUser(user);
        category.setId(1);

        when(categoryRepository.findOne(1)).thenReturn(category);
        when(securityContextHolderWrapper.currentUserId()).thenReturn(1);
        doNothing().when(categoryRepository).delete(1);

        categoryService.deleteCategory(1);
        verify(categoryRepository, times(1)).delete(eq(category));
    }

}
