package Clases;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    protected List<Producto> productos;

    public Catalogo() {
        this.productos = new ArrayList<>();
    }

    public void listarProductos() {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public void enviarPorCorreo(String correo) {
        System.out.println("El catálogo fue enviado al correo " + correo);
    }
}
