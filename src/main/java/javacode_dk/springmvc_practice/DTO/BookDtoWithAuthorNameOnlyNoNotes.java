package javacode_dk.springmvc_practice.DTO;

import java.math.BigDecimal;

public class BookDtoWithAuthorNameOnlyNoNotes {

    private String bookName;
    private BigDecimal yearWritten;
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

    public BigDecimal getYearWritten() {
        return yearWritten;
    }

    public void setYearWritten(BigDecimal yearWritten) {
        this.yearWritten = yearWritten;
    }
}
