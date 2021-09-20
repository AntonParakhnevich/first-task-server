package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.ResponseDto;
import com.balinasoft.firsttask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.balinasoft.firsttask.system.StaticWrapper.wrap;

//TODO add swagger annotations everywhere
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Secured("ROLE_USER")
    @GetMapping(value = "{id}")
    public ResponseDto getCategory(@PathVariable int id) {
        return wrap(categoryService.getCategory(id));
    }

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseDto getCategories(@RequestParam int page) {
        return wrap(categoryService.getCategories(page));
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseDto addCategory(@RequestBody @Valid CategoryDtoIn categoryDtoIn) {
        return wrap(categoryService.addCategory(categoryDtoIn));
    }

    @Secured("ROLE_USER")
    @DeleteMapping(value = "{id}")
    public ResponseDto deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return wrap();
    }

}
