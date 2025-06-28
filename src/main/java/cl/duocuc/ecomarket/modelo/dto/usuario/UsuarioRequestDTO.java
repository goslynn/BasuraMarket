package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.PeticionEncriptableDTO;
import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import cl.duocuc.ecomarket.tipodatos.Genero;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import cl.duocuc.ecomarket.util.validacion.*;

public record UsuarioRequestDTO(

        String nombre,

        String correo,

        @Requerido
        @Contrasena
        String contrasenaHash,

        @FechaDB
        String fechaNacimiento,

        Genero genero,

        Integer idRol,

        @Telefono
        String telefono,

        @Rut
        String rutEmpleado,

        @FechaDB
        String fechaContratacion,

        String cargoEmpleado,

        String areaEmpleado
) implements PeticionEncriptableDTO<Usuario> {

        @Override
        public Usuario toEntidad(Encriptador<String> enc) {
                Usuario u = new Usuario();
                u.setNombreUsuario(nombre);
                u.setCorreoUsuario(correo);
                String passw;
                if (!enc.encriptado(contrasenaHash)) {
                        passw = enc.encriptar(contrasenaHash);
                } else {
                        passw = contrasenaHash;
                }
                u.setContrasenaHash(passw);
                u.setGenero(String.valueOf(genero.toChar()));
                u.setFechaNacimiento(fechaNacimiento);
                Rol r = new Rol();
                r.setId(idRol);
                u.setRol(r);
                return u;
        }

}
