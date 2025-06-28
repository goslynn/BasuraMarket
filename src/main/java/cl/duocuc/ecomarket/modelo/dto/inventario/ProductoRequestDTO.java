package cl.duocuc.ecomarket.modelo.dto.inventario;

import java.math.BigDecimal;

public record ProductoRequestDTO(
        String CodigoProducto,
        String NombreProducto,
        String Descripcion,
        BigDecimal Precio,
        Long idSubFamilia,
        Byte Img
){}
