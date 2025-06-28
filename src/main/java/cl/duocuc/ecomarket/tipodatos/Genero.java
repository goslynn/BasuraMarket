package cl.duocuc.ecomarket.tipodatos;

public enum Genero {
    MASCULINO('M'),
    FEMENINO('F'),
    UNDEFINED('X');

    private char g;

    Genero(char g) {
        this.g = g;
    }

    /**
     * @return valor char del genero ex: 'M' o 'F'
     */
    public char toChar() {
        return g;
    }

    public static Genero valueOf(char g) {
        for (Genero genero : Genero.values()) {
            if (genero.g == g) {
                return genero;
            }
        }
        return null;
    }

}
