
package modelo;

import enums.Estado;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDateTime fecha;
    private boolean esDomicilio;
    private Estado estado;
    private String clienteDoc;
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
    
    public String getClienteDoc() { return clienteDoc; }
    public void setClienteDoc(String clienteDoc) { this.clienteDoc = clienteDoc; }
    
    public List<ItemPedido> getItems() { return items; }
    public void setItems(List<ItemPedido> items) { this.items = items; }
}