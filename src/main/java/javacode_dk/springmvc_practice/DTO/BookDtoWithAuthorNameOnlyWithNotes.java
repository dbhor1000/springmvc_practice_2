package javacode_dk.springmvc_practice.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class BookDtoWithAuthorNameOnlyWithNotes {

    private String bookName;
    private BigDecimal yearWritten;
    private String bookNotes;
    private String authorNameSimple;

    public String getAuthorNameSimple() {
        return authorNameSimple;
    }

    public void setAuthorNameSimple(String authorNameSimple) {
        this.authorNameSimple = authorNameSimple;
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

    public BigDecimal getYearWritten() {
        return yearWritten;
    }

    public void setYearWritten(BigDecimal yearWritten) {
        this.yearWritten = yearWritten;
    }
}
