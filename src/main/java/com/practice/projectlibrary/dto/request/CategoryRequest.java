package com.practice.projectlibrary.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
