package cl.duocuc.ecomarket.util.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class ManejadorExcepciones {

    private static final Logger log = LoggerFactory.getLogger(ManejadorExcepciones.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> manejarApiException(ApiException ex) {
        return manejarExcepcion(ex.getStatus(), ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidacion(MethodArgumentNotValidException ex) {
        traza(ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // Extraer errores campo por campo
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        body.put("message", "Error de validación");
        body.put("fields", errores);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> manejarSqlException(SQLException ex) {
        return manejarExcepcion(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorDatos("Error en la base de datos", ex));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> manejarRestricciones(ConstraintViolationException ex) {
        return manejarExcepcion(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<Object> manejarExcepcion(HttpStatus status, Throwable ex) {
        traza(ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        List<String> causas = extraerCausas(ex);
        if (!causas.isEmpty()) {
            body.put("cause", causas);
        }

        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());

        return new ResponseEntity<>(body, status);
    }



    private List<String> extraerCausas(Throwable ex) {
        List<String> lista = new ArrayList<>();
        Throwable causa = ex.getCause();

        while (causa != null) {
            String mensaje = causa.getMessage();
            if (mensaje != null && !mensaje.isEmpty() && !mensaje.equals(ex.getMessage())) {
                lista.add(mensaje);
            }
            causa = causa.getCause();
        }

        return lista.stream()
                    .distinct()
                    .toList();
    }


    private void traza(Throwable ex) {
        log.error("OOPS! algo malio sal: ", ex);
    }

}
