package ec.iess.api;

import ec.iess.domain.Cliente;
import ec.iess.repo.ClienteRepo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientesResource {

    @Inject ClienteRepo repo;

    @GET
    public List<Cliente> list() { return repo.listAll(); }

    @GET @Path("/{id}")
    public Cliente byId(@PathParam("id") Long id) {
        Cliente c = repo.findById(id);
        if (c == null) throw new NotFoundException();
        return c;
    }

    @POST @Transactional
    public Response create(Cliente c) {
        repo.persist(c);
        return Response.status(Response.Status.CREATED).entity(c).build();
    }

    @PUT @Path("/{id}") @Transactional
    public Cliente update(@PathParam("id") Long id, Cliente in) {
        Cliente c = repo.findById(id);
        if (c == null) throw new NotFoundException();
        c.setIdentificacion(in.getIdentificacion());
        c.setNombre(in.getNombre());
        c.setContrasena(in.getContrasena());
        c.setEstado(in.getEstado());
        return c;
    }

    @DELETE @Path("/{id}") @Transactional
    public void delete(@PathParam("id") Long id) {
        if (!repo.deleteById(id)) throw new NotFoundException();
    }
}
