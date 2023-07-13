package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

  @NotBlank(message = "Category name is mandatory")
  @NotNull(message = "Category name is mandatory")
  @NotEmpty(message = "Category name is mandatory")
  private String categoryName;


}
