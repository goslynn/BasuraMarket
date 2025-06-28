package cl.duocuc.ecomarket.modelo.dto.inventario;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

public record BodegaRequestDTO(
        @Requerido String nombreBodega,
        @NotNull Long idSucursal
) {}
