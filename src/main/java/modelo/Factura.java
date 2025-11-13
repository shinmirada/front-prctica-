/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Dilan
 */
public class Factura {

    private String codigo;
    private double valor;
    private String usuarioDoc;
    private int pedidoId;
    private String hora;

    public Factura() {
    }

    public Factura(String codigo, double valor, String usuarioDoc, int pedidoId, String hora) {
        this.codigo = codigo;
        this.valor = valor;
        this.usuarioDoc = usuarioDoc;
        this.pedidoId = pedidoId;
        this.hora = hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUsuarioDoc() {
        return usuarioDoc;
    }

    public void setUsuarioDoc(String usuarioId) {
        this.usuarioDoc = usuarioId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
