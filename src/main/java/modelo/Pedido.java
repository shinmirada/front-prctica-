/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import enums.Estado;

/**
 *
 * @author alexi
 */
public class Pedido {

    private int id;
    private String clienteDoc;
    private int idPlato;
    private String nombrePlato; 
    private boolean esDomicilio;
    private Estado estado;
    
    
	public Pedido() {
	}


	public Pedido(int id, String clienteDoc, int idPlato, String nombrePlato, boolean esDomicilio, Estado estado) {
		this.id = id;
		this.clienteDoc = clienteDoc;
		this.idPlato = idPlato;
		this.nombrePlato = nombrePlato;
		this.esDomicilio = esDomicilio;
		this.estado = estado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getClienteDoc() {
		return clienteDoc;
	}


	public void setClienteDoc(String clienteDoc) {
		this.clienteDoc = clienteDoc;
	}


	public int getIdPlato() {
		return idPlato;
	}


	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}


	public String getNombrePlato() {
		return nombrePlato;
	}


	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}


	public boolean isEsDomicilio() {
		return esDomicilio;
	}


	public void setEsDomicilio(boolean esDomicilio) {
		this.esDomicilio = esDomicilio;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}   
    
}
