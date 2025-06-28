package cl.duocuc.ecomarket.util.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(int status, String mensaje){
        this(HttpStatus.valueOf(status), mensaje);
    }

    public ApiException(HttpStatus estado, String mensaje){
        super(mensaje);
        this.status = estado;
    }

    public ApiException(int status, String mensaje, Throwable causa){
        this(HttpStatus.valueOf(status), mensaje, causa);
    }

    public ApiException(HttpStatus estado, String mensaje, Throwable causa){
        super(mensaje, causa);
        this.status = estado;
    }

    public HttpStatus getStatus() {
        return status;
    }



}
