package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.dto.ResponseDto;
import com.balinasoft.firsttask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.balinasoft.firsttask.system.StaticWrapper.wrap;

/**
 * Created by .
 */

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseDto getCategories() {
        return wrap(categoryService.getAllCategories());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseDto addCategory(@RequestParam String name) {
        return wrap(categoryService.save(name));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseDto deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return wrap();
    }

}
