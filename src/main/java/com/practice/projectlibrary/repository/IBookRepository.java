package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

  //check exist
  Boolean existsBookBySlug(String slug);
  Boolean existsBookById(Long id);

  //list book
//  @Query(value = "SELECT * FROM books WHERE active = true", nativeQuery = true)
	@Query(value = "select b.id, b.book_title,c.category_name from books as b inner join categories as c on b.category_id = c.id where b.active = true", nativeQuery = true)
  List<Book> books();

  @Query(value = "SELECT * FROM books WHERE (active = true AND slug=:slug AND id=:id)", nativeQuery = true)
  Optional<Book> getBookBySlugId(String slug, Long id);

  @Query(value = "SELECT * FROM books WHERE (active = true AND slug like %:slug%)", nativeQuery = true)
  Optional<Book> searchBookBySlug(String slug);

  @Query(value = "SELECT b FROM books b WHERE (b.active = true AND (b.slug like %:slug% OR b.title like %:slug% OR b.description like %:slug%) )", nativeQuery = true)
  List<Book> searchBook(String slug);
}
