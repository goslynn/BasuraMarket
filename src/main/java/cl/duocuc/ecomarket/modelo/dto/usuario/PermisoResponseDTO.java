package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("id, clave, descripcion_permiso")
public record PermisoResponseDTO(

        Integer id,

        @Requerido
        String clave,

        @JsonProperty("descripcion_permiso")
        String descripcion

) implements RespuestaDTO {

        public static PermisoResponseDTO fromEntidad(Permiso p){
                return new PermisoResponseDTO(
                        p.getId(),
                        p.getClavePermiso(),
                        p.getDescripcion()
                );
        }

        @Override
        public Integer getCodigo() {
                return id;
        }

        @Override
        public String getDescripcion() {
                return String.format("%s - %s", clave, descripcion);
        }


}
