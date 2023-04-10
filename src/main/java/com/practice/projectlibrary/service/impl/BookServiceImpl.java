package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.BookMapper;
import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.entity.Book;
import com.practice.projectlibrary.entity.Category;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.IBookRepository;
import com.practice.projectlibrary.repository.ICategoryRepository;
import com.practice.projectlibrary.service.IBookService;
import com.practice.projectlibrary.service.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
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
    private final IBookRepository bookRepository;
    private final ICategoryRepository categoryRepository;
    private final ICloudinaryService cloudinaryService;


    //list book from db return to client DTO format
    @Override
    public List<BookDTO> books() {
        List<BookDTO> bookDTOs = new ArrayList<>();

        bookRepository.books().stream().map(
                book -> bookDTOs.add(BookMapper.getInstance().toDto(
                        book)
                )).collect(Collectors.toList());

        return bookDTOs;
    }


    public BookDTO addBook(MultipartFile file, BookRequest bookRequest) {

        Book book = new Book();
        book = BookMapper.getInstance().toEntity(bookRequest);
        String url = cloudinaryService.uploadFile(file);
        book.setImage(url);
        book.setStatus(true);
        book.setCategory(categoryRepository.getById(bookRequest.getCategoryId()));


        book.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        bookRepository.save(book);

        return BookMapper.getInstance().toDto(book);


    }

    @Override
    public List<BookDTO> addListBook(List<BookRequest> bookRequest) {
        List<BookDTO> booksDTO = new ArrayList<>();

        for (BookRequest bookReq : bookRequest) {
            Book book = new Book();
            book = BookMapper.getInstance().toEntity(bookReq);
            book.setStatus(true);
            book.setCategory(categoryRepository.getById(bookReq.getCategoryId()));

            book.setCreatedBy("Librarian");

            bookRepository.save(book);

            booksDTO.add(BookMapper.getInstance().toDto(book));
        }
        return booksDTO;
    }

    @Override
    @Modifying
    public BookDTO updateBook(String slug, Long id, BookRequest bookRequest) {
        BookDTO bookDTO;
        Optional<Book> bookExist = Optional.ofNullable(bookRepository.getBookBySlugId(slug, id));
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
            bookDTO = BookMapper.getInstance().toDto(bookExist.get());

        } else {
            throw new BadRequestException("Something went wrong!!!!, check all filed of book request");
        }
        return bookDTO;
    }

    @Override
    @Modifying
    public BookDTO deleteBook(String slug, Long id) {
        BookDTO bookDTO;
        Optional<Book> bookExist = Optional.ofNullable(bookRepository.getBookBySlugId(slug, id));
        if (bookExist.isPresent()) {
            bookExist.get().setStatus(false);
            bookRepository.save(bookExist.get());
            bookDTO = BookMapper.getInstance().toDto(bookExist.get());
        } else {
            throw new NotFoundException("Not found book by slug and id, check again");
        }
        return bookDTO;
    }

}
