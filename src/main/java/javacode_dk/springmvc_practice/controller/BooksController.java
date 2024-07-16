package javacode_dk.springmvc_practice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javacode_dk.springmvc_practice.DTO.BookViews;
import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.model.BookEntity;
import javacode_dk.springmvc_practice.service.books.BookService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<BookEntity>> fetchAllBooks(@RequestParam int page, @RequestParam int size) {
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Page<BookEntity> allBooks = bookService.getBooksFromDatabase(page, size);
        return ResponseEntity.ok((allBooks.getContent()));
    }

    @GetMapping("/getOneBook")
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<?> fetchOneBook(@RequestBody @JsonView(BookViews.BookDtoWithAuthorNameOnlyNoNotes.class) BookEntity bookEntity) {

        BookEntity booksFound = bookService.getBookByNameAndYearAndAuthor(bookEntity.getBookName(), bookEntity.getYearWritten(), bookEntity.getAuthorNameSimple());
        if(booksFound == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(booksFound);
    }

    @PostMapping
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<BookEntity> newBook(@RequestBody @JsonView(BookViews.BookDtoWithAuthorNameOnlyWithNotes.class) BookEntity newBook) {

        BookEntity createdBook = bookService.createBook(newBook);

        if(createdBook == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(createdBook);
    }

    @PatchMapping()
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    public ResponseEntity<BookEntity> updateBook(@RequestBody UpdateBookDTO updateBookDTO) {

        BookEntity updatedBook = bookService.updateBook(updateBookDTO);

        if(updatedBook == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBook(@RequestBody @JsonView(BookViews.BookDtoWithAuthorNameOnlyNoNotes.class) BookEntity bookEntity) {

        if (bookService.deleteBookByName(bookEntity)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
