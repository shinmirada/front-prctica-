package modelo;

import java.time.LocalDateTime;

public class Factura {
    private Long facturaid;  // ← Debe ser Long (ID en el backend es Long)
    private double total;
    private LocalDateTime fecha;  // ← CAMBIAR: De String a LocalDateTime
    private Pedido pedido;        // ← CAMBIAR: De int a Pedido completo
    private Usuario usuario;      // ← CAMBIAR: De String a Usuario completo

    public Factura() {}

    public Factura(Long facturaid, double total, LocalDateTime fecha, Pedido pedido, Usuario usuario) {
        this.facturaid = facturaid;
        this.total = total;
        this.fecha = fecha;
        this.pedido = pedido;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getFacturaid() {
        return facturaid;
    }

    public void setFacturaid(Long facturaid) {
        this.facturaid = facturaid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // ✅ AGREGAR: Métodos helper para retrocompatibilidad
    public int getPedidoId() {
        return pedido != null ? pedido.getId() : 0;
    }

    public String getUsuarioDoc() {
        return usuario != null ? usuario.getDocumento() : null;
    }
}