package cl.duocuc.ecomarket.modelo.dto.inventario;

public record SubFamiliaResponseDTO(
        Long id_subfamilia,
        Long id_familia,
        String nombre_subfamilia,
        String descripcion,
        boolean activo
){}
