import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private List<ObraDeArte> obras;

    public Catalogo() {
        this.obras = new ArrayList<>();
    }

    public List<ObraDeArte> getObras() { return new ArrayList<>(obras); }

    public void agregarObra(ObraDeArte obra) {
        if (!obras.contains(obra)) {
            obras.add(obra);
            System.out.println("Obra '" + obra.getTitulo() + "' registrada en el catálogo.");
        } else {
            System.out.println("La obra '" + obra.getTitulo() + "' ya está en el catálogo.");
        }
    }

    public ObraDeArte buscarObra(String titulo) {
        for (ObraDeArte obra : obras) {
            if (obra.getTitulo().equalsIgnoreCase(titulo)) {
                return obra;
            }
        }
        System.out.println("Obra con título '" + titulo + "' no encontrada en el catálogo.");
        return null;
    }

    public List<ObraDeArte> listarObras() {
        return new ArrayList<>(obras);
    }

    @Override
    public String toString() {
        return "Catalogo(obras=" + obras.size() + ")";
    }
}
