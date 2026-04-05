import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Director extends Usuario {

    public Director(int idUsuario, String nombre, String email, String contrasena) {
        super(idUsuario, nombre, email, contrasena);
    }

    public void gestionarPrestamo(Prestamo prestamo) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para gestionar préstamos.");
            return;
        }
        prestamo.registrarPrestamo();
    }

    public double consultarValoracionTotal(Museo museo) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para consultar valoraciones.");
            return 0.0;
        }
        double total = museo.calcularValoracionTotal();
        System.out.printf("Valoración total del museo '%s': $%,.2f%n", museo.getNombre(), total);
        return total;
    }

    public Map<String, Integer> consultarEstadisticasRestauracion(List<Restauracion> restauraciones) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para consultar estadísticas.");
            return new HashMap<>();
        }
        int enCurso = 0, finalizadas = 0;
        for (Restauracion r : restauraciones) {
            if ("en_curso".equals(r.getEstado())) enCurso++;
            else if ("finalizada".equals(r.getEstado())) finalizadas++;
        }
        Map<String, Integer> stats = new HashMap<>();
        stats.put("total", restauraciones.size());
        stats.put("en_curso", enCurso);
        stats.put("finalizadas", finalizadas);
        return stats;
    }
}
