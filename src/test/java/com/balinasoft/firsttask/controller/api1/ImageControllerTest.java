package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.dto.ImageDtoOut;
import com.balinasoft.firsttask.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ImageController.class, secure = false)
public class ImageControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ImageService imageService;

    @Test
    public void testGetImages() throws Exception {
        ImageDtoOut imageDtoOut1 = new ImageDtoOut();
        ImageDtoOut imageDtoOut2 = new ImageDtoOut();
        imageDtoOut1.setId(1);
        imageDtoOut2.setId(2);

        when(imageService.getImages(1)).thenReturn(Arrays.asList(imageDtoOut1, imageDtoOut2));

        mvc.perform(MockMvcRequestBuilders.get("/api/image")
                .param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2));
    }

    @Test
    public void testGetImagesByCategories() throws Exception {
        ImageDtoOut imageDtoOut1 = new ImageDtoOut();
        ImageDtoOut imageDtoOut2 = new ImageDtoOut();
        imageDtoOut1.setId(1);
        imageDtoOut2.setId(2);

        List<ImageDtoOut> images = Arrays.asList(imageDtoOut1, imageDtoOut2);
        when(imageService.getImagesByCategories(Arrays.asList(1, 2), 1)).thenReturn(images);

        mvc.perform(MockMvcRequestBuilders.get("/api/image/images")
                .param("categories", "1,2")
                .param("page", "1")
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2));
    }

}
