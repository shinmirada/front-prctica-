package modelo;

import enums.Estado;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDateTime fecha;
    private boolean esDomicilio;
    private Estado estado;
    private Usuario cliente;  // ← CAMBIAR: Ya no es String, ahora es Usuario completo
    private List<ItemPedido> items; 
    
    public Pedido() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public boolean isEsDomicilio() { return esDomicilio; }
    public void setEsDomicilio(boolean esDomicilio) { this.esDomicilio = esDomicilio; }
    
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    
    // ✅ NUEVO: Ahora es Usuario completo
    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }
    
    // ✅ AGREGAR: Método helper para obtener solo el documento (retrocompatibilidad)
    public String getClienteDoc() { 
        return cliente != null ? cliente.getDocumento() : null; 
    }
    
    public List<ItemPedido> getItems() { return items; }
    public void setItems(List<ItemPedido> items) { this.items = items; }
}