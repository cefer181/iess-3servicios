package ec.iess.api;

import ec.iess.domain.Cliente;
import ec.iess.domain.Cuenta;
import ec.iess.repo.ClienteRepo;
import ec.iess.repo.CuentaRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/cuentas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CuentasResource {

    @Inject CuentaRepo cuentaRepo;
    @Inject ClienteRepo clienteRepo;

    @GET
    public List<Cuenta> list() { return cuentaRepo.listAll(); }

    @POST @Transactional
    public Response create(Cuenta c) {
        // valida cliente
        Long clienteId = c.getCliente() != null ? c.getCliente().getId() : null;
        if (clienteId == null || clienteRepo.findById(clienteId) == null) {
            throw new BadRequestException("Cliente inexistente");
        }
        cuentaRepo.persist(c);
        return Response.status(Response.Status.CREATED).entity(c).build();
    }
}
