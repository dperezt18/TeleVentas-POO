import java.time.LocalDate;

public abstract class ObraDeArte {

    public static final String[] ESTADOS_VALIDOS = {"expuesta", "en_restauracion", "cedida"};

    protected int idObra;
    protected String titulo;
    protected String autor;
    protected String periodo;
    protected double valoracion;
    protected LocalDate fechaCreacion;
    protected LocalDate fechaEntrada;
    protected String estado;

    public ObraDeArte(int idObra, String titulo, String autor, String periodo,
                      double valoracion, LocalDate fechaCreacion,
                      LocalDate fechaEntrada, String estado) {
        this.idObra = idObra;
        this.titulo = titulo;
        this.autor = autor;
        this.periodo = periodo;
        this.valoracion = valoracion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrada = fechaEntrada;
        this.estado = estado;
    }

    public ObraDeArte(int idObra, String titulo, String autor, String periodo,
                      double valoracion, LocalDate fechaCreacion, LocalDate fechaEntrada) {
        this(idObra, titulo, autor, periodo, valoracion, fechaCreacion, fechaEntrada, "expuesta");
    }

    public int getIdObra() { return idObra; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getPeriodo() { return periodo; }
    public double getValoracion() { return valoracion; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public String getEstado() { return estado; }

    public void setEstado(String nuevoEstado) {
        boolean valido = false;
        for (String e : ESTADOS_VALIDOS) {
            if (e.equals(nuevoEstado)) { valido = true; break; }
        }
        if (!valido) {
            throw new IllegalArgumentException(
                "Estado inválido: '" + nuevoEstado + "'. Debe ser uno de: expuesta, en_restauracion, cedida."
            );
        }
        this.estado = nuevoEstado;
    }

    public abstract String consultarInfo();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(id=" + idObra +
               ", titulo='" + titulo + "', estado='" + estado + "')";
    }
}
