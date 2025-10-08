package ec.iess.repo;

import ec.iess.domain.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepo implements PanacheRepository<Cliente> {
    public Cliente findByIdentificacion(String id) {
        return find("identificacion", id).firstResult();
    }
}
