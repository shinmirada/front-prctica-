package modelo;

public class ItemPedido {
    private int id;
    private int cantidad;
    private double precioUnitario;
    private Plato plato; // ← El plato completo
    
    public ItemPedido() {}
    
    public ItemPedido(int id, int cantidad, double precioUnitario, Plato plato) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.plato = plato;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    
    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }
}