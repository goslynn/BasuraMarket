package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotNull;

public record InventarioRequestDTO(
        @NotNull Integer idBodega,
        @NotNull Integer stockActual,
        @NotNull Integer stockMinimo,
        @NotNull Integer stockMaximo,
        @NotNull Long idProducto
) {}