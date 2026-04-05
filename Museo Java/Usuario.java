public abstract class Usuario {

    protected int idUsuario;
    protected String nombre;
    protected String email;
    protected String contrasena;
    protected boolean autenticado;

    public Usuario(int idUsuario, String nombre, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.autenticado = false;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public boolean getAutenticado() { return autenticado; }

    public boolean login(String contrasena) {
        if (contrasena.equals(this.contrasena)) {
            this.autenticado = true;
            System.out.println("Usuario '" + nombre + "' autenticado exitosamente.");
            return true;
        }
        System.out.println("Error: Contraseña incorrecta.");
        return false;
    }

    public void logout() {
        this.autenticado = false;
        System.out.println("Usuario '" + nombre + "' ha cerrado sesión.");
    }

    @Override
    public String toString() {
        String estado = autenticado ? "autenticado" : "no autenticado";
        return getClass().getSimpleName() + "(id=" + idUsuario +
               ", nombre='" + nombre + "', " + estado + ")";
    }
}
