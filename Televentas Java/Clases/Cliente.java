package Clases;

public class Cliente extends Usuario {

    private String telefono;
    private String fechaRegistro;
    private String estado;

    public Cliente(int id, String nombre, String email, String contrasena,
                   String telefono, String fechaRegistro, String estado) {
        super(id, nombre, email, contrasena);
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
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

    // --- Getters ---
    public String getTelefono() { return telefono; }
    public String getFechaRegistro() { return fechaRegistro; }
    public String getEstado() { return estado; }

    // --- Setters ---
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public void setEstado(String estado) { this.estado = estado; }
}
