package javacode_dk.springmvc_practice.repository;

import javacode_dk.springmvc_practice.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM book_table b WHERE b.bookName = :bookName AND b.yearWritten = :yearWritten AND b.authorNameSimple = :authorName")
    BookEntity findBookByNameAndYearWrittenAndAuthorNameSimple(@Param("bookName") String bookName, @Param("yearWritten") BigDecimal yearWritten, @Param("authorName") String authorName);
    @Modifying
    @Query("DELETE FROM book_table b WHERE b.bookName = :bookName AND b.yearWritten = :yearWritten AND b.authorNameSimple = :authorName")
    void deleteByBookName(@Param("bookName") String bookName, @Param("yearWritten") BigDecimal yearWritten, @Param("authorName") String authorName);
    Page<BookEntity> findAll(Pageable pageable);

}
