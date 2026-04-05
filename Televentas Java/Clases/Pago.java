package Clases;

import java.time.LocalDate;

public abstract class Pago {

    private int idPago;
    private double monto;
    private LocalDate fecha;

    public Pago(int idPago, double monto, LocalDate fecha) {
        this.idPago = idPago;
        this.monto = monto;
        this.fecha = fecha;
    }

    public abstract boolean procesar();
    public abstract String obtenerEstado();

    // --- Getters ---
    public int getIdPago() { return idPago; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
}
