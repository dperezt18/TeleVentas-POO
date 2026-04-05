import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcesoRestauracionAutomatica {

    public static final int ANOS_PARA_RESTAURACION = 5;

    private List<Restauracion> restauraciones;

    public ProcesoRestauracionAutomatica(List<Restauracion> restauraciones) {
        this.restauraciones = (restauraciones != null) ? restauraciones : new ArrayList<>();
    }

    public ProcesoRestauracionAutomatica() {
        this(null);
    }

    public List<ObraDeArte> verificarObrasParaRestauracion(List<ObraDeArte> obras) {
        LocalDate hoy = LocalDate.now();
        List<ObraDeArte> obrasARestaurar = new ArrayList<>();

        for (ObraDeArte obra : obras) {
            if (!"expuesta".equals(obra.getEstado())) continue;

            LocalDate ultimaFecha = obtenerFechaUltima(obra);
            LocalDate fechaRef = (ultimaFecha != null) ? ultimaFecha : obra.getFechaEntrada();
            long dias = ChronoUnit.DAYS.between(fechaRef, hoy);
            double anos = dias / 365.25;

            if (anos >= ANOS_PARA_RESTAURACION) {
                obrasARestaurar.add(obra);
                System.out.printf("Obra '%s' requiere restauración (%.1f años desde última intervención).%n",
                        obra.getTitulo(), anos);
            }
        }

        if (obrasARestaurar.isEmpty()) {
            System.out.println("No hay obras que requieran restauración automática hoy.");
        }

        return obrasARestaurar;
    }

    public void actualizarEstadoObras(List<ObraDeArte> obrasARestaurar) {
        for (ObraDeArte obra : obrasARestaurar) {
            obra.setEstado("en_restauracion");
            System.out.println("Obra '" + obra.getTitulo() + "' enviada a restauración automática.");
        }
    }

    private LocalDate obtenerFechaUltima(ObraDeArte obra) {
        return restauraciones.stream()
                .filter(r -> r.getObra().getIdObra() == obra.getIdObra()
                          && "finalizada".equals(r.getEstado()))
                .max(Comparator.comparing(Restauracion::getFechaFin))
                .map(Restauracion::getFechaFin)
                .orElse(null);
    }
}
