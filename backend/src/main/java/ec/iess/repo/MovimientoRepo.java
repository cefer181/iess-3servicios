package ec.iess.repo;

import ec.iess.domain.Movimiento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MovimientoRepo implements PanacheRepository<Movimiento> {
    public List<Movimiento> porCuentaYFechas(Long cuentaId, LocalDateTime desde, LocalDateTime hasta) {
        return find("cuenta.id = ?1 and fecha between ?2 and ?3 order by fecha", cuentaId, desde, hasta).list();
    }
}
