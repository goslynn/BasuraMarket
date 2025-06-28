package cl.duocuc.ecomarket.util.exception;

public class ErrorDatos extends Exception {
    public ErrorDatos(String message) {
        super(message);
    }
    public ErrorDatos(String message, Throwable cause) {
        super(message, cause);
    }
}