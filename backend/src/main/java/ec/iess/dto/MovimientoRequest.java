package ec.iess.dto;

import java.math.BigDecimal;

public class MovimientoRequest {
    public String tipo;      // "CREDITO" | "DEBITO"
    public BigDecimal monto; // > 0
}

