package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;

import java.util.List;
import java.util.stream.Collectors;

public record PeticionVentaDTO(
        Integer idCliente,
        Integer idFormaPago,
        String observaciones,
        List<DetalleVentaDTO> items
) implements PeticionDTO<Venta> {

    @Override
    public Venta toEntidad() {
        Venta venta = new Venta();
        venta.setIdCliente(idCliente);
        venta.setIdFormaPago(idFormaPago);
        venta.setObservaciones(observaciones);
        venta.setDetalleVentas(items.stream().map(DetalleVentaDTO::toEntidad).collect(Collectors.toSet()));
        return venta;
    }

}
