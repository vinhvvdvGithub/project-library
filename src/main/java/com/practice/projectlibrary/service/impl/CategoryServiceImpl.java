package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.CategoryMapper;
import com.practice.projectlibrary.common.stringUltils.StringConvertToSlug;
import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.response.CategoryResponse;
import com.practice.projectlibrary.entity.Category;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ICategoryRepository;

import com.practice.projectlibrary.service.ICategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategorySevice {
    @Autowired
    private ICategoryRepository categoryRepository;


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
        CategoryResponse categoryDTO;
        Category category = CategoryMapper.getInstance().toEntity(categoryRequest);
        category.setStatus(true);
        category.setCreatedBy("Librarian");
        categoryDTO = CategoryMapper.getInstance().toResponse(category);
        categoryRepository.save(category);

        return categoryDTO;
    }

    @Override
    public List<CategoryResponse> addListCategory(List<CategoryRequest> categoryRequest) {
        List<CategoryResponse> categoryDTOS = new ArrayList<>();
        for (CategoryRequest categoryReq : categoryRequest) {
            Category category = new Category();
            category.setCategoryName(categoryReq.getCategoryName());
            category.setStatus(true);
            category.setSlug(categoryReq.getSlug());
            category.setCreatedBy("Librarian");
            categoryDTOS.add(CategoryMapper.getInstance().toResponse(category));
            categoryRepository.save(category);
        }

        return categoryDTOS;
    }


    @Override
    @Modifying
    public CategoryResponse updateCategory(String categorySlug, Long id, CategoryRequest categoryRequest) {
        CategoryResponse categoryDTO;
        Optional<Category> categoryExist = Optional.ofNullable(categoryRepository.selectCategoryBySlugId(categorySlug, id));
        if (categoryExist.isPresent() && categoryExist.get().getStatus() == true) {
            categoryExist.get().setCategoryName(categoryRequest.getCategoryName());
            categoryExist.get().setSlug(StringConvertToSlug.covertStringToSlug(categoryRequest.getCategoryName()));
            categoryExist.get().setUpdatedBy("Librarian");
            categoryRepository.save(categoryExist.get());
            categoryDTO = CategoryMapper.getInstance().toResponse(categoryExist.get());

        } else {
            throw new NotFoundException("Category not found by slug && id");
        }
        return categoryDTO;
    }

    @Override
    @Modifying
    public CategoryResponse deleteCategory(String categorySlug, Long id) {
        CategoryResponse categoryDTO;
        Optional<Category> categoryExist = Optional.ofNullable(categoryRepository.selectCategoryBySlugId(categorySlug, id));
        if (categoryExist.isPresent() && categoryExist.get().getStatus() == true) {
            categoryExist.get().setStatus(false);
            categoryRepository.save(categoryExist.get());
            categoryDTO = CategoryMapper.getInstance().toResponse(categoryExist.get());

        } else {
            throw new NotFoundException("Category not found by slug && id");
        }
        return categoryDTO;
    }

    @Override
    public CategoryResponse categoryDetailById(Long id) {
        Category category = categoryRepository.selectCategoryById(id);

        CategoryResponse categoryDTO = new CategoryResponse();

        if (category != null) {
            categoryDTO = CategoryMapper.getInstance().toResponse(category);

            return categoryDTO;
        } else {
            throw new BadRequestException("Not found category by id");
        }
    }


}
