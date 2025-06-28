package cl.duocuc.ecomarket.funcional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CalculaMonto {

    private static final BigDecimal IVA = new BigDecimal("0.19");
    private static final Logger log = LoggerFactory.getLogger(CalculaMonto.class);

    public Resultado calcular(List<LineaDetalle> lineasDetalle){
        BigDecimal totalNeto = BigDecimal.ZERO;

        for (LineaDetalle linea : lineasDetalle) {
            totalNeto = totalNeto.add(calcularLinea(linea));
        }
        log.info("Total neto -> {}", totalNeto);

        BigDecimal totalIva = totalNeto.multiply(IVA).setScale(2, RoundingMode.HALF_UP);
        log.info("Total iva -> {}", totalIva);

        BigDecimal totalBruto = totalNeto.add(totalIva).setScale(2, RoundingMode.HALF_UP);
        log.info("Total bruto -> {}", totalBruto);

        return new Resultado(
                totalNeto.setScale(2, RoundingMode.HALF_UP),
                totalBruto,
                totalIva
        );
    }

    private BigDecimal calcularLinea(LineaDetalle linea){
        BigDecimal res = linea.getPrecioUnitario().multiply(BigDecimal.valueOf(linea.getCantidad()));
        log.info("linea -> {} * {} = {}", linea.getPrecioUnitario(), linea.getCantidad(), res);
        return res;
    }


    public record Resultado(
        BigDecimal subtotal,
        BigDecimal totalIva,
        BigDecimal total
    ){}
}
