import java.util.ArrayList;
import java.util.List;

public class Museo {

    private int idMuseo;
    private String nombre;
    private String direccion;
    private List<ObraDeArte> obras;
    private List<Museo> museosAsociados;

    public Museo(int idMuseo, String nombre, String direccion) {
        this.idMuseo = idMuseo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.obras = new ArrayList<>();
        this.museosAsociados = new ArrayList<>();
    }

    public int getIdMuseo() { return idMuseo; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public List<ObraDeArte> getObras() { return new ArrayList<>(obras); }
    public List<Museo> getMuseosAsociados() { return new ArrayList<>(museosAsociados); }

    public void agregarObra(ObraDeArte obra) {
        if (!obras.contains(obra)) {
            obras.add(obra);
            System.out.println("Obra '" + obra.getTitulo() + "' agregada al museo '" + nombre + "'.");
        } else {
            System.out.println("La obra '" + obra.getTitulo() + "' ya existe en el museo.");
        }
    }

    public List<ObraDeArte> listarObras() {
        return new ArrayList<>(obras);
    }

    public void agregarMuseoAsociado(Museo museo) {
        if (!museosAsociados.contains(museo)) {
            museosAsociados.add(museo);
            System.out.println("Museo '" + museo.getNombre() + "' asociado con '" + nombre + "'.");
        } else {
            System.out.println("El museo '" + museo.getNombre() + "' ya está asociado.");
        }
    }

    public double calcularValoracionTotal() {
        return obras.stream().mapToDouble(ObraDeArte::getValoracion).sum();
    }

    @Override
    public String toString() {
        return "Museo(id=" + idMuseo + ", nombre='" + nombre + "', obras=" + obras.size() + ")";
    }
}
