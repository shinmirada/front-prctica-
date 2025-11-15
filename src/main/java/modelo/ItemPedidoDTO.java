package modelo;

public class ItemPedidoDTO {
    private Integer platoId;
    private Integer cantidad;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(Integer platoId, Integer cantidad) {
        this.platoId = platoId;
        this.cantidad = cantidad;
    }


    public Integer getPlatoId() { return platoId; }
    public void setPlatoId(Integer platoId) { this.platoId = platoId; }
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}