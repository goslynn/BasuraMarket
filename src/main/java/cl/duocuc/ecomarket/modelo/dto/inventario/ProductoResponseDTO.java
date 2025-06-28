package cl.duocuc.ecomarket.modelo.dto.inventario;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long idProducto,
        String CodigoProducto,
        String NombreProducto,
        String Descripcion,
        BigDecimal Precio,
        Long idSubFamilia,
        Boolean Activo,
        byte[] Img
){}
