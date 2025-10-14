package ec.iess.resource.mappers;

import ec.iess.service.SaldoInsuficienteException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class SaldoInsuficienteMapper implements ExceptionMapper<SaldoInsuficienteException> {
    @Override
    public Response toResponse(SaldoInsuficienteException ex) {
        return Response.status(409).entity(Map.of("error", ex.getMessage())).build();
    }
}

