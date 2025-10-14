package ec.iess.repository;

import ec.iess.domain.Movimiento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovimientoRepository implements PanacheRepository<Movimiento> { }

