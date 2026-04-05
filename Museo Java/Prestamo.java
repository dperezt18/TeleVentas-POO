import java.time.LocalDate;

public class Prestamo {

    private int idPrestamo;
    private ObraDeArte obra;
    private Museo museoOrigen;
    private Museo museoDestino;
    private double importe;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    public Prestamo(int idPrestamo, ObraDeArte obra, Museo museoOrigen, Museo museoDestino,
                    double importe, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idPrestamo = idPrestamo;
        this.obra = obra;
        this.museoOrigen = museoOrigen;
        this.museoDestino = museoDestino;
        this.importe = importe;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = "pendiente";
    }

    public int getIdPrestamo() { return idPrestamo; }
    public ObraDeArte getObra() { return obra; }
    public Museo getMuseoOrigen() { return museoOrigen; }
    public Museo getMuseoDestino() { return museoDestino; }
    public double getImporte() { return importe; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }

    public void registrarPrestamo() {
        if ("cedida".equals(obra.getEstado())) {
            System.out.println("La obra '" + obra.getTitulo() +
                    "' ya está cedida. No se puede registrar el préstamo.");
            return;
        }
        if ("en_restauracion".equals(obra.getEstado())) {
            System.out.println("La obra '" + obra.getTitulo() +
                    "' está en restauración. No se puede ceder.");
            return;
        }
        obra.setEstado("cedida");
        this.estado = "activo";
        System.out.printf("Préstamo registrado: '%s' cedida de '%s' a '%s' por $%,.2f.%n",
                obra.getTitulo(), museoOrigen.getNombre(), museoDestino.getNombre(), importe);
    }

    public void finalizarPrestamo() {
        if (!"activo".equals(estado)) {
            System.out.println("El préstamo no está activo.");
            return;
        }
        obra.setEstado("expuesta");
        this.estado = "finalizado";
        System.out.println("Préstamo finalizado: '" + obra.getTitulo() +
                "' devuelta a '" + museoOrigen.getNombre() + "'.");
    }

    @Override
    public String toString() {
        return "Prestamo(id=" + idPrestamo + ", obra='" + obra.getTitulo() +
               "', destino='" + museoDestino.getNombre() + "', estado='" + estado + "')";
    }
}
