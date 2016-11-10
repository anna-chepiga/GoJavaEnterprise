package ua.goit.calculator.exception;

public class UnsupportedTypeException extends Exception {
    public UnsupportedTypeException() {
    }

    public UnsupportedTypeException(String message) {
        super(message);
    }
}
