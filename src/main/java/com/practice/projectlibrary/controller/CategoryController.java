package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.CategoryDTO;
import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.respone.ResponeObject;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.ICategorySevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@Validated
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private ICategorySevice categorySevice;

    @Autowired
    private ICategoryRepository categoryRepository;

    //get list category
    @GetMapping("/")
    public List<CategoryDTO> categories(){
        return categorySevice.categories();
    }

    //get detail category
    @GetMapping("/{id}")
    public ResponeObject categoryDetail(@PathVariable("id") Long id){

        return new ResponeObject(HttpStatus.OK,"Detail category by id",categorySevice.categoryDetailById(id)) ;
    }



    // add list category
    @PostMapping("/add-list-category")
    public List<CategoryDTO> addCategory(@RequestBody List<CategoryRequest> categoryRequest){
        return categorySevice.addListCategory(categoryRequest);
    }

    //add category
    @PostMapping("/")
    public CategoryDTO addCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categorySevice.addCategory(categoryRequest);
    }

    //update category by slug && id
    @PostMapping("/category/{slug}/{id}")
    public CategoryDTO updateCategory(@PathVariable("slug") String categorySlug, @PathVariable("id") Long id,@Valid @RequestBody CategoryRequest categoryRequest){
        if(categorySlug == null){
            throw new BadRequestException("Slug category is empty");
        }
        if(id == null){
            throw new BadRequestException("Id category is empty");
        }
        return categorySevice.updateCategory(categorySlug,id,categoryRequest);
    }

    @DeleteMapping("/category/{slug}/{id}")
    public CategoryDTO deleteCategory(@Valid @PathVariable("slug") String categorySlug, @PathVariable("id") Long id){
        if(categorySlug == null){
            throw new BadRequestException("Slug category is empty");
        }
        if(id == null){
            throw new BadRequestException("Id category is empty");
        }
        return categorySevice.deleteCategory(categorySlug,id);
    }





}
