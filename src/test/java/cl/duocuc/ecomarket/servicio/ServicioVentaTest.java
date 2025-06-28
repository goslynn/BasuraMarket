package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.CalculaMonto;
import cl.duocuc.ecomarket.modelo.dto.venta.DetalleVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.PeticionVentaDTO;
import cl.duocuc.ecomarket.modelo.repository.DetalleVentaRepository;
import cl.duocuc.ecomarket.modelo.repository.VentaRepository;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
class ServicioVentaTest {

    @Mock
    private VentaRepository ventaRepo;

    @Mock
    private DetalleVentaRepository detalleRepo;

    @Mock
    private CalculaMonto calculaMonto;

    @InjectMocks
    private ServicioVenta servicioVenta;

    /**
     * Se espera que cree una venta, con registro de encabezado y detalle de venta
     * calculando correctamente los montos.
     */
    @Test
    void registrarVentaTest() {
        PeticionVentaDTO peticion = getPeticionVentaDTO();

        final CalculaMonto.Resultado res = new CalculaMonto.Resultado(
                new BigDecimal(9950),
                new BigDecimal("1890.50"),
                new BigDecimal("11840.50")
        );

        calculaMonto = Mockito.mock(CalculaMonto.class);
        Mockito.when(servicioVenta.delegarCalculoMontos(anyList())).thenReturn(res);


        CodigoDescripcion<Integer, String> respuesta = servicioVenta.registrarVenta(peticion);

        assertNotNull(respuesta);
        assertNotNull(ventaRepo.findById(respuesta.getCodigo()));
        assertNotNull(respuesta.getDescripcion());
    }

    private static PeticionVentaDTO getPeticionVentaDTO() {
        final BigDecimal precioUnitario = BigDecimal.valueOf(1990);
        return new PeticionVentaDTO(
                4,
                5,
                "test venta",
                List.of(
                        new DetalleVentaDTO(
                                6,
                                2L,
                                precioUnitario.multiply(BigDecimal.valueOf(2L))
                        ),
                        new DetalleVentaDTO(
                                6,
                                3L,
                                precioUnitario.multiply(BigDecimal.valueOf(3L))
                        )
                ));
    }

}
