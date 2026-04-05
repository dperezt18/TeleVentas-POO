import java.time.LocalDate;

public class Escultura extends ObraDeArte {

    private String estilo;
    private String material;

    public Escultura(int idObra, String titulo, String autor, String periodo,
                     double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                     String estilo, String material, String estado) {
        super(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, estado);
        this.estilo = estilo;
        this.material = material;
    }

    public Escultura(int idObra, String titulo, String autor, String periodo,
                     double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                     String estilo, String material) {
        this(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, estilo, material, "expuesta");
    }

    public String getEstilo() { return estilo; }
    public String getMaterial() { return material; }

    @Override
    public String consultarInfo() {
        return String.format("Escultura: '%s' | Autor: %s | Periodo: %s | Estilo: %s | Material: %s | Valoración: $%,.2f | Estado: %s",
                titulo, autor, periodo, estilo, material, valoracion, estado);
    }
}
