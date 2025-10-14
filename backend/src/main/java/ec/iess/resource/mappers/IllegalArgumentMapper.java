package ec.iess.resource.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class IllegalArgumentMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException ex) {
        return Response.status(400).entity(Map.of("error", ex.getMessage())).build();
    }
}

