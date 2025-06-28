package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Sucursal;

public class SucursalMapper {

    public static SucursalResponseDTO toResponseDTO(Sucursal sucursal) {
        return new SucursalResponseDTO(
                sucursal.getId(),
                sucursal.getNombreSucursal(),
                sucursal.getActivo()
        );
    }
    public static Sucursal toEntity(SucursalRequestDTO dto){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombreSucursal(dto.nombreSucursal());
        sucursal.setActivo(true);
        return sucursal;
    }
}
