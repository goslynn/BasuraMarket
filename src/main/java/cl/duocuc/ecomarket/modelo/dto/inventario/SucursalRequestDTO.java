package cl.duocuc.ecomarket.modelo.dto.inventario;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.Size;

public record SucursalRequestDTO(
        @Requerido  @Size(max = 100) String nombreSucursal
){}
