package com.balinasoft.firsttask.controller;


import com.balinasoft.firsttask.controller.api2.PhotoController;
import com.balinasoft.firsttask.dto.api2.PhotoTypeDtoOut;
import com.balinasoft.firsttask.service.api2.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PhotoController.class, secure = false)
public class PhotoControllerTest {

    private static final String PHOTO_API_PATH = "/api/v2/photo";

    @MockBean
    private PhotoService photoService;
    @Autowired
    private MockMvc mvc;


    @Test
    public void testGetAccount() throws Exception {
        //given
        PhotoTypeDtoOut dtoOut = new PhotoTypeDtoOut(1, "NAME", "IMAGE");
        when(photoService.getTypes(1)).thenReturn(new PageImpl<>(Collections.singletonList(dtoOut)));

        //when then
        mvc.perform(
                MockMvcRequestBuilders.get(PHOTO_API_PATH + "/type")
                        .param("page", "1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("NAME"))
                .andExpect(jsonPath("$.content[0].image").value("https://junior.balinasoft.com/images/IMAGE"));
    }

}
