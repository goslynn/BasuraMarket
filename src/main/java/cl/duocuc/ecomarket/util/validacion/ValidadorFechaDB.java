package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorFechaDB implements ConstraintValidator<FechaDB, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return esFechaValida(s,"yyyyMMdd");
    }

    private boolean esFechaValida(String fecha, String formato) {
        if (fecha == null || fecha.isEmpty()) return false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

}
