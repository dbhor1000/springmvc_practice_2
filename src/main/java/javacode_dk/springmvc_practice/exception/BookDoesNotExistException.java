package javacode_dk.springmvc_practice.exception;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(String message) {
        super(message);
    }
}