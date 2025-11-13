/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import enums.Rol;

/**
 *
 * @author Dilan
 */
public class Usuario {

    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;
    private String usuario;
    private String contraseña;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String nombre, String documento, String telefono, String direccion, String usuario,
            String contraseña, Rol rol) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
