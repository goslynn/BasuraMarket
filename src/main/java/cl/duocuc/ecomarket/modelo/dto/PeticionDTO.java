package cl.duocuc.ecomarket.modelo.dto;


/**
 * Interfaz para creacion de java records DTO
 * que son una peticion (JSON) requerida por la API
 * para representar una entidad de la base de datos
 * @param <E> Entidad a representar
 */
public interface PeticionDTO<E> {

    /**
     * Convierte el DTO a la entidad que representa
     * Es imposible que el retorno sea un registro existente en la base de datos,
     * La entidad sera generada en memoria y debera persistir mas adelante.
     * Esa responsabilidad no le corresponde ni al DTO ni a este metodo.
     * @return Entidad correspondiente
     */
    E toEntidad();

}
