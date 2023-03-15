package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.CategoryDTO;
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

    //to Entity

    public Category toEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setSlug(categoryDTO.getSlug());
        return category;
    }


    //to DTO
    public CategoryDTO toDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setSlug(category.getSlug());


        List<BookDTO> booksDtoByCategory = category.getBookByCategory().stream().map(
            bookCategory ->
                    new BookDTO(
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

        categoryDTO.setBooksByCategory(booksDtoByCategory);

        return categoryDTO;
    }
}
