package ec.iess.service;

import ec.iess.domain.Cuenta;
import ec.iess.domain.Movimiento;
import ec.iess.repo.CuentaRepo;
import ec.iess.repo.MovimientoRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

@ApplicationScoped
public class MovimientoService {

    @Inject CuentaRepo cuentaRepo;
    @Inject MovimientoRepo movimientoRepo;

    @Transactional
    public Movimiento registrar(Movimiento m) {
        // Cargar cuenta actualizada
        Cuenta c = cuentaRepo.findById(m.getCuenta().getId());
        if (c == null || !Boolean.TRUE.equals(c.getEstado())) {
            throw new WebApplicationException("Cuenta no válida o inactiva", Response.Status.BAD_REQUEST);
        }

        BigDecimal saldo = c.getSaldo() == null ? BigDecimal.ZERO : c.getSaldo();
        BigDecimal valor = m.getValor();

        if ("DEBITO".equalsIgnoreCase(m.getTipo())) {
            BigDecimal nuevo = saldo.subtract(valor);
            // Regla: no permitir saldo negativo
            if (nuevo.compareTo(BigDecimal.ZERO) < 0) {
                throw new WebApplicationException("Saldo no disponible", 422);
            }
            c.setSaldo(nuevo);
        } else if ("CREDITO".equalsIgnoreCase(m.getTipo())) {
            c.setSaldo(saldo.add(valor));
        } else {
            throw new WebApplicationException("Tipo de movimiento inválido", Response.Status.BAD_REQUEST);
        }

        // Persistir ambos (cuenta y movimiento)
        movimientoRepo.persist(m);
        // Panache sincroniza al finalizar la transacción
        return m;
    }
}
