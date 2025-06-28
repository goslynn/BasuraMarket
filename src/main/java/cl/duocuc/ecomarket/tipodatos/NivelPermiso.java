package cl.duocuc.ecomarket.tipodatos;

public enum NivelPermiso {
    LECTURA(100),
    EDICION(200),
    CREACION(300),
    ELIMINACION(400),
    ADMINISTRACION(500);

    private final int nivel;

    NivelPermiso(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public static NivelPermiso valueOf(int nivel) {
        for (NivelPermiso permiso : NivelPermiso.values()) {
            if (permiso.getNivel() == nivel) {
                return permiso;
            }
        }
        throw new IllegalArgumentException("No existe un nivel de permiso con el valor: " + nivel);
    }

}
