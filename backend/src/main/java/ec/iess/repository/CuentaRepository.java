package ec.iess.repository;

import ec.iess.domain.Cuenta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CuentaRepository implements PanacheRepository<Cuenta> { }

