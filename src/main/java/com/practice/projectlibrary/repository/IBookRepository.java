package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {

    //list book
    @Query(value = "SELECT * FROM books WHERE active = true",nativeQuery = true)
    List<Book> books();

    //insert book

    //update book by slug && id

    //delete book by slug && id

    //select book by slug && id
    @Query(value = "SELECT * FROM books WHERE (active = true AND slug=:slug AND id=:id)",nativeQuery = true)
    Book getBookBySlugId(@Param("slug")String slug,@Param("id")Long id);


}
