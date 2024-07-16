package javacode_dk.springmvc_practice.service.books;

import javacode_dk.springmvc_practice.DTO.UpdateBookDTO;
import javacode_dk.springmvc_practice.model.AuthorEntity;
import javacode_dk.springmvc_practice.model.BookEntity;
import javacode_dk.springmvc_practice.repository.AuthorRepository;
import javacode_dk.springmvc_practice.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Page<BookEntity> getBooksFromDatabase(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public BookEntity getBookByNameAndYearAndAuthor(String bookName, BigDecimal yearWritten, String author) {
        BookEntity booksFounds = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(bookName, yearWritten, author);

        return booksFounds;
    }

    public BookEntity createBook(BookEntity book) {
        if(bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(book.getBookName(), book.getYearWritten(), book.getAuthorNameSimple()) != null){
            return null;
        }
        book.setId(0);
        AuthorEntity author = authorRepository.findAuthorByAuthorName(book.getAuthorNameSimple());
        book.setAuthor(author);
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public BookEntity updateBook(UpdateBookDTO updateBookDTO) {

        BookEntity findBookToUpdate = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(updateBookDTO.getBookNameOld(), updateBookDTO.getYearWrittenOld(), updateBookDTO.getAuthorNameSimpleOld());
        BookEntity checkIfBookInfoIsOccupied = bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(updateBookDTO.getBookNameNew(), updateBookDTO.getYearWrittenNew(), updateBookDTO.getAuthorNameSimpleNew());

        if(findBookToUpdate == null || checkIfBookInfoIsOccupied != null) {
            return null;
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
    public boolean deleteBookByName(BookEntity bookEntity) {

        if(bookRepository.findBookByNameAndYearWrittenAndAuthorNameSimple(bookEntity.getBookName(), bookEntity.getYearWritten(), bookEntity.getAuthorNameSimple()) == null){
            return false;
        }
        bookRepository.deleteByBookName(bookEntity.getBookName(), bookEntity.getYearWritten(), bookEntity.getAuthorNameSimple());
        return true;

    }

}
