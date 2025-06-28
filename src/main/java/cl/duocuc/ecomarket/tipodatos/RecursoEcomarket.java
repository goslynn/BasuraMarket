package cl.duocuc.ecomarket.tipodatos;

import cl.duocuc.ecomarket.util.CodigoDescripcion;

public enum RecursoEcomarket implements CodigoDescripcion<Integer, String> {
    USUARIO(10, "user"),
    ROL(11, "rol"),
    PERMISO(12, "permiso"),
    INVENTARIO(30, "inventario"),
    MOVIMIENTO(31, "movimiento"),
    TIPOMOVIMIENTO(32, "tipo-movimiento"),
    BODEGA(34, "bodega"),
    PRODUCTO(40, "producto"),
    FAMILIA(41, "familia"),
    SUBFAMILIA(42, "subfamilia"),
    VENTA(50, "venta");

    final Integer codigo;
    final String descripcion;

    RecursoEcomarket(String descripcion){
        this(0, descripcion);
    }

    RecursoEcomarket(Integer codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }


    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }



    public static RecursoEcomarket valueOf(Integer codigo) {
        for (RecursoEcomarket recurso : values()) {
            if (recurso.getCodigo().equals(codigo)) {
                return recurso;
            }
        }
        throw new IllegalArgumentException("No se encontró un recurso con el código: " + codigo);
    }


}
