package cl.duocuc.ecomarket.funcional;

import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.tipodatos.RecursoEcomarket;
import cl.duocuc.ecomarket.tipodatos.NivelPermiso;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.exception.ErrorDatos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PermisoFuncional implements CodigoDescripcion<String, String> {

    private final String codigo;
    private final String descripcion;
    private final TipoPermiso tipo;

    public PermisoFuncional(Permiso entidad) throws IllegalArgumentException {
        this.codigo = entidad.getClavePermiso();
        this.descripcion = entidad.getDescripcion();
        this.tipo = TipoPermiso.valueOf(entidad.getClavePermiso().toUpperCase());
    }

    //TODO: Considerar mover estos metodos a otra clase para evitar acceso estatico.

    public static Optional<List<PermisoFuncional>> buscarCandidatos(List<Permiso> permisos, TipoPermiso tipo) {
        return buscarCandidatos(permisos, tipo.getValor() % 100);
    }

    public static Optional<List<PermisoFuncional>> buscarCandidatos(List<Permiso> permisos, int tipo) {
        return buscarCandidatos(permisos, RecursoEcomarket.valueOf(tipo));
    }

    public static Optional<List<PermisoFuncional>> buscarCandidatos(List<Permiso> permisos, RecursoEcomarket recurso) {
        return buscarCandidatos0(
                permisos.stream().map(PermisoFuncional::new).toList(),
                recurso
        );
    }

    private static Optional<List<PermisoFuncional>> buscarCandidatos0(List<PermisoFuncional> permisos, RecursoEcomarket recurso) {
        return buscarCandidatos0(permisos, recurso.getCodigo());
    }

    private static Optional<List<PermisoFuncional>> buscarCandidatos0(List<PermisoFuncional> permisos, int recurso) {
        return !(permisos).stream()
                          .filter(p -> equalsRecurso(p.getTipo().getValor(), recurso))
                          .toList().isEmpty() ? Optional.of(permisos) : Optional.empty();
    }

    private static boolean equalsRecurso(int permiso, int idRecurso) {
        return permiso % 100 == idRecurso;
    }

    public static boolean esSuficiente(PermisoFuncional permiso, TipoPermiso tipo) {
        return esSuficiente(permiso.getTipo().getValor() / 100, tipo.getValor() / 100);
    }

    public static boolean esSuficiente(PermisoFuncional permiso, NivelPermiso nivel) {
        return esSuficiente(permiso.getTipo().getValor() / 100, nivel.getNivel());
    }

    public static boolean esSuficiente(PermisoFuncional permiso, int nivel) {
        return esSuficiente(permiso.getTipo().getValor() / 100, nivel);
    }

    private static boolean esSuficiente(int nivel, int nivelRequerido) {
        return nivel >= nivelRequerido;
    }

    public TipoPermiso getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PermisoFuncional permiso &&
                this.tipo.equals(permiso.tipo);
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }
}
