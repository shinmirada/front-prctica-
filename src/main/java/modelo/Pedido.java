/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import enums.Estado;

public class Pedido {
    private int id;
    private String clienteDoc;
    private boolean esDomicilio;
    private Estado estado;
    private String nombrePlato;

    public Pedido() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClienteDoc() { return clienteDoc; }
    public void setClienteDoc(String clienteDoc) { this.clienteDoc = clienteDoc; }

    public boolean isEsDomicilio() { return esDomicilio; }
    public void setEsDomicilio(boolean esDomicilio) { this.esDomicilio = esDomicilio; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public String getNombrePlato() { return nombrePlato; }
    public void setNombrePlato(String nombrePlato) { this.nombrePlato = nombrePlato; }
}
