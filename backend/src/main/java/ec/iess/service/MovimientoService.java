package ec.iess.service;

import ec.iess.domain.Cuenta;
import ec.iess.domain.Movimiento;
import ec.iess.repository.CuentaRepository;
import ec.iess.repository.MovimientoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@ApplicationScoped
public class MovimientoService {

    @Inject
    MovimientoRepository movimientoRepository;

    @Inject
    CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrar(Long cuentaId, String tipo, BigDecimal monto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId);
        if (cuenta == null) {
            throw new WebApplicationException("Cuenta no encontrada", 404);
        }

        BigDecimal delta = "CREDITO".equalsIgnoreCase(tipo)
                ? monto
                : monto.negate();

        BigDecimal nuevoSaldo = cuenta.getSaldo().add(delta);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new WebApplicationException("Saldo no disponible", 400);
        }

        Movimiento m = new Movimiento();
        m.setCuenta(cuenta);
        m.setTipo("CREDITO".equalsIgnoreCase(tipo) ? "CREDITO" : "DEBITO");
        m.setMonto(monto);
        m.setSaldoPosterior(nuevoSaldo);
        m.setFecha(OffsetDateTime.now());

        movimientoRepository.persist(m);
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.persist(cuenta);

        return m;
    }
}

