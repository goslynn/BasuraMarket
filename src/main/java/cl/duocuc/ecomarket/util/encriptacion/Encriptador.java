package cl.duocuc.ecomarket.util.encriptacion;


public interface Encriptador<T> {

    /**
     * Encripta el valor de tipo T.
     *
     * @param valor El valor a encriptar.
     * @return El valor encriptado.
     */
    T encriptar(T valor);

    /**
     * Desencripta el valor de tipo T.
     *
     * @param valor El valor a desencriptar.
     * @return El valor desencriptado.
     */
    T desencriptar(T valor);

    /**
     * Indica si el valor está encriptado.
     * @param valor valor a verificar
     * @return <code>true</code> si el valor está encriptado,<br>
     *          <code>false</code> en caso contrario.
     */
    boolean encriptado(T valor);


}
