package ec.iess.api;

import ec.iess.service.ReporteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Map;

@Path("/api/reportes")
@Produces(MediaType.APPLICATION_JSON)
public class ReportesResource {

    @Inject ReporteService service;

    // GET /api/reportes?cuentaId=1&desde=2025-01-01&hasta=2025-12-31
    @GET
    public Map<String,Object> reporte(@QueryParam("cuentaId") Long cuentaId,
                                      @QueryParam("desde") String desde,
                                      @QueryParam("hasta") String hasta) {
        if (cuentaId == null || desde == null || hasta == null) {
            throw new BadRequestException("cuentaId, desde y hasta son requeridos");
        }
        return service.reporteCuenta(cuentaId, LocalDate.parse(desde), LocalDate.parse(hasta));
    }
}
