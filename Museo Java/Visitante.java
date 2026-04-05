import java.util.List;

public class Visitante {

    public List<ObraDeArte> consultarObrasPorSala(Sala sala) {
        List<ObraDeArte> obras = sala.listarObras();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  Sala: " + sala.getNombre() + " | Ubicación: " + sala.getUbicacion());
        System.out.println("=".repeat(60));
        if (obras.isEmpty()) {
            System.out.println("  No hay obras en esta sala actualmente.");
        }
        for (ObraDeArte obra : obras) {
            System.out.println("  - " + obra.consultarInfo());
        }
        System.out.println("=".repeat(60) + "\n");
        return obras;
    }
}
