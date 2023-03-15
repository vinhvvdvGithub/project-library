package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.CategoryMapper;
import com.practice.projectlibrary.common.stringUltils.StringConvertToSlug;
import com.practice.projectlibrary.dto.CategoryDTO;
import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.respone.CategoryRespone;
import com.practice.projectlibrary.entity.Book;
import com.practice.projectlibrary.entity.Category;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.IBookService;
import com.practice.projectlibrary.service.ICategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategorySevice {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IBookService bookService;




    @Override
    public List<CategoryRespone> categories() {
        return categoryRepository.categories().stream().map(
                category -> new CategoryRespone(
                        category.getCategoryName(),
                        category.getSlug()
                )
        ).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO addCategory(CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO;
        Category category = new Category();

        category.setCategoryName(categoryRequest.getCategoryName());
        category.setSlug(categoryRequest.getSlug());
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setCreatedBy("Librarian");
        categoryDTO= CategoryMapper.getInstance().toDTO(category);

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> addListCategory(List<CategoryRequest> categoryRequest) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryRequest categoryReq : categoryRequest) {
            Category category = new Category();
            category.setCategoryName(categoryReq.getCategoryName());
            category.setStatus(true);
            category.setSlug(categoryReq.getSlug());
            category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            category.setCreatedBy("Librarian");
            categoryDTOS.add(CategoryMapper.getInstance().toDTO(category));
            categoryRepository.save(category);
        }

        return categoryDTOS;
    }


    @Override
    @Modifying
    public CategoryDTO updateCategory(String categorySlug, Long id,CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO;
        Optional<Category> categoryExist = Optional.ofNullable(categoryRepository.selectCategoryBySlugId(categorySlug, id));
        if(categoryExist.isPresent() && categoryExist.get().getStatus()==true){
            categoryExist.get().setCategoryName(categoryRequest.getCategoryName());
            categoryExist.get().setSlug(StringConvertToSlug.covertStringToSlug(categoryRequest.getCategoryName()));
            categoryExist.get().setUpdatedBy("Librarian");
            categoryRepository.save(categoryExist.get());
            categoryDTO= CategoryMapper.getInstance().toDTO(categoryExist.get());

        }else {
            throw new NotFoundException("Category not found by slug && id");
        }
        return categoryDTO;
    }

    @Override
    @Modifying
    public CategoryDTO deleteCategory(String categorySlug, Long id) {
        CategoryDTO categoryDTO;
        Optional<Category> categoryExist = Optional.ofNullable(categoryRepository.selectCategoryBySlugId(categorySlug, id));
        if(categoryExist.isPresent() && categoryExist.get().getStatus()==true){
            categoryExist.get().setStatus(false);
            categoryRepository.save(categoryExist.get());
            categoryDTO=CategoryMapper.getInstance().toDTO(categoryExist.get());

        }else {
            throw new NotFoundException("Category not found by slug && id");
        }
        return categoryDTO;
    }

    @Override
    public CategoryDTO categoryDetailById(Long id) {
        Category category = categoryRepository.selectCategoryById(id);

        CategoryDTO categoryDTO = new CategoryDTO();

        if(category != null){
            categoryDTO = CategoryMapper.getInstance().toDTO(category);

            return categoryDTO;
        }else{
            throw new BadRequestException("Not found category by id");
        }
    }


}
