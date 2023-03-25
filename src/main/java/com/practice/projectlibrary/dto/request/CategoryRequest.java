package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Category name is mandatory")
    @NotNull(message = "Category name is mandatory")
    @NotEmpty(message = "Category name is mandatory")
    private String categoryName;

    @NotBlank(message = "Category slug is mandatory")
    @NotNull(message = "Category slug is mandatory")
    @NotEmpty(message = "Category slug is mandatory")
    private String slug;
}
