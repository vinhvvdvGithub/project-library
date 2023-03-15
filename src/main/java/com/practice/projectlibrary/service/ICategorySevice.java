package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.CategoryDTO;
import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.respone.CategoryRespone;
import com.practice.projectlibrary.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategorySevice {
    //list category
    List<CategoryRespone> categories();

    //add new category
    CategoryDTO addCategory(CategoryRequest categoryRequest);


    //add new list  category
    List<CategoryDTO> addListCategory(List<CategoryRequest> categoryRequest);



    //update category by slug && id
    CategoryDTO updateCategory(String categorySlug, Long id,CategoryRequest categoryRequest);



    //delete category by slug && id
    CategoryDTO deleteCategory(String categorySlug, Long id);

    //get detail category
    CategoryDTO categoryDetailById(Long id);


}
