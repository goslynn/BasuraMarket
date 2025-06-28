package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorRut implements ConstraintValidator<Rut, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return esRutValido(value);
    }

    private boolean esRutValido(String rut){
        if (rut == null || rut.isEmpty()) return false;

        String[] partes = rut.split("-");
        if (partes.length != 2) return false;

        String numero = partes[0];
        String dv = partes[1].toUpperCase();

        if (!numero.matches("\\d+") || !dv.matches("[0-9K]")) return false;

        int suma = 0;
        int multiplicador = 2;
        for (int i = numero.length() - 1; i >= 0; i--) {
            suma += Integer.parseInt(String.valueOf(numero.charAt(i))) * multiplicador;
            multiplicador++;
            if (multiplicador > 7) multiplicador = 2;
        }

        int dvCalculado = 11 - (suma % 11);
        String dvEsperado;
        if (dvCalculado == 11) {
            dvEsperado = "0";
        } else if (dvCalculado == 10) {
            dvEsperado = "K";
        } else {
            dvEsperado = String.valueOf(dvCalculado);
        }

        return dv.equals(dvEsperado);
    }
}
