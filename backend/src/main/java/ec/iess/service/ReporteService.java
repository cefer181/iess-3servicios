package ec.iess.service;

import ec.iess.domain.Cuenta;
import ec.iess.domain.Movimiento;
import ec.iess.repo.CuentaRepo;
import ec.iess.repo.MovimientoRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
public class ReporteService {

    @Inject CuentaRepo cuentaRepo;
    @Inject MovimientoRepo movimientoRepo;

    public Map<String, Object> reporteCuenta(Long cuentaId, LocalDate desde, LocalDate hasta) {
        Cuenta c = cuentaRepo.findById(cuentaId);
        if (c == null) return Map.of("error","Cuenta no encontrada");

        LocalDateTime d = desde.atStartOfDay();
        LocalDateTime h = hasta.plusDays(1).atStartOfDay().minusSeconds(1);

        List<Movimiento> movs = movimientoRepo.porCuentaYFechas(cuentaId, d, h);

        BigDecimal creditos = movs.stream()
                .filter(m -> "CREDITO".equalsIgnoreCase(m.getTipo()))
                .map(Movimiento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal debitos = movs.stream()
                .filter(m -> "DEBITO".equalsIgnoreCase(m.getTipo()))
                .map(Movimiento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(
                "cuenta", c.getNumero(),
                "desde", desde.toString(),
                "hasta", hasta.toString(),
                "creditos", creditos,
                "debitos", debitos,
                "saldoActual", c.getSaldo(),
                "movimientos", movs
        );
    }
}
