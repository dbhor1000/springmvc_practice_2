package javacode_dk.springmvc_practice.service.books;

import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyNoNotes;
import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyWithNotes;
import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {


    public Page<BookEntity> getBooksFromDatabase(Pageable pageable);
    public BookEntity getBookByNameAndYearAndAuthor(String bookName, BigDecimal yearWritten, String author);
    public BookEntity createBook(BookDtoWithAuthorNameOnlyWithNotes bookDtoWithAuthorNameOnlyWithNotes);
    public BookEntity updateBook(UpdateBookDTO updateBookDTO);
    public boolean deleteBookByName(BookDtoWithAuthorNameOnlyNoNotes bookDtoWithAuthorNameOnlyNoNotes);
}
