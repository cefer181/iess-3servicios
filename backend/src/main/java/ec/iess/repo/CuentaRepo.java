package ec.iess.repo;

import ec.iess.domain.Cuenta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CuentaRepo implements PanacheRepository<Cuenta> {
    public Cuenta findByNumero(String numero) {
        return find("numero", numero).firstResult();
    }
}
