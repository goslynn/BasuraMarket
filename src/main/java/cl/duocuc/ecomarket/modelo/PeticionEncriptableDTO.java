package cl.duocuc.ecomarket.modelo;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;

/**
 * Interfaz que define un DTO que puede ser convertido a una entidad, pero requiere
 * de un encriptador para realizar la conversión.
 *
 * @param <E> Tipo de entidad a la que se convertirá el DTO.
 */
public interface PeticionEncriptableDTO<E> extends PeticionDTO<E> {

    /**
     * por defecto este metodo no puede ser usado.
     * @return
     */
    @Override
    default E toEntidad() {
        throw new UnsupportedOperationException("No se puede convertir un DTO a una entidad sin encriptar");
    }

    /**
     * Convierte el DTO a la entidad que representa,
     * requiere un encriptador para procesar datos sensibles, se espera
     * que sea usado para encriptar x,y valores si no lo estaban.
     * @param enc Encriptador que se usara para encriptar los datos sensibles del DTO.
     * @return Entidad correspondiente, con los datos sensibles encriptados si es necesario.
     */
    E toEntidad(Encriptador<String> enc);


}
