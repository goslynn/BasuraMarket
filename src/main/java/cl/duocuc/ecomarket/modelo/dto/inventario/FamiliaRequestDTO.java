package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FamiliaRequestDTO(
        @NotBlank @Size(max = 100) String nombre_familia,
        @NotBlank @Size(max = 100) String descripcion

){}
