package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.CategoryResponse;
import com.practice.projectlibrary.dto.response.ResponseObject;
import com.practice.projectlibrary.service.ICategorySevice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

  private final ICategorySevice categorySevice;


  //get list category
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryResponse> categories() {
    return categorySevice.categories();
  }

  //get detail category
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseObject categoryDetail(@PathVariable("id")   @Positive(message = "Category Id must greater than zero") Long id) {
    return new ResponseObject(HttpStatus.OK, "Detail category by id", categorySevice.categoryDetailById(id));
  }

  //search category by slug
  @GetMapping("/search/{slug}")
  public ResponseObject searchCategoryBySlug(@PathVariable("slug")   @NotNull(message = "Category slug is mandatory") String slug) {
    return new ResponseObject(HttpStatus.OK, "Search category by slug", categorySevice.searchCategoryBySlug(slug));
  }

  // add list category for init project
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
  @PutMapping("/{slug}/{id}")
  public CategoryResponse updateCategory(@PathVariable("slug") @NotNull(message = "slug is mandatory") String categorySlug, @PathVariable("id") @NotNull(message = "Category Id is mandatory") @Positive(message = "ID lớn hơn hoặc bằng 0") Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
    return categorySevice.updateCategory(categorySlug, id, categoryRequest);
  }

  @DeleteMapping("/{slug}/{id}")
  public CategoryResponse deleteCategory(@PathVariable("slug") @NotNull(message = "slug is mandatory") String categorySlug, @PathVariable("id") @NotNull(message = "Category Id is mandatory") @Positive(message = "ID lớn hơn hoặc bằng 0") Long id) {
    return categorySevice.deleteCategory(categorySlug, id);
  }


}
