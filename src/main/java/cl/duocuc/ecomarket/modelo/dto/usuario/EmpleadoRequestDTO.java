package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.tipodatos.Genero;
import cl.duocuc.ecomarket.util.validacion.FechaDB;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import cl.duocuc.ecomarket.util.validacion.Rut;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmpleadoRequestDTO(

        @Requerido
        String nombre,

        @Requerido
        @Email(message = "El correo no cumple con formato de correo valido")
        String correo,

        @FechaDB
        String fechaNacimiento,

        @Requerido
        String contrasena,

        Genero genero,

        @Rut
        String rut,

        @FechaDB
        String fechaContratacion,

        @Requerido
        String cargoEmpleado,

        String AreaEmpleado,

        @NotNull(message = "El id de rol no puede ser nulo")
        Integer idRolUsuario
) implements PeticionDTO<Usuario> {

        @Override
        public Usuario toEntidad() {
                return null;
        }

}
