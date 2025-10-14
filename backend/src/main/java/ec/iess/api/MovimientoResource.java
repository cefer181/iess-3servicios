package ec.iess.api;

import ec.iess.domain.Movimiento;
import ec.iess.repository.MovimientoRepository;
import ec.iess.service.MovimientoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.List;

@Path("/api/cuentas/{cuentaId}/movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimientoResource {

    @Inject MovimientoService movimientoService;
    @Inject MovimientoRepository movimientoRepository;

    public static class CrearReq {
        public String tipo;      // "CREDITO" | "DEBITO"
        public BigDecimal monto; // 18,2
    }

    @GET
    public List<Movimiento> listar(@PathParam("cuentaId") Long cuentaId) {
        return movimientoRepository.find("cuenta.id = ?1 ORDER BY fecha DESC, id DESC", cuentaId).list();
    }

    @POST
    @Transactional
    public Movimiento crear(@PathParam("cuentaId") Long cuentaId, CrearReq req) {
        if (req == null || req.tipo == null || req.monto == null) {
            throw new BadRequestException("tipo y monto son obligatorios");
        }
        return movimientoService.registrar(cuentaId, req.tipo, req.monto);
    }
}
