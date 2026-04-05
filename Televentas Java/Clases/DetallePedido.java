package Clases;

public class DetallePedido {

    private int idDetalle;
    private int cantidad;
    private double precioUnitario;
    private double descuento;

    public DetallePedido(int idDetalle, int cantidad,
                         double precioUnitario, double descuento) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario - descuento;
    }

    // --- Getters ---
    public int getIdDetalle() { return idDetalle; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getDescuento() { return descuento; }
}
