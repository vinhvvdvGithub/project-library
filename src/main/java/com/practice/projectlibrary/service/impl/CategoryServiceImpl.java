package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.CategoryMapper;
import com.practice.projectlibrary.common.stringUltils.StringConvertToSlug;
import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.CategoryResponse;
import com.practice.projectlibrary.entity.Category;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.ICategorySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategorySevice {
  private final ICategoryRepository categoryRepository;


  @Override
  public List<CategoryResponse> categories() {
    List<CategoryResponse> categorysDTO = new ArrayList<>();

    categoryRepository.categories().stream().map(
        category -> categorysDTO.add(CategoryMapper.getInstance().toResponse(category))
    ).collect(Collectors.toList());
    return categorysDTO;

  }

  @Override
  public CategoryResponse addCategory(CategoryRequest categoryRequest) {

    //check category exist


    Category category = CategoryMapper.getInstance().toEntity(categoryRequest);
    category.setActive(true);
    category.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    categoryRepository.save(category);
    return CategoryMapper.getInstance().toResponse(category);
  }

  @Override
  public List<CategoryResponse> addListCategory(List<CategoryRequest> categoryRequest) {
    List<CategoryResponse> categoryDTOS = new ArrayList<>();
    for (CategoryRequest categoryReq : categoryRequest) {
      Category category = new Category();
      category.setCategoryName(categoryReq.getCategoryName());
      category.setActive(true);
      category.setSlug(categoryReq.getSlug());
      category.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      categoryDTOS.add(CategoryMapper.getInstance().toResponse(category));
      categoryRepository.save(category);
    }

    return categoryDTOS;
  }


  @Override
  @Modifying
  public CategoryResponse updateCategory(String categorySlug, Long id, CategoryRequest categoryRequest) {
    Optional<Category> categoryExist = categoryRepository.selectCategoryBySlugId(categorySlug, id);
    if (categoryExist.isPresent() && categoryExist.get().getActive() == true) {
      categoryExist.get().setCategoryName(categoryRequest.getCategoryName());
      categoryExist.get().setSlug(StringConvertToSlug.covertStringToSlug(categoryRequest.getCategoryName()));
      categoryExist.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      categoryRepository.save(categoryExist.get());
      return CategoryMapper.getInstance().toResponse(categoryExist.get());

    } else {
      throw new NotFoundException("Category not found by slug && id");
    }
  }

  @Override
  @Modifying
  public CategoryResponse deleteCategory(String categorySlug, Long id) {
    Optional<Category> categoryExist = categoryRepository.selectCategoryBySlugId(categorySlug, id);
    if (categoryExist.isPresent() ) {
      categoryExist.get().setActive(false);
      categoryExist.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      categoryRepository.save(categoryExist.get());
      return CategoryMapper.getInstance().toResponse(categoryExist.get());
    } else {
      throw new NotFoundException("Category not found by slug && id");
    }
  }

  @Override
  public CategoryResponse categoryDetailById(Long id) {
    Optional<Category> category = categoryRepository.selectCategoryById(id);

    if (category.isPresent()) {
      return CategoryMapper.getInstance().toResponse(category.get());
    } else {
      throw new NotFoundException("Not found category by id");
    }
  }

  @Override
  public CategoryResponse searchCategoryBySlug(String slug) {
    Optional<Category> category = categoryRepository.searchCategoryBySlug(slug);
    if (category.isPresent()) {
      return CategoryMapper.getInstance().toResponse(category.get());
    } else {
      throw new NotFoundException("Not found category by id");
    }

  }


}
