package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.CategoryResponse;

import java.util.List;

public interface ICategorySevice {
  //list category
  List<CategoryResponse> categories();

  //add new category
  CategoryResponse addCategory(CategoryRequest categoryRequest);

  //add new list  category
  List<CategoryResponse> addListCategory(List<CategoryRequest> categoryRequest);


  //update category by slug && id
  CategoryResponse updateCategory(String categorySlug, Long id, CategoryRequest categoryRequest);


  //delete category by slug && id
  CategoryResponse deleteCategory(String categorySlug, Long id);

  //get detail category
  CategoryResponse categoryDetailById(Long id);


  CategoryResponse searchCategoryBySlug(String slug);
}
