package Clases;

public class Direccion {

    private String calle;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private String pais;

    public Direccion(String calle, String ciudad, String departamento,
                     String codigoPostal, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }

    // --- Getters ---
    public String getCalle() { return calle; }
    public String getCiudad() { return ciudad; }
    public String getDepartamento() { return departamento; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getPais() { return pais; }

    // --- Setters ---
    public void setCalle(String calle) { this.calle = calle; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public void setPais(String pais) { this.pais = pais; }

    public String obtenerDireccionCompleta() {
        return calle + ", " + ciudad + ", " + departamento + ", " + codigoPostal + ", " + pais;
    }
}
