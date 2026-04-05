import java.util.ArrayList;
import java.util.List;

public class Sala {

    private int idSala;
    private String nombre;
    private String ubicacion;
    private List<ObraDeArte> obras;

    public Sala(int idSala, String nombre, String ubicacion) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.obras = new ArrayList<>();
    }

    public int getIdSala() { return idSala; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public List<ObraDeArte> getObras() { return new ArrayList<>(obras); }

    public void agregarObra(ObraDeArte obra) {
        if (!"expuesta".equals(obra.getEstado())) {
            System.out.println("La obra '" + obra.getTitulo() +
                    "' no puede exponerse. Estado actual: '" + obra.getEstado() + "'.");
            return;
        }
        if (!obras.contains(obra)) {
            obras.add(obra);
            System.out.println("Obra '" + obra.getTitulo() + "' agregada a sala '" + nombre + "'.");
        } else {
            System.out.println("La obra '" + obra.getTitulo() + "' ya está en esta sala.");
        }
    }

    public List<ObraDeArte> listarObras() {
        return new ArrayList<>(obras);
    }

    @Override
    public String toString() {
        return "Sala(id=" + idSala + ", nombre='" + nombre + "', obras=" + obras.size() + ")";
    }
}
