package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.dto.ImageDtoOut;
import com.balinasoft.firsttask.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by .
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ImageController.class, secure = false)
public class ImageControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ImageService imageService;

    @Test
    public void getImages() throws Exception {
        List<ImageDtoOut> images = new ArrayList<>();

        ImageDtoOut imageDtoOut1 = new ImageDtoOut();
        ImageDtoOut imageDtoOut2 = new ImageDtoOut();
        images.add(imageDtoOut1);
        images.add(imageDtoOut2);

        when(imageService.getImages(1)).thenReturn(images);

        mvc.perform(MockMvcRequestBuilders.get("/api/image")
                .param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testGetImagesByCategories() throws Exception {
        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        category1.setName("first");

        Category category2 = new Category();
        category2.setName("second");

        Category category3 = new Category();
        category3.setName("third");

        categories.add(category1);
        categories.add(category2);

        List<ImageDtoOut> images = new ArrayList<>();

        ImageDtoOut imageDtoOut1 = new ImageDtoOut();
        ImageDtoOut imageDtoOut2 = new ImageDtoOut();

        imageDtoOut1.setId(1);
        imageDtoOut2.setId(2);

        images.add(imageDtoOut1);
        images.add(imageDtoOut2);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(categories);

        when(imageService.getImagesByCategories(categories, 1)).thenReturn(images);

        mvc.perform(MockMvcRequestBuilders.get("/api/image/images")
                .param("page", "1")
                .contentType(APPLICATION_JSON_UTF8)
                .content(s))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
