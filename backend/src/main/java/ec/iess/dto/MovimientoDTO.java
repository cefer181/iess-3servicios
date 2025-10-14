package ec.iess.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoDTO {
    // Request para crear
    public static class Crear {
        public String tipo;       // "CREDITO" | "DEBITO"
        public BigDecimal monto;  // Debe venir positivo
    }
    // Response para listar/crear
    public static class Ver {
        public Long id;
        public LocalDateTime fecha;
        public String tipo;
        public BigDecimal monto;  // Con signo
        public BigDecimal saldo;

        public Ver(Long id, LocalDateTime fecha, String tipo, BigDecimal monto, BigDecimal saldo) {
            this.id = id;
            this.fecha = fecha;
            this.tipo = tipo;
            this.monto = monto;
            this.saldo = saldo;
        }
    }
}

