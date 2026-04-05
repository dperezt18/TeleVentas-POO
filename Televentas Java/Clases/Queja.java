package Clases;

import java.time.LocalDate;

public class Queja {

    private int idQueja;
    private String descripcion;
    private LocalDate fecha;
    private String estado;

    public Queja(int idQueja, String descripcion, LocalDate fecha, String estado) {
        this.idQueja = idQueja;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void enviarAGerente() {
        this.estado = "Enviada al gerente";
        System.out.println("La queja fue enviada correctamente al gerente");
    }

    // --- Getters ---
    public int getIdQueja() { return idQueja; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
}
