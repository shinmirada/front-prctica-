package modelo;

public class Plato {

    private Integer id;  // ✅ IMPORTANTE: Puede ser null
    private String nombre;
    private String descripcion;
    private double precio;

    public Plato() {
        // ✅ NO inicializar id aquí, dejarlo null
    }

    public Plato(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    // ✅ Constructor SIN id para crear platos nuevos
    public Plato(String nombre, String descripcion, double precio) {
        this.id = null;  // Explícitamente null
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}