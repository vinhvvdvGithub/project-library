package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

  //proxy entity for insert book
  Category getById(Long id);


//  Boolean existsCategoryById(Long id );


  //list category
  @Query(value = "SELECT * FROM categories WHERE active = true", nativeQuery = true)
  List<Category> categories();
  //add new category

  //select category by slug && id
  @Query(value = "SELECT * FROM categories WHERE active = true AND id=:id AND slug like %:slug%", nativeQuery = true)
  Optional<Category> selectCategoryBySlugId(String slug, Long id);

  //get detail category by id
  @Query(value = "SELECT * FROM categories WHERE active = true AND id=:id", nativeQuery = true)
  Optional<Category> selectCategoryById(Long id);

  @Query(value = "SELECT * FROM categories WHERE active = true AND id=:id", nativeQuery = true)
  Optional<Category> findCategoryById(Long id);

  @Query(value = "SELECT * FROM categories WHERE active = true AND slug like %:slug%", nativeQuery = true)
  Optional<Category> searchCategoryBySlug(String slug);
}
