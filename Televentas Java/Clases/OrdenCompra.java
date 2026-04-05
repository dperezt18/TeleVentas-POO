package Clases;

import java.time.LocalDate;

public class OrdenCompra {

    private int idOrden;
    private double valorTotal;
    private LocalDate fecha;
    private String estado;

    public OrdenCompra(int idOrden, double valorTotal, LocalDate fecha, String estado) {
        this.idOrden = idOrden;
        this.valorTotal = valorTotal;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void confirmar() {
        this.estado = "Confirmada";
        System.out.println("Su orden ha sido confirmada");
    }

    public void cancelar() {
        this.estado = "Cancelada";
        System.out.println("Su orden ha sido cancelada");
    }

    // --- Getters ---
    public int getIdOrden() { return idOrden; }
    public double getValorTotal() { return valorTotal; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
}
