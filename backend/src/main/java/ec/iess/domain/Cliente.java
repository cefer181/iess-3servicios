package ec.iess.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String identificacion;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 120)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado = true;

    // Relación 1..N con cuentas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Cuenta> cuentas = new ArrayList<>();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
    public List<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(List<Cuenta> cuentas) { this.cuentas = cuentas; }
}
