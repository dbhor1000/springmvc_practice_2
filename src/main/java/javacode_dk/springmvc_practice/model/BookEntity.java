package javacode_dk.springmvc_practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import javacode_dk.springmvc_practice.DTO.BookViews;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "book_table")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_name")
    @JsonView(BookViews.BookDtoWithAuthorNameOnlyNoNotes.class)
    private String bookName;
    @Column(name = "book_notes")
    @JsonView(BookViews.BookDtoWithAuthorNameOnlyWithNotes.class)
    private String bookNotes;
    @Column(name = "year_written")
    @JsonView(BookViews.BookDtoWithAuthorNameOnlyNoNotes.class)
    private BigDecimal yearWritten;
    @Column(name = "author_name_simple")
    @JsonView(BookViews.BookDtoWithAuthorNameOnlyNoNotes.class)
    private String authorNameSimple;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    private AuthorEntity author;

    public String getAuthorNameSimple() {
        return authorNameSimple;
    }

    public void setAuthorNameSimple(String authorNameSimple) {
        this.authorNameSimple = authorNameSimple;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNotes() {
        return bookNotes;
    }

    public void setBookNotes(String bookNotes) {
        this.bookNotes = bookNotes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getYearWritten() {
        return yearWritten;
    }

    public void setYearWritten(BigDecimal yearWritten) {
        this.yearWritten = yearWritten;
    }
}

