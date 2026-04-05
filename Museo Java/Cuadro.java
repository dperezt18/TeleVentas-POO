import java.time.LocalDate;

public class Cuadro extends ObraDeArte {

    private String estilo;
    private String tecnica;

    public Cuadro(int idObra, String titulo, String autor, String periodo,
                  double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                  String estilo, String tecnica, String estado) {
        super(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, estado);
        this.estilo = estilo;
        this.tecnica = tecnica;
    }

    public Cuadro(int idObra, String titulo, String autor, String periodo,
                  double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada,
                  String estilo, String tecnica) {
        this(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, estilo, tecnica, "expuesta");
    }

    public String getEstilo() { return estilo; }
    public String getTecnica() { return tecnica; }

    @Override
    public String consultarInfo() {
        return String.format("Cuadro: '%s' | Autor: %s | Periodo: %s | Estilo: %s | Técnica: %s | Valoración: $%,.2f | Estado: %s",
                titulo, autor, periodo, estilo, tecnica, valoracion, estado);
    }
}
