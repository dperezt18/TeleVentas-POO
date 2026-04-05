import java.time.LocalDate;

public class Restauracion {

    private int idRestauracion;
    private ObraDeArte obra;
    private String tipoRestauracion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    public Restauracion(int idRestauracion, ObraDeArte obra,
                        String tipoRestauracion, LocalDate fechaInicio) {
        this.idRestauracion = idRestauracion;
        this.obra = obra;
        this.tipoRestauracion = tipoRestauracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.estado = "en_curso";
    }

    public int getIdRestauracion() { return idRestauracion; }
    public ObraDeArte getObra() { return obra; }
    public String getTipoRestauracion() { return tipoRestauracion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }

    public void registrarInicio() {
        obra.setEstado("en_restauracion");
        this.estado = "en_curso";
        System.out.println("Restauración iniciada para '" + obra.getTitulo() +
                "' - Tipo: " + tipoRestauracion + ".");
    }

    public void registrarFin(LocalDate fechaFin) {
        if (!"en_curso".equals(estado)) {
            System.out.println("Esta restauración ya fue finalizada.");
            return;
        }
        this.fechaFin = (fechaFin != null) ? fechaFin : LocalDate.now();
        this.estado = "finalizada";
        obra.setEstado("expuesta");
        System.out.println("Restauración finalizada para '" + obra.getTitulo() +
                "' el " + this.fechaFin + ".");
    }

    public void registrarFin() {
        registrarFin(null);
    }

    @Override
    public String toString() {
        return "Restauracion(id=" + idRestauracion + ", obra='" + obra.getTitulo() +
               "', tipo='" + tipoRestauracion + "', estado='" + estado + "')";
    }
}
