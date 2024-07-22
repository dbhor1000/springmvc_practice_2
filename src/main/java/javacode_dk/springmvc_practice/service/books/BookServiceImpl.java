package javacode_dk.springmvc_practice.service.books;

import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyNoNotes;
import javacode_dk.springmvc_practice.DTO.BookDtoWithAuthorNameOnlyWithNotes;
import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.exception.BookAlreadyExistsException;
import javacode_dk.springmvc_practice.exception.BookDoesNotExistException;
import javacode_dk.springmvc_practice.model.AuthorEntity;
import javacode_dk.springmvc_practice.model.BookEntity;
import javacode_dk.springmvc_practice.repository.AuthorRepository;
import javacode_dk.springmvc_practice.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Page<BookEntity> getBooksFromDatabase(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public BookEntity getBookByNameAndYearAndAuthor(String bookName, BigDecimal yearWritten, String author) {

        BookEntity booksFounds = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(bookName, yearWritten, author);

        if(booksFounds == null) {
            throw new BookDoesNotExistException("Requested book not found");
        }

        return booksFounds;
    }

    public BookEntity createBook(BookDtoWithAuthorNameOnlyWithNotes bookDtoWithAuthorNameOnlyWithNotes) {

        BookEntity bookFound = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(bookDtoWithAuthorNameOnlyWithNotes.getBookName(), bookDtoWithAuthorNameOnlyWithNotes.getYearWritten(), bookDtoWithAuthorNameOnlyWithNotes.getAuthorNameSimple());
        if(bookFound != null){
            throw new BookAlreadyExistsException("Book already exists.");
        }

        BookEntity newBook = new BookEntity();
        newBook.setId(0);
        newBook.setBookName(bookDtoWithAuthorNameOnlyWithNotes.getBookName());
        newBook.setBookNotes(bookDtoWithAuthorNameOnlyWithNotes.getBookNotes());
        newBook.setYearWritten(bookDtoWithAuthorNameOnlyWithNotes.getYearWritten());
        newBook.setAuthorNameSimple(bookDtoWithAuthorNameOnlyWithNotes.getAuthorNameSimple());
        AuthorEntity author = authorRepository.findAuthorByAuthorName(bookDtoWithAuthorNameOnlyWithNotes.getAuthorNameSimple());
        newBook.setAuthor(author);
        bookRepository.save(newBook);
        return newBook;
    }

    @Transactional
    public BookEntity updateBook(UpdateBookDTO updateBookDTO) {

        BookEntity findBookToUpdate = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(updateBookDTO.getBookNameOld(), updateBookDTO.getYearWrittenOld(), updateBookDTO.getAuthorNameSimpleOld());
        BookEntity checkIfBookInfoIsOccupied = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(updateBookDTO.getBookNameNew(), updateBookDTO.getYearWrittenNew(), updateBookDTO.getAuthorNameSimpleNew());

        if(findBookToUpdate == null) {
            throw new BookDoesNotExistException("No book to update in database.");
        }
        if(checkIfBookInfoIsOccupied != null) {
            throw new BookAlreadyExistsException("Book information to update is already occupied");
        }

        findBookToUpdate.setBookName(updateBookDTO.getBookNameNew());
        findBookToUpdate.setAuthorNameSimple(updateBookDTO.getAuthorNameSimpleNew());
        findBookToUpdate.setBookNotes(updateBookDTO.getBookNotesNew());
        findBookToUpdate.setYearWritten(updateBookDTO.getYearWrittenNew());
        findBookToUpdate.setAuthor(authorRepository.findAuthorByAuthorName(updateBookDTO.getAuthorNameSimpleNew()));

        bookRepository.save(findBookToUpdate);
        return findBookToUpdate;
    }

    @Transactional
    public boolean deleteBookByName(BookDtoWithAuthorNameOnlyNoNotes bookDtoWithAuthorNameOnlyNoNotes) {

        if(bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(bookDtoWithAuthorNameOnlyNoNotes.getBookName(), bookDtoWithAuthorNameOnlyNoNotes.getYearWritten(), bookDtoWithAuthorNameOnlyNoNotes.getAuthorNameSimple()) == null){
            throw new BookDoesNotExistException("Book for deletion does not exist.");
        }
        bookRepository.deleteByBookName(bookDtoWithAuthorNameOnlyNoNotes.getBookName(), bookDtoWithAuthorNameOnlyNoNotes.getYearWritten(), bookDtoWithAuthorNameOnlyNoNotes.getAuthorNameSimple());
        return true;

    }

}
