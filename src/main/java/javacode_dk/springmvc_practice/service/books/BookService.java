package javacode_dk.springmvc_practice.service.books;

import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.model.BookEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {


    public Page<BookEntity> getBooksFromDatabase(int page, int size);
    public BookEntity getBookByNameAndYearAndAuthor(String bookName, BigDecimal yearWritten, String author);
    public BookEntity createBook(BookEntity book);
    public BookEntity updateBook(UpdateBookDTO updateBookDTO);
    public boolean deleteBookByName(BookEntity bookEntity);
}
