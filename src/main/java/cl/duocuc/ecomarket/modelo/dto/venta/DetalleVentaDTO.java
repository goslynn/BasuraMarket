package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;

import java.math.BigDecimal;

//TODO: Mejora, precioUnitario opcional, si es null usar el de la base de datos.
public record DetalleVentaDTO(
        Integer idProducto,
        Long cantidad,
        BigDecimal precioUnitario
) implements RespuestaDTO, PeticionDTO<DetalleVenta> {

    public static DetalleVentaDTO fromEntidad(DetalleVenta entidad) {
        return new DetalleVentaDTO(
                Math.toIntExact(entidad.getIdProducto()),
                entidad.getCantidad(),
                entidad.getPrecioUnitario()
        );
    }

    @Override
    public DetalleVenta toEntidad() {
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setIdProducto(Long.valueOf(idProducto));
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setPrecioUnitario(precioUnitario);
        return detalleVenta;
    }

    @Override
    public Integer getCodigo() {
        return idProducto;
    }

    @Override
    public String getDescripcion() {
        return String.format("PRODUCTO_%d -> %d x %.2f",
                idProducto, cantidad, precioUnitario);
    }

}
