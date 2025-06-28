package cl.duocuc.ecomarket.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Interfaz multiproposito que expone un codigo y una descripcion.
 * @param <C> Tipo del codigo.
 * @param <D> Tipo de la descripcion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"codigo", "descripcion"})
public interface CodigoDescripcion<C, D> {

    C getCodigo();

    D getDescripcion();

    @JsonIgnore
    default String getCodigoDescripcion() {
        return String.format("%s - %s", getCodigo(), getDescripcion());
    }

    /**
     * Crea una instancia de {@link CodigoDescripcion} con los valores proporcionados. <br>
     * Se recomienda usar este metodo en lugar de crear instancias anonimas.
     *
     * @param codigo      El codigo a establecer.
     * @param descripcion La descripcion a establecer.
     * @param <C>    Tipo del codigo.
     * @param <D> Tipo de la descripcion.
     * @return Una instancia de {@link CodigoDescripcion} con los valores proporcionados.
     */
    static <C, D> CodigoDescripcion<C, D> of(C codigo, D descripcion) {
        return new CodigoDescripcion<>() {
            @Override
            public C getCodigo() {
                return codigo;
            }

            @Override
            public D getDescripcion() {
                return descripcion;
            }
        };
    }

}
