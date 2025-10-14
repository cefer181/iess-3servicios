package ec.iess.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoResponse {
    public Long id;
    public String tipo;
    public BigDecimal monto;
    public BigDecimal saldoPosterior;
    public LocalDateTime fecha;
}

