package javacode_dk.springmvc_practice.DTO;

public class BookViews {

    public static class BookDtoWithAuthorNameOnlyNoNotes {}
    public static class BookDtoWithAuthorNameOnlyWithNotes extends  BookDtoWithAuthorNameOnlyNoNotes{}
    public static class BookDtoWithAuthorExtended extends BookDtoWithAuthorNameOnlyWithNotes {}

}
