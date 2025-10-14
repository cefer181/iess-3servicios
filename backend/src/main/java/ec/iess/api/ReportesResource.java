package ec.iess.api;

import ec.iess.service.ReporteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Path("/api/reportes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportesResource {

    @Inject
    ReporteService service;

    @GET
    public Map<String, Object> reporte(
            @QueryParam("clienteId") Long clienteId,
            @QueryParam("desde") String desde,   // formato: yyyy-MM-dd
            @QueryParam("hasta") String hasta) { // formato: yyyy-MM-dd

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime f1 = LocalDateTime.parse(desde + " 00:00:00", fmt);
        LocalDateTime f2 = LocalDateTime.parse(hasta + " 23:59:59", fmt);

        return service.generarReporte(clienteId, f1, f2);
    }
}

