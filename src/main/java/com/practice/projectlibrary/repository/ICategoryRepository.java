package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.dto.respone.CategoryRespone;
import com.practice.projectlibrary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    //proxy entity for insert book
    Category getById(Long id);

    //list category
    @Query(value = "SELECT * FROM categories WHERE status = true",nativeQuery = true)
    List<Category> categories();
    //add new category

    //select category bu slug && id
    @Query(value = "SELECT * FROM categories WHERE status = true AND id=:id AND slug=:slug",nativeQuery = true)
    Category selectCategoryBySlugId(@Param("slug") String categorySlug, @Param("id") Long id);

    //get detail category by id
    @Query(value = "SELECT * FROM categories WHERE status = true AND id=:id",nativeQuery = true)
    Category selectCategoryById(@Param("id") Long id);
}
