package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.CalculaMonto;
import cl.duocuc.ecomarket.modelo.dto.venta.DetalleVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.PeticionVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.RespuestaVentaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;
import cl.duocuc.ecomarket.modelo.repository.DetalleVentaRepository;
import cl.duocuc.ecomarket.modelo.repository.VentaRepository;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.funcional.LineaDetalle;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioVenta {
    private final VentaRepository ventaRepo;
    private final DetalleVentaRepository detalleRepo;
    private final CalculaMonto calculaMonto;
//    private final ServicioInventario inventario;

    public ServicioVenta(VentaRepository venaRepo, DetalleVentaRepository detalleRepo, CalculaMonto calculaMonto) {
        this.ventaRepo = venaRepo;
        this.detalleRepo = detalleRepo;
        this.calculaMonto = calculaMonto;
    }

    public RespuestaVentaDTO obtenerVenta(Integer id) throws ApiException {
        Venta v = ventaRepo.findById(id).filter(Venta::getActivo).orElseThrow(
                () -> new ApiException(404, String.format("La venta con ID %d no existe", id))
        );
        return RespuestaVentaDTO.fromEntidad(v);
    }

    public CodigoDescripcion<Integer, String> registrarVenta(PeticionVentaDTO venta) {
        final Venta registroVenta = venta.toEntidad();
        final List<DetalleVenta> registroDetalle = new ArrayList<>();

        registroVenta.setIdTipoDocumento(4); //Factura
        registroVenta.setIdEstadoVenta(6); //Pagada

        CalculaMonto.Resultado r = delegarCalculoMontos(venta.items());

        registroVenta.setTotalNeto(r.subtotal());
        registroVenta.setTotalBruto(r.total());
        registroVenta.setTotalIva(r.totalIva());

        ventaRepo.save(registroVenta);

        venta.items().forEach(item ->{
           DetalleVenta det = item.toEntidad();
           det.setIdVenta(registroVenta);
           registroDetalle.add(det);
        });

        detalleRepo.saveAll(registroDetalle);

        return RespuestaVentaDTO.fromEntidad(registroVenta);
    }

    public CalculaMonto.Resultado delegarCalculoMontos(List<DetalleVentaDTO> items) {
        List<LineaDetalle> lineas = items.stream().map(item -> new LineaDetalle() {
            @Override
            public Long getCantidad() {
                return item.cantidad();
            }

            @Override
            public BigDecimal getPrecioUnitario() {
                return item.precioUnitario();
            }
        }).collect(Collectors.toList());

        return calculaMonto.calcular(lineas);
    }





}
