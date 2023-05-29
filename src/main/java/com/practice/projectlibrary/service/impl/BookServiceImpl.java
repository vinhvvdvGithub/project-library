package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.BookMapper;
import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.dto.response.BookResponse;
import com.practice.projectlibrary.entity.Book;
import com.practice.projectlibrary.entity.Category;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.IBookRepository;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.IBookService;
import com.practice.projectlibrary.service.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
  private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

  private final IBookRepository bookRepository;
  private final ICategoryRepository categoryRepository;
  private final ICloudinaryService cloudinaryService;


  //list book from db return to client DTO format
  @Override
  public List<BookResponse> books() {
    List<BookResponse> bookResponses = new ArrayList<>();

    bookRepository.books().stream().map(
        book -> bookResponses.add(BookMapper.getInstance().toResponse(
            book)
        )).collect(Collectors.toList());

    return bookResponses;
  }

  @Override
  public BookResponse searchBoook(String slug) {
    Optional<Book> bookExist = bookRepository.searchBookBySlug(slug);

    if (bookExist.isPresent()) {
      return BookMapper.getInstance().toResponse(bookExist.get());
    } else {
      throw new NotFoundException("Book not found");
    }

  }


  public BookResponse addBook(MultipartFile file, BookRequest bookRequest) {
    Optional<Category> categoryExist = categoryRepository.findCategoryById(bookRequest.getCategoryId());
    if (categoryExist.isPresent()) {
      if (file.isEmpty()) {
        throw new BadRequestException("File image is mandatory");
      } else {
        Book book = BookMapper.getInstance().toEntity(bookRequest);
        String url = cloudinaryService.uploadFile(file);
        book.setImage(url);
        book.setActive(true);
        book.setCategory(categoryExist.get());
        book.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        bookRepository.save(book);
        return BookMapper.getInstance().toResponse(book);
      }
    } else {
      throw new NotFoundException("Category not found or empty");
    }


  }

  @Override
  public List<BookResponse> addListBook(List<BookRequest> bookRequest) {

    List<BookResponse> bookResponses = new ArrayList<>();

    for (BookRequest bookReq : bookRequest) {
      Book book = BookMapper.getInstance().toEntity(bookReq);
      book.setActive(true);
      book.setCategory(categoryRepository.getById(bookReq.getCategoryId()));
      book.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      bookRepository.save(book);

      bookResponses.add(BookMapper.getInstance().toResponse(book));
    }
    return bookResponses;
  }

  @Override
  @Modifying
  public BookResponse updateBook(String slug, Long id, BookRequest bookRequest) {

    Optional<Book> bookExist = bookRepository.getBookBySlugId(slug, id);
    if (bookExist.isPresent()) {
      bookExist.get().setBookTitle(bookRequest.getBookTitle());
      bookExist.get().setAuthor(bookRequest.getAuthor());
      bookExist.get().setDescription(bookRequest.getDescription());
      bookExist.get().setSlug(bookRequest.getSlug());
//            bookExist.get().setImage(bookRequest.getImage());
      bookExist.get().setQuantity(bookRequest.getQuantity());
      bookExist.get().setPrice(bookRequest.getPrice());
      Category category = categoryRepository.getById(bookRequest.getCategoryId());
      bookExist.get().setCategory(category);
      bookRepository.save(bookExist.get());
      return BookMapper.getInstance().toResponse(bookExist.get());

    } else {
      throw new BadRequestException("Something went wrong!!!!, check all filed of book request");
    }
  }

  @Override
  @Modifying
  public BookResponse deleteBook(String slug, Long id) {
    Optional<Book> bookExist = bookRepository.getBookBySlugId(slug, id);
    if (bookExist.isPresent()) {
      bookExist.get().setActive(false);
      bookExist.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
      bookRepository.save(bookExist.get());
      return BookMapper.getInstance().toResponse(bookExist.get());
    } else {
      throw new NotFoundException("Not found book by slug and id, check again");
    }
  }

  @Override
  public List<BookResponse> search(String slug) {
    List<Book> resultsBookEntity = bookRepository.searchBook(slug);

    if (resultsBookEntity.isEmpty()) {
      logger.debug("Không tìm thấy book bằng slug .... ");
      throw new NotFoundException("Không tìm thấy sách....");
    } else {
      List<BookResponse> resultsBookRespone = new ArrayList<>();
      resultsBookEntity.stream().map(result ->
          resultsBookRespone.add(BookMapper.getInstance().toResponse(result))

      ).collect(Collectors.toList());
      return resultsBookRespone;
    }

  }

}
