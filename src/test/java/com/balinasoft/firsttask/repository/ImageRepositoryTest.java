package com.balinasoft.firsttask.repository;

import com.balinasoft.firsttask.domain.Category;
import com.balinasoft.firsttask.domain.Image;
import com.balinasoft.firsttask.domain.User;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetImagesByCategories() {
        // given
        Category category1 = new Category();
        category1.setName("first");
        Category category2 = new Category();
        category2.setName("second");
        Category category3 = new Category();
        category3.setName("third");

        User user1 = new User();
        User user2 = new User();

        userRepository.save(user1);
        userRepository.save(user2);

        Image image1 = new Image();
        Image image2 = new Image();
        Image image3 = new Image();

        image1.addCategory(category1);
        image1.addCategory(category2);
        image2.addCategory(category2);
        image3.addCategory(category3);

        image1.setUser(user1);
        image2.setUser(user2);
        image3.setUser(user1);

        Set<Image> expectedImages = Sets.newHashSet(image1);

        // when
        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);

        List<Integer> categoriesIds = Arrays.asList(category1.getId(), category2.getId());
        Set<Image> imagesByCategories = imageRepository.getImagesByUserAndCategories(user1.getId(), categoriesIds);

        // then
        assertEquals(expectedImages, imagesByCategories);
    }

}
