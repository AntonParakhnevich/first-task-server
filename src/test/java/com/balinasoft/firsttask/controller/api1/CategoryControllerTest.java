package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;
import com.balinasoft.firsttask.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class, secure = false)
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void testGetCategories() throws Exception {
        //given
        CategoryDtoOut first = CategoryDtoOut.builder()
                .id(1)
                .name("first")
                .build();
        CategoryDtoOut second = CategoryDtoOut.builder()
                .id(2)
                .name("second")
                .build();

        when(categoryService.getCategories(1)).thenReturn(Arrays.asList(first, second));

        //when then
        mvc.perform(MockMvcRequestBuilders.get("/api/category")
                .param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("first"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("second"));
    }

    @Test
    public void testAddCategory() throws Exception {
        //given
        CategoryDtoOut category = CategoryDtoOut.builder().name("first").build();
        CategoryDtoIn categoryDtoIn = new CategoryDtoIn();
        categoryDtoIn.setName("first");
        when(categoryService.addCategory(categoryDtoIn)).thenReturn(category);

        //when then
        mvc.perform(MockMvcRequestBuilders.post("/api/category")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\":\"first\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("first"));
    }

    @Test
    public void testGetCategory() throws Exception {
        //given
        CategoryDtoOut categoryDtoOut = CategoryDtoOut.builder().id(1).name("first").build();

        when(categoryService.getCategory(1)).thenReturn(categoryDtoOut);

        //when then
        mvc.perform(MockMvcRequestBuilders.get("/api/category/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("first"));
    }


    @Test
    public void testDeleteCategory() throws Exception {
        //given
        doNothing().when(categoryService).deleteCategory(1);

        //when then
        mvc.perform(MockMvcRequestBuilders.delete("/api/category/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}