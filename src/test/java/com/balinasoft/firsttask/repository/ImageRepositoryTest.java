package com.balinasoft.firsttask.repository;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.domain.User;
import com.balinasoft.firsttask.dto.ImageDtoOut;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by .
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetImagesByCategories() throws Exception {
        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        category1.setName("first");

        Category category2 = new Category();
        category2.setName("second");

        Category category3 = new Category();
        category3.setName("third");

        List<Image> images = new ArrayList<>();

        Image image = new Image();
        Image image1 = new Image();
        Image image2 = new Image();

        category1.addImage(image);
        category1.addImage(image1);
        category3.addImage(image2);

        image.addCategory(category1);
        image1.addCategory(category1);
        image2.addCategory(category3);

        categoryRepository.save(category1);
        categoryRepository.save(category3);
        categoryRepository.save(category2);

        imageRepository.save(image);
        imageRepository.save(image1);
        imageRepository.save(image2);

        categories.add(category1);
        categories.add(category2);

        images.add(image);
        images.add(image1);

//        List<Image> imagesByCategories = imageRepository.getImagesByCategoriesIn(categories,new PageRequest(1,5));
        List<Image> imagesByCategories = imageRepository.getImagesByCategoriesIn(categories);

        Assert.assertEquals(imagesByCategories,images);
    }

}
