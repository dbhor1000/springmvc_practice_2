package javacode_dk.springmvc_practice.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import javacode_dk.springmvc_practice.DTO.BookViews;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "authors_table")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_birthdate")
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    private LocalDate birthDate;
    @Column(name = "author_notes")
    @JsonView(BookViews.BookDtoWithAuthorExtended.class)
    private String authorNotes;
    @Column(name = "author_books")
    @OneToMany(mappedBy = "author",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<BookEntity> booksAuthored = new ArrayList<>();


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<BookEntity> getBooksAuthored() {
        return booksAuthored;
    }

    public void setBooksAuthored(List<BookEntity> booksAuthored) {
        this.booksAuthored = booksAuthored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}