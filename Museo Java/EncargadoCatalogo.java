import java.util.List;

public class EncargadoCatalogo extends Usuario {

    public EncargadoCatalogo(int idUsuario, String nombre, String email, String contrasena) {
        super(idUsuario, nombre, email, contrasena);
    }

    public void registrarObra(Catalogo catalogo, ObraDeArte obra) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para registrar obras.");
            return;
        }
        catalogo.agregarObra(obra);
    }

    public void actualizarCatalogo(Catalogo catalogo) {
        if (!getAutenticado()) {
            System.out.println("Error: Debe autenticarse para actualizar el catálogo.");
            return;
        }
        List<ObraDeArte> obras = catalogo.listarObras();
        System.out.println("Catálogo actualizado. Total de obras: " + obras.size() + ".");
        for (ObraDeArte obra : obras) {
            System.out.println("  - " + obra.consultarInfo());
        }
    }
}
