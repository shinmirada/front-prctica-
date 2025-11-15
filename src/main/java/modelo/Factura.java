package modelo;

public class Factura {
    private String codigo;
    private double valor;
    private String fecha;
    private String usuarioDoc;
    private int pedidoId;

    public Factura() {}

    public Factura(String codigo, double valor, String fecha, String usuarioDoc, int pedidoId) {
        this.codigo = codigo;
        this.valor = valor;
        this.fecha = fecha;
        this.usuarioDoc = usuarioDoc;
        this.pedidoId = pedidoId;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    
    public String getUsuarioDoc() { return usuarioDoc; }
    public void setUsuarioDoc(String usuarioDoc) { this.usuarioDoc = usuarioDoc; }
    
    public int getPedidoId() { return pedidoId; }
    public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }
}