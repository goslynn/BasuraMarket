package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Familia;
import cl.duocuc.ecomarket.modelo.entity.inventario.Subfamilia;

public class SubFamiliaMapper {

    public static SubFamiliaResponseDTO toResponseDTO(Subfamilia subfamilia) {
        return new SubFamiliaResponseDTO(
                subfamilia.getId(),
                subfamilia.getIdFamilia().getId(),
                subfamilia.getNombreSubfamilia(),
                subfamilia.getDescripcion(),
                subfamilia.getActivo()
        );
    }

    public static Subfamilia toEntity(SubFamiliaRequestDTO dto) {
        Subfamilia subfamilia = new Subfamilia();

        // Crear un objeto Familia solo con el ID
        Familia familia = new Familia();
        familia.setId(dto.id_familia());
        subfamilia.setIdFamilia(familia);

        subfamilia.setNombreSubfamilia(dto.nombre_subfamilia());
        subfamilia.setDescripcion(dto.descripcion());
        subfamilia.setActivo(true);
        return subfamilia;
    }
}

