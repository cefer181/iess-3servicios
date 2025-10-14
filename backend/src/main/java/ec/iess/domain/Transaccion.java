package ec.iess.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(nullable=false, length=10)
    private String tipo; // CREDITO | DEBITO

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal monto;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal saldoDisponible; // saldo de la cuenta DESPUÉS de aplicar el movimiento

    @ManyToOne(optional=false)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    // getters/setters
    public Long getId() { return id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public BigDecimal getSaldoDisponible() { return saldoDisponible; }
    public void setSaldoDisponible(BigDecimal saldoDisponible) { this.saldoDisponible = saldoDisponible; }
    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
}

