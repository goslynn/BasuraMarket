package cl.duocuc.ecomarket.util.encriptacion;

import org.springframework.beans.factory.annotation.Value;


public class EncriptadorVigenere implements Encriptador<String> {

    @Value("${ecomarket.encriptacion.clave-vigenere}")
    private String clave;
    private static final String prefix = "#ENCPTD#";

    /**
     * {@inheritDoc}
     */
    @Override
    public String encriptar(String valor) {
        StringBuilder resultado = new StringBuilder();
        final int longitudClave = clave.length();

        for (int i = 0, j = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (Character.isLetter(c)) {
                char claveChar = clave.charAt(j % longitudClave);
                int desplazamiento = claveChar - 'A';

                char pivote = Character.isUpperCase(c) ? 'A' : 'a';
                char charCifrado = (char) ((c - pivote + desplazamiento) % 26 + pivote);
                resultado.append(charCifrado);
                j++;
            } else {
                resultado.append(c);
            }
        }

        return prefix + resultado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String desencriptar(String valor) {
        if (!valor.startsWith(prefix)) {
            throw new IllegalArgumentException("El texto no parece estar encriptado con este método");
        }

        valor = valor.substring(prefix.length());

        StringBuilder resultado = new StringBuilder();
        final int longitudClave = clave.length();

        for (int i = 0, j = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (Character.isLetter(c)) {
                char claveChar = clave.charAt(j % longitudClave);
                int desplazamiento = claveChar - 'A';
                char pivote = Character.isUpperCase(c) ? 'A' : 'a';
                char charOriginal = (char) ((c - pivote - desplazamiento + 26) % 26 + pivote);
                resultado.append(charOriginal);
                j++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean encriptado(String valor) {
        return valor != null && valor.startsWith(prefix);
    }



}
