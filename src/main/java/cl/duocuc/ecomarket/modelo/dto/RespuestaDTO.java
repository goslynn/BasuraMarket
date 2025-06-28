package cl.duocuc.ecomarket.modelo.dto;

import cl.duocuc.ecomarket.util.CodigoDescripcion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Interfaz para la creacion de java records DTO
 * que son una respuesta (JSON) de la API
 * representando una entidad de la base de datos
 */
@JsonIgnoreProperties({"codigo", "descripcion"})
public interface RespuestaDTO extends CodigoDescripcion<Integer, String> {}
