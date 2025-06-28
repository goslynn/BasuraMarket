package cl.duocuc.ecomarket.tipodatos;

/**
 * Enum que posee todos los permisos conocidos y evaluables por el sistema
 * Son representados por un codigo de 3 digitos.
 */
public enum TipoPermiso {
    CREAR_USUARIO(NivelPermiso.CREACION, RecursoEcomarket.USUARIO),
    VER_USUARIOS(NivelPermiso.LECTURA, RecursoEcomarket.USUARIO),
    EDITAR_USUARIO(NivelPermiso.EDICION, RecursoEcomarket.USUARIO),
    CREAR_VENTA(NivelPermiso.CREACION, RecursoEcomarket.VENTA),
    VER_VENTAS(NivelPermiso.LECTURA, RecursoEcomarket.VENTA);

    final int valorCuantificable;

    /**
     * Extrae un valor cuantificable a partir de un nivel de permiso y un modulo
     * (modulo al que pertenece este permiso).
     * El formato del valor cuantificable siempre debe resultar en un numero de 3 digitos <br>
     * El primer digito siempre va a representar la centena extraida desde el {@link NivelPermiso} <br>
     * los segundos 2 digitos se rellenan con el modificador (valor numerico de {@link RecursoEcomarket}), rellena con 0 a la derecha si son necesarios.
     * Se espera que el modificador sea un valor entre 0 y 99
     * @param nivelPermiso primer digito del valor cuantificable
     * @param modulo modificador del valor cuantificable
     */
    TipoPermiso(NivelPermiso nivelPermiso, RecursoEcomarket modulo){
        int nivel = nivelPermiso.getNivel(); //100
        int centena = nivel / 100; //1
        int modificador = modulo.getCodigo(); //30

        if (modificador < 0 || modificador > 99) {
            throw new IllegalArgumentException("El código del módulo debe estar entre 0 y 99");
        }

        //String.format se encarga de rellenar con 0 a la derecha si hiciese falta
        String resultado = String.format("%d%02d", centena, modificador);
        this.valorCuantificable = Integer.parseInt(resultado); //130
    }

    public static TipoPermiso valueOf(int numero) {
        for (TipoPermiso t : TipoPermiso.values()) {
            if (t.valorCuantificable == numero) {
                return t;
            }
        }
        throw new IllegalArgumentException("No existe un tipo de permiso con el valor: " + numero);
    }



    public int getValor() {
        return valorCuantificable;
    }


    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.valorCuantificable);
    }
}
