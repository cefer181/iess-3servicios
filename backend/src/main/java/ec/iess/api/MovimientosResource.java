package ec.iess.api;

import ec.iess.domain.Movimiento;
import ec.iess.service.MovimientoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientosResource {

    @Inject MovimientoService service;

    @POST
    public Response registrar(Movimiento m) {
        Movimiento r = service.registrar(m);
        return Response.status(Response.Status.CREATED).entity(r).build();
    }
}
