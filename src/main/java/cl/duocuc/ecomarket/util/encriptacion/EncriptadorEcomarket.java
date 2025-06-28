package cl.duocuc.ecomarket.util.encriptacion;


public class EncriptadorEcomarket implements Encriptador<String> {

    private final Encriptador<String> primero;
    private final Encriptador<String> segundo;


    private EncriptadorEcomarket(Encriptador<String> primero, Encriptador<String> segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public static EncriptadorEcomarket createEncriptadorEcomarket(Encriptador<String> primero, Encriptador<String> segundo) {
        return new EncriptadorEcomarket(primero, segundo);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String encriptar(String valor) {
        valor = primero.encriptar(valor);
        valor = segundo.encriptar(valor);
        return valor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String desencriptar(String valor) {
        valor = segundo.desencriptar(valor);
        valor = primero.desencriptar(valor);
        return valor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean encriptado(String valor) {
        return segundo.encriptado(valor) && primero.encriptado(segundo.desencriptar(valor));
    }


}
