package Clases;

import java.time.LocalDate;

public class Pedido {

    private int idPedido;
    private LocalDate fecha;
    private String estado;
    private String empresaTransporte;

    public Pedido(int idPedido, LocalDate fecha, String estado, String empresaTransporte) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.empresaTransporte = empresaTransporte;
    }

    public void armar() {
        this.estado = "Armado";
        System.out.println("El pedido ha sido armado");
    }

    public void despachar() {
        this.estado = "Despachado";
        System.out.println("El pedido ha sido despachado con éxito");
    }

    // --- Getters ---
    public int getIdPedido() { return idPedido; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public String getEmpresaTransporte() { return empresaTransporte; }
}
