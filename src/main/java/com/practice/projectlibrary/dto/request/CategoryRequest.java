package com.practice.projectlibrary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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
