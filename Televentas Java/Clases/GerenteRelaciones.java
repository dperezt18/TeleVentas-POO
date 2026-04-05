package Clases;

public class GerenteRelaciones extends Usuario {

    public GerenteRelaciones(int id, String nombre, String email, String contrasena) {
        super(id, nombre, email, contrasena);
    }

    @Override
    public boolean login() {
        System.out.println(getNombre() + " ha iniciado sesión.");
        return true;
    }

    @Override
    public void logout() {
        System.out.println(getNombre() + " ha cerrado sesion.");
    }

    public void recibirQueja() {
        System.out.println(getNombre() + " ha recibido una queja.");
    }
}
