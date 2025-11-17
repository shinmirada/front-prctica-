package modelo;

import enums.Estado;

/**
 * DTO para actualizar el estado de un pedido
 * Este objeto se serializa a JSON: {"estado": "PREPARACION"}
 */
public class EstadoUpdateDTO {
    private Estado estado;

    // Constructor vacío (requerido por Gson)
    public EstadoUpdateDTO() {}

    // Constructor con parámetro
    public EstadoUpdateDTO(Estado estado) {
        this.estado = estado;
    }

    // Getters y Setters
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
