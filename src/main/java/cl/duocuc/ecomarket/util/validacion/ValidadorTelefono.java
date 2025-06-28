package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorTelefono implements ConstraintValidator<Telefono, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return esNumeroTelefonicoValido(value);
    }

    private boolean esNumeroTelefonicoValido(String numero){
        if (numero == null || numero.isEmpty()) return false;
        if (numero.length() > 9 && numero.startsWith("+")){
            numero = numero.substring(3);
        }
        String regex = "^[0-9]{9}$";
        return numero.matches(regex);
    }

}
