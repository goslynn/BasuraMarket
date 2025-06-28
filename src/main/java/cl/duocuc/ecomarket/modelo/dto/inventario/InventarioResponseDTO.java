package cl.duocuc.ecomarket.modelo.dto.inventario;

public record InventarioResponseDTO(
        Long id,
        String nombreProducto,
        Integer stockActual,
        Integer stockMinimo,
        Integer stockMaximo,
        Integer idBodega,
        Boolean activo
) {}