package cl.duocuc.ecomarket.control.venta;


import cl.duocuc.ecomarket.funcional.RequierePermiso;
import cl.duocuc.ecomarket.modelo.dto.venta.PeticionVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.RespuestaVentaDTO;
import cl.duocuc.ecomarket.servicio.ServicioVenta;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.IdDescripcion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ventas", description = "Controlador para operaciones de venta publicas")
@RestController
@RequestMapping("/api/v1/public/venta")
public class PublicVentaController {
    private final ServicioVenta servicio;

    public PublicVentaController(ServicioVenta servicioVenta) {
        this.servicio = servicioVenta;
    }

    @Operation(
            summary = "Consultar venta por su ID",
            description = "Permite consultar una venta especifica por su ID. Requiere permiso de VER_VENTAS.")
    @Parameter(
            name = "id",
            description = "ID de la venta a consultar",
            required = true,
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Venta encontrada",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaVentaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada, no existe o esta marcada inactiva.",
            content = @Content()
    )
    @GetMapping("/{id}")
    @RequierePermiso(TipoPermiso.VER_VENTAS)
    public ResponseEntity<RespuestaVentaDTO> consultarVenta(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.obtenerVenta(id));
    }



    @Operation(
            summary = "Registrar una nueva venta",
            description = "Permite registrar una nueva venta. Requiere permiso de REGISTRAR_VENTAS."
    )
    @RequestBody(
            description = "Datos de la venta a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PeticionVentaDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Venta registrada exitosamente, devuelve el ID de la venta y una pequeña descripcion.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IdDescripcion.class)
            )
    )
    @PostMapping
    public ResponseEntity<CodigoDescripcion<Integer, String>> registrarVenta(@Valid @RequestBody PeticionVentaDTO venta){
        return ResponseEntity.ok(servicio.registrarVenta(venta));
    }
}
