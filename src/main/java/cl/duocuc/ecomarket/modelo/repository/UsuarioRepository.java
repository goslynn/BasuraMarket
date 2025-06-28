package cl.duocuc.ecomarket.modelo.repository;

import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su correo electronico (username)
     * @param correoUsuario correo usuario a buscar
     * @return Opcional de usuario, unico usuario porque el correo es un dato irrepetible en la tabla.
     */
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);
}