package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Bodega;
import cl.duocuc.ecomarket.modelo.entity.inventario.Sucursal;

public class BodegaMapper {

    public static BodegaResponseDTO toResponseDTO(Bodega bodega) {
        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombreBodega(),
                bodega.getActiva(),
                bodega.getIdSucursal().getId()
        );
    }

    public static Bodega toEntity(BodegaRequestDTO dto) {
        Bodega bodega = new Bodega();
        bodega.setNombreBodega(dto.nombreBodega());
        bodega.setActiva(true); // Nuevas bodegas son activas por defecto

        // Asignar sucursal usando solo el ID
        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.idSucursal());
        bodega.setIdSucursal(sucursal);

        return bodega;
    }
}

