package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;
import cl.duocuc.ecomarket.util.CodigoDescripcion;

import java.math.BigDecimal;

public record RespuestaVentaDTO(
        Integer idVenta,
        Integer idEstadoVenta,
        Integer idTipoDocumento,
        BigDecimal subTotal,
        BigDecimal totalIva,
        BigDecimal total,
        PeticionVentaDTO venta,
        String fechaCreacion
) implements RespuestaDTO {

    public static RespuestaVentaDTO fromEntidad(Venta entidad){
        return new RespuestaVentaDTO(
                entidad.getId(),
                entidad.getIdEstadoVenta(),
                entidad.getIdTipoDocumento(),
                entidad.getTotalNeto(),
                entidad.getTotalIva(),
                entidad.getTotalBruto(),
                new PeticionVentaDTO(
                        entidad.getIdCliente(),
                        entidad.getIdFormaPago(),
                        entidad.getObservaciones(),
                        entidad.getDetalleVentas().stream().map(DetalleVentaDTO::fromEntidad).toList()
                ),
                entidad.getFechaCreacion() == null ? "null" : entidad.getFechaCreacion().toString()
        );
    }


    @Override
    public Integer getCodigo() {
        return idVenta();
    }

    @Override
    public String getDescripcion() {
        return String.format("Venta E-%d [ SII-%d ]", idEstadoVenta(), idTipoDocumento());
    }
}
