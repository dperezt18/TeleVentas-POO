import java.time.LocalDate;

public class OtroObjeto extends ObraDeArte {

    private String descripcion;

    public OtroObjeto(int idObra, String titulo, String autor, String periodo,
                      double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                      String descripcion, String estado) {
        super(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, estado);
        this.descripcion = descripcion;
    }

    public OtroObjeto(int idObra, String titulo, String autor, String periodo,
                      double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                      String descripcion) {
        this(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, descripcion, "expuesta");
    }

    public String getDescripcion() { return descripcion; }

    @Override
    public String consultarInfo() {
        return String.format("Objeto: '%s' | Autor: %s | Periodo: %s | Descripción: %s | Valoración: $%,.2f | Estado: %s",
                titulo, autor, periodo, descripcion, valoracion, estado);
    }
}
