package Clases;

public class Producto {

    private int idProducto;
    private String codigo;
    private String descripcion;
    private double precio;
    private int cantidad;

    public Producto(int idProducto, String codigo, String descripcion,
                    double precio, int cantidad) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // --- Getters ---
    public int getIdProducto() { return idProducto; }
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    // --- Setters ---
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\n" +
               "Descripcion: " + descripcion + "\n" +
               "Precio: " + precio + "\n" +
               "Cantidad: " + cantidad;
    }
}
