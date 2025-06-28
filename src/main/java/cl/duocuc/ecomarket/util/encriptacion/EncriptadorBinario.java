package cl.duocuc.ecomarket.util.encriptacion;


public class EncriptadorBinario implements Encriptador<String>{

    /**
     * {@inheritDoc}
     */
    @Override
    public String encriptar(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (char c : valor.toCharArray()) {
            String binario = Integer.toBinaryString(c);
            while (binario.length() < 8) {
                binario = "0" + binario;
            }
            resultado.append(binario);
        }
        return resultado.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String desencriptar(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < valor.length(); i += 8) {
            String byteStr = valor.substring(i, Math.min(i + 8, valor.length()));
            char c = (char) Integer.parseInt(byteStr, 2);
            resultado.append(c);
        }
        return resultado.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean encriptado(String valor) {
        if (valor == null || valor.isEmpty() || valor.length() % 8 != 0) {
            return false;
        }
        for (char c : valor.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

}
