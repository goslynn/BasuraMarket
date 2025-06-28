package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacionContrasena implements ConstraintValidator<Contrasena, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return esContrasenaValida(value);
    }

    /**
     * Pide contrasena de al menos 8 caracteres, al menos una mayúscula, una minúscula y un número.
     * @param contrasena contrasena a validar
     * @return true si es valido false si no
     */
    private boolean esContrasenaValida(String contrasena) {
        if (contrasena == null || contrasena.isEmpty()) return false;
        if (contrasena.length() < 8) return false;
        if (!contrasena.matches(".*[A-Z].*")) return false;
        if (!contrasena.matches(".*[a-z].*")) return false;
        return contrasena.matches(".*[0-9].*");
    }
}
