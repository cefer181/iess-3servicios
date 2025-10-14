package ec.iess.service;

import ec.iess.domain.Cliente;
import ec.iess.domain.Cuenta;
import ec.iess.domain.Movimiento;
import ec.iess.repository.ClienteRepository;
import ec.iess.repository.MovimientoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ReporteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    MovimientoRepository movimientoRepository;

    public Map<String, Object> generarReporte(Long clienteId, LocalDateTime desde, LocalDateTime hasta) {
        Cliente cliente = clienteRepository.findById(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("cliente", cliente.getNombre());

        BigDecimal totalCreditos = BigDecimal.ZERO;
        BigDecimal totalDebitos = BigDecimal.ZERO;

        for (Cuenta cuenta : cliente.getCuentas()) {
            List<Movimiento> movimientos = movimientoRepository
                    .find("cuenta.id = ?1 and fecha between ?2 and ?3 ORDER BY fecha", cuenta.getId(), desde, hasta)
                    .list();

            BigDecimal creditos = movimientos.stream()
                    .filter(m -> "CREDITO".equalsIgnoreCase(m.getTipo()))
                    .map(Movimiento::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal debitos = movimientos.stream()
                    .filter(m -> "DEBITO".equalsIgnoreCase(m.getTipo()))
                    .map(Movimiento::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            totalCreditos = totalCreditos.add(creditos);
            totalDebitos = totalDebitos.add(debitos);

            Map<String, Object> cuentaResumen = new HashMap<>();
            cuentaResumen.put("numero", cuenta.getNumero());
            cuentaResumen.put("saldoActual", cuenta.getSaldo());
            cuentaResumen.put("creditos", creditos);
            cuentaResumen.put("debitos", debitos);
            cuentaResumen.put("movimientos", movimientos);

            resultado.put("cuenta_" + cuenta.getNumero(), cuentaResumen);
        }

        resultado.put("totalCreditos", totalCreditos);
        resultado.put("totalDebitos", totalDebitos);

        return resultado;
    }
}

