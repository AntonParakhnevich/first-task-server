package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.dto.ImageDtoOut;
import com.balinasoft.firsttask.repository.ImageRepository;
import com.balinasoft.firsttask.util.SecurityContextHolderWrapper;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private SecurityContextHolderWrapper securityContextHolderWrapper;

    @InjectMocks
    public ImageServiceImpl imageService;

    @Test
    public void testGetImagesByCategories() {
        // given
        when(securityContextHolderWrapper.currentUserId()).thenReturn(1);

        Image image = new Image();
        image.setId(1);
        when(imageRepository.getImagesByUserAndCategories(1, Arrays.asList(1, 2))).thenReturn(Sets.newHashSet(image));

        // when
        List<ImageDtoOut> images = imageService.getImagesByCategories(Arrays.asList(1, 2), 1);
        // then
        assertEquals(1, images.get(0).getId());
    }

}