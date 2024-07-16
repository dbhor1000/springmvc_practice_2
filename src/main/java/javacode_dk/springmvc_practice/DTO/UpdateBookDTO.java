package javacode_dk.springmvc_practice.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class UpdateBookDTO {

    private String bookNameOld;
    private BigDecimal yearWrittenOld;
    private String authorNameSimpleOld;
    private String bookNameNew;
    private String bookNotesNew;
    private BigDecimal yearWrittenNew;
    private String authorNameSimpleNew;

    public String getAuthorNameSimpleNew() {
        return authorNameSimpleNew;
    }

    public void setAuthorNameSimpleNew(String authorNameSimpleNew) {
        this.authorNameSimpleNew = authorNameSimpleNew;
    }

    public String getAuthorNameSimpleOld() {
        return authorNameSimpleOld;
    }

    public void setAuthorNameSimpleOld(String authorNameSimpleOld) {
        this.authorNameSimpleOld = authorNameSimpleOld;
    }

    public String getBookNameNew() {
        return bookNameNew;
    }

    public void setBookNameNew(String bookNameNew) {
        this.bookNameNew = bookNameNew;
    }

    public String getBookNameOld() {
        return bookNameOld;
    }

    public void setBookNameOld(String bookNameOld) {
        this.bookNameOld = bookNameOld;
    }

    public String getBookNotesNew() {
        return bookNotesNew;
    }

    public void setBookNotesNew(String bookNotesNew) {
        this.bookNotesNew = bookNotesNew;
    }

    public BigDecimal getYearWrittenNew() {
        return yearWrittenNew;
    }

    public void setYearWrittenNew(BigDecimal yearWrittenNew) {
        this.yearWrittenNew = yearWrittenNew;
    }

    public BigDecimal getYearWrittenOld() {
        return yearWrittenOld;
    }

    public void setYearWrittenOld(BigDecimal yearWrittenOld) {
        this.yearWrittenOld = yearWrittenOld;
    }
}
