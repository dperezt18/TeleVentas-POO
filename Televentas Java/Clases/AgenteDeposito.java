package Clases;

import java.util.ArrayList;
import java.util.List;

public class AgenteDeposito extends Usuario {

    private String deposito;

    public AgenteDeposito(int id, String nombre, String email,
                          String contrasena, String deposito) {
        super(id, nombre, email, contrasena);
        this.deposito = deposito;
    }

    @Override
    public boolean login() {
        System.out.println(getNombre() + " ha iniciado sesión");
        return true;
    }

    @Override
    public void logout() {
        System.out.println(getNombre() + " ha cerrado sesión");
    }

    public List<Object> consultarOrdenes() {
        System.out.println(getNombre() + " está consultando órdenes");
        return new ArrayList<>();
    }

    public void crearPedido() {
        System.out.println(getNombre() + " está creando un pedido");
    }

    // --- Getter ---
    public String getDeposito() { return deposito; }

    // --- Setter ---
    public void setDeposito(String deposito) { this.deposito = deposito; }
}
