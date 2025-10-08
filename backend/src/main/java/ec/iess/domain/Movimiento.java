package ec.iess.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @Column(nullable = false, length = 20)
    private String tipo; // CREDITO | DEBITO

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
