package javacode_dk.springmvc_practice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyNoNotes;
import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyWithNotes;
import javacode_dk.springmvc_practice.DTO.BookViews;
import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.model.BookEntity;
import javacode_dk.springmvc_practice.service.books.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;

@RestController
@Validated
@RequestMapping("/book")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    //1) Получения списка всех книг с возможностью сортировки и пагинации. Используйте Pageable для указания страницы и размера страницы.
    //2) Получения информации о конкретной книге.
    //3) Добавления новых книг.
    //4) Обновления информации о книге.
    //5)Удаления книги.

    @GetMapping
    @JsonView(BookViews.BookDtoWithAuthorNameOnlyWithNotes.class)
    public ResponseEntity<List<BookEntity>> fetchAllBooks(Pageable pageable) {
        if (pageable.getPageNumber() < 0 || pageable.getPageSize() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Page<BookEntity> allBooks = bookService.getBooksFromDatabase(pageable);
        return ResponseEntity.ok((allBooks.getContent()));
    }

    @GetMapping("/getOneBook")
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<?> fetchOneBook(@RequestBody BookDtoWithAuthorNameOnlyNoNotes bookDtoWithAuthorNameOnlyNoNotes) {

        BookEntity booksFound = bookService.getBookByNameAndYearAndAuthor(bookDtoWithAuthorNameOnlyNoNotes.getBookName(), bookDtoWithAuthorNameOnlyNoNotes.getYearWritten(), bookDtoWithAuthorNameOnlyNoNotes.getAuthorNameSimple());
        return ResponseEntity.ok(booksFound);
    }

    @PostMapping
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<BookEntity> newBook(@RequestBody BookDtoWithAuthorNameOnlyWithNotes bookDtoWithAuthorNameOnlyWithNotes) {

        BookEntity createdBook = bookService.createBook(bookDtoWithAuthorNameOnlyWithNotes);
        return ResponseEntity.ok(createdBook);
    }

    @PatchMapping()
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<BookEntity> updateBook(@RequestBody UpdateBookDTO updateBookDTO) {

        BookEntity updatedBook = bookService.updateBook(updateBookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBook(@RequestBody BookDtoWithAuthorNameOnlyNoNotes bookDtoWithAuthorNameOnlyNoNotes) {

        bookService.deleteBookByName(bookDtoWithAuthorNameOnlyNoNotes);
            return ResponseEntity.ok().build();

    }
}
