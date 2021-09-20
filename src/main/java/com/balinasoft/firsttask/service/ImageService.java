package com.balinasoft.firsttask.service;

import com.balinasoft.firsttask.dto.ImageDtoIn;
import com.balinasoft.firsttask.dto.ImageDtoOut;

import java.util.List;
import java.util.Set;

public interface ImageService {
    ImageDtoOut uploadImage(ImageDtoIn imageDtoIn);

    void deleteImage(int id);

    List<ImageDtoOut> getImages(int page);

    List<ImageDtoOut> getImagesByCategories(List<Integer> categoriesIds, int page);

}
