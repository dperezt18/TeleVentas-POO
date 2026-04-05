import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RestauradorJefe extends Usuario {

    private List<Restauracion> restauraciones;

    public RestauradorJefe(int idUsuario, String nombre, String email, String contrasena) {
        super(idUsuario, nombre, email, contrasena);
        this.restauraciones = new ArrayList<>();
    }

    public List<Restauracion> getRestauraciones() {
        return new ArrayList<>(restauraciones);
    }

    public void iniciarRestauracion(Restauracion restauracion) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para iniciar restauraciones.");
            return;
        }
        restauracion.registrarInicio();
        restauraciones.add(restauracion);
    }

    public void finalizarRestauracion(Restauracion restauracion, LocalDate fechaFin) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para finalizar restauraciones.");
            return;
        }
        restauracion.registrarFin(fechaFin);
    }

    public void finalizarRestauracion(Restauracion restauracion) {
        finalizarRestauracion(restauracion, null);
    }

    public List<Restauracion> consultarRestauraciones(ObraDeArte obra) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para consultar restauraciones.");
            return new ArrayList<>();
        }
        List<Restauracion> restauracionesObra = restauraciones.stream()
                .filter(r -> r.getObra().getIdObra() == obra.getIdObra())
                .sorted(Comparator.comparing(Restauracion::getFechaInicio))
                .collect(Collectors.toList());

        System.out.printf("Restauraciones de '%s' (%d encontradas):%n",
                obra.getTitulo(), restauracionesObra.size());
        for (Restauracion r : restauracionesObra) {
            String fin = r.getFechaFin() != null ? r.getFechaFin().toString() : "En curso";
            System.out.printf("  - [%s] Inicio: %s | Fin: %s%n",
                    r.getTipoRestauracion(), r.getFechaInicio(), fin);
        }
        return restauracionesObra;
    }

    public List<ObraDeArte> verificarObrasParaRestauracion(List<ObraDeArte> obras) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para verificar obras.");
            return new ArrayList<>();
        }
        LocalDate hoy = LocalDate.now();
        List<ObraDeArte> obrasARestaurar = new ArrayList<>();

        for (ObraDeArte obra : obras) {
            if ("en_restauracion".equals(obra.getEstado())) continue;
            Restauracion ultima = obtenerUltimaRestauracion(obra);
            LocalDate fechaRef = (ultima != null) ? ultima.getFechaFin() : obra.getFechaEntrada();
            long dias = ChronoUnit.DAYS.between(fechaRef, hoy);
            if (dias >= 5 * 365) {
                obrasARestaurar.add(obra);
            }
        }
        return obrasARestaurar;
    }

    private Restauracion obtenerUltimaRestauracion(ObraDeArte obra) {
        return restauraciones.stream()
                .filter(r -> r.getObra().getIdObra() == obra.getIdObra()
                          && "finalizada".equals(r.getEstado()))
                .max(Comparator.comparing(Restauracion::getFechaFin))
                .orElse(null);
    }
}
