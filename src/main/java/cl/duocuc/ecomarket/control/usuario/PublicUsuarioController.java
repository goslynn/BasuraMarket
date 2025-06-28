package cl.duocuc.ecomarket.control.usuario;


import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Controlador para operaciones públicas relacionadas con usuarios y roles")
@RestController
@RequestMapping("/api/v1/public/user")
public class PublicUsuarioController {
    private final ServicioUsuarios service;
    private static final Logger log = LoggerFactory.getLogger(PublicUsuarioController.class);

    public PublicUsuarioController(ServicioUsuarios service){
        this.service = service;
    }

    @Operation(
            summary = "Consultar usuario por su ID",
            description = "Permite consultar un usuario específico por su ID. Devuelve los detalles del usuario si existe."
    )
    @Parameter(
            name = "id",
            description = "ID del usuario a consultar",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content()
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> consultarUsuario(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerUsuario(id));
    }

    @Operation(
            summary = "Registrar un nuevo usuario (cliente)",
            description = "Permite registrar un cliente nuevo proporcionando los datos necesarios."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del cliente a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ClienteRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Cliente registrado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)
            )
    )
    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody ClienteRequestDTO usuario){
        return ResponseEntity.status(201).body(service.registrar(usuario));
    }

    @Operation(
            summary = "Actualizar un usuario existente",
            description = "Permite actualizar la información de un usuario existente mediante su ID."
    )
    @Parameter(
            name = "id",
            description = "ID del usuario a actualizar",
            required = true,
            example = "1"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del usuario a actualizar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario actualizado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CodigoDescripcion.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content()
    )
    @PutMapping("/{id}")
    public ResponseEntity<CodigoDescripcion<Integer, String>> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuario) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }

    @Operation(
            summary = "Eliminar un usuario existente",
            description = "Permite eliminar (desactivar) un usuario existente mediante su ID."
    )
    @Parameter(
            name = "id",
            description = "ID del usuario a eliminar",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Usuario eliminado exitosamente"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content()
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id){
        service.desactivarUsuario(id);
        return ResponseEntity.status(204).body(String.format("Usuario %d eliminado correctamente", id));
    }

    @Operation(
            summary = "Crear un nuevo rol",
            description = "Permite la creación de un nuevo rol junto con sus permisos correspondientes."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del rol y sus permisos",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolPermisosRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Rol creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolDTO.class)
            )
    )
    @PostMapping("/rol")
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolPermisosRequestDTO dto) {
        return ResponseEntity.status(201).body(service.crearRol(dto));
    }

    @Operation(
            summary = "Consultar rol por su ID",
            description = "Permite consultar un rol específico por su ID."
    )
    @Parameter(
            name = "id",
            description = "ID del rol a consultar",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Rol encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content()
    )
    @GetMapping("/rol/{id}")
    public ResponseEntity<RolDTO> consultarRol(@PathVariable Integer id)  {
        return ResponseEntity.ok(service.obtenerRol(id));
    }

    @Operation(
            summary = "Consultar todos los roles activos",
            description = "Devuelve una lista de todos los roles activos disponibles en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de roles activos",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolDTO.class)
            )
    )
    @GetMapping("/rol")
    public ResponseEntity<List<RolDTO>> consultarRoles() {
        return ResponseEntity.ok(service.obtenerRoles());
    }

    @Operation(
            summary = "Actualizar un rol existente",
            description = "Permite actualizar los detalles de un rol existente, incluyendo sus permisos."
    )
    @Parameter(
            name = "id",
            description = "ID del rol a actualizar",
            required = true,
            example = "1"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del rol a actualizar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolPermisosRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Rol actualizado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CodigoDescripcion.class)
            )
    )
    @PutMapping("/rol/{id}")
    public ResponseEntity<CodigoDescripcion<Integer, String>> actualizarRol(@PathVariable Integer id, @Valid @RequestBody RolPermisosRequestDTO rol) {
        return ResponseEntity.ok(service.actualizarRol(id, rol));
    }

    @Operation(
            summary = "Eliminar un rol existente",
            description = "Permite eliminar (desactivar) un rol existente mediante su ID."
    )
    @Parameter(
            name = "id",
            description = "ID del rol a eliminar",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Rol eliminado exitosamente"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content()
    )
    @DeleteMapping("/rol/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Integer id)  {
        service.eliminarRol(id);
        return ResponseEntity.status(204).body(String.format("Rol %d eliminado correctamente", id));
    }

    @Operation(
            summary = "Consultar todos los permisos disponibles",
            description = "Devuelve una lista de todos los permisos activos registrados en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de permisos activos",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PermisoResponseDTO.class)
            )
    )
    @GetMapping("/permiso")
    public ResponseEntity<List<PermisoResponseDTO>> consultarPermisos(){
        return ResponseEntity.ok(service.obtenerPermisos());
    }

    @Operation(
            summary = "Consultar permisos asociados a un rol",
            description = "Devuelve los permisos asociados a un rol específico identificado por su ID."
    )
    @Parameter(
            name = "id",
            description = "ID del rol a consultar permisos",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Permisos encontrados para el rol especificado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RolPermisosResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Rol no encontrado",
            content = @Content()
    )
    @GetMapping("/rol/{id}/permiso")
    public ResponseEntity<RolPermisosResponseDTO> consultarPermisosRol(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerRolPermisos(id));
    }
}