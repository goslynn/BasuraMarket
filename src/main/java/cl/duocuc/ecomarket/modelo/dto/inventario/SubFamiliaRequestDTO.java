package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubFamiliaRequestDTO(
        @NotNull Long id_familia,
        @NotBlank @Size(max = 100) String nombre_subfamilia,
        @NotBlank @Size(max = 100) String descripcion

){}
