package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.CategoryResponse;
import com.practice.projectlibrary.dto.response.ResponseObject;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.ICategorySevice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final ICategorySevice categorySevice;


    private final ICategoryRepository categoryRepository;

    //get list category
    @GetMapping("/")
    public List<CategoryResponse> categories() {
        return categorySevice.categories();
    }

    //get detail category
    @GetMapping("/{id}")
    public ResponseObject categoryDetail(@PathVariable("id") Long id) {

        return new ResponseObject(HttpStatus.OK, "Detail category by id", categorySevice.categoryDetailById(id));
    }


    // add list category
    @PostMapping("/add-list-category")
    public List<CategoryResponse> addCategory(@RequestBody @Valid List<CategoryRequest> categoryRequest) {
        return categorySevice.addListCategory(categoryRequest);
    }

    //add category
    @PostMapping("/")
    public CategoryResponse addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categorySevice.addCategory(categoryRequest);
    }

    //update category by slug && id
    @PostMapping("/category/{slug}/{id}")
    public CategoryResponse updateCategory(@PathVariable("slug") String categorySlug, @PathVariable("id") Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        if (categorySlug == null) {
            throw new BadRequestException("Slug category is empty");
        }
        if (id == null) {
            throw new BadRequestException("Id category is empty");
        }
        return categorySevice.updateCategory(categorySlug, id, categoryRequest);
    }

    @DeleteMapping("/category/{slug}/{id}")
    public CategoryResponse deleteCategory(@Valid @PathVariable("slug") String categorySlug, @PathVariable("id") Long id) {
        if (categorySlug == null) {
            throw new BadRequestException("Slug category is empty");
        }
        if (id == null) {
            throw new BadRequestException("Id category is empty");
        }
        return categorySevice.deleteCategory(categorySlug, id);
    }


}
