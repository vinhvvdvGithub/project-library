package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.BookResponse;
import com.practice.projectlibrary.dto.response.CategoryResponse;
import com.practice.projectlibrary.entity.Category;

import java.util.List;
import java.util.stream.Collectors;


public class CategoryMapper {

  private static CategoryMapper INSTANCE;

  public static CategoryMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CategoryMapper();
    }
    return INSTANCE;
  }


  //to category Entity from request
  public Category toEntity(CategoryRequest categoryRequest) {
    Category category = new Category();
    category.setCategoryName(categoryRequest.getCategoryName());
    category.setSlug(categoryRequest.getSlug());
    return category;
  }


  //to category response
  public CategoryResponse toResponse(Category category) {
    CategoryResponse categoryResponse = new CategoryResponse();
    categoryResponse.setCategoryName(category.getCategoryName());
    categoryResponse.setSlug(category.getSlug());


    List<BookResponse> bookResponsesByCategory = category.getBookByCategory().stream().map(
        bookCategory ->
            new BookResponse(
                bookCategory.getBookTitle(),
                bookCategory.getAuthor(),
                bookCategory.getDescription(),
                bookCategory.getSlug(),
                bookCategory.getImage(),
                bookCategory.getQuantity(),
                bookCategory.getPrice(),
                bookCategory.getCategory().getCategoryName()
            )
    ).collect(Collectors.toList());

    categoryResponse.setBooksByCategory(bookResponsesByCategory);

    return categoryResponse;
  }
}
