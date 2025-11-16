package modelo;

public class Factura {
    private Long facturaid;
    private double total;
    private String fecha;
    private String usuarioDoc;
    private int pedidoId;

    public Factura() {}

   
    
    public Factura(Long facturaid, double total, String fecha, String usuarioDoc, int pedidoId) {

		this.facturaid = facturaid;
		this.total = total;
		this.fecha = fecha;
		this.usuarioDoc = usuarioDoc;
		this.pedidoId = pedidoId;
	}



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



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getUsuarioDoc() {
		return usuarioDoc;
	}



	public void setUsuarioDoc(String usuarioDoc) {
		this.usuarioDoc = usuarioDoc;
	}



	public int getPedidoId() {
		return pedidoId;
	}



	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}



}