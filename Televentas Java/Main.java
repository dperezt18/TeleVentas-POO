import Clases.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarUsuariosPrueba() {
        System.out.println("\n========== USUARIOS DISPONIBLES ==========");
        System.out.println("1. Cliente");
        System.out.println("   Usuario: Diunis Perez");
        System.out.println("   Contraseña: 1234");
        System.out.println();
        System.out.println("2. Agente de Depósito");
        System.out.println("   Usuario: Carlos");
        System.out.println("   Contraseña: 5678");
        System.out.println();
        System.out.println("3. Gerente de Relaciones");
        System.out.println("   Usuario: Laura");
        System.out.println("   Contraseña: abcd");
        System.out.println("==========================================\n");
    }

    public static String[] autenticar() {
        System.out.println("===== INICIO DE SESIÓN =====");
        mostrarUsuariosPrueba();

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine().trim();

        if ("Diunis Perez".equals(usuario) && "1234".equals(contrasena)) {
            System.out.println("\nBienvenido/a " + usuario);
            System.out.println("Rol: Cliente");
            return new String[]{usuario, "Cliente"};
        } else if ("Carlos".equals(usuario) && "5678".equals(contrasena)) {
            System.out.println("\nBienvenido/a " + usuario);
            System.out.println("Rol: Agente de Depósito");
            return new String[]{usuario, "Agente de Depósito"};
        } else if ("Laura".equals(usuario) && "abcd".equals(contrasena)) {
            System.out.println("\nBienvenido/a " + usuario);
            System.out.println("Rol: Gerente de Relaciones");
            return new String[]{usuario, "Gerente de Relaciones"};
        }

        System.out.println("\nUsuario o contraseña incorrectos.");
        return new String[]{null, null};
    }

    public static void mostrarMenu(String rol) {
        System.out.println("\n========== MENÚ TELEVENTAS ==========");
        System.out.println("Rol activo: " + rol);
        System.out.println("1. Ver catálogo");
        System.out.println("2. Buscar producto por código");
        System.out.println("3. Agregar producto al catálogo");
        System.out.println("4. Comprar producto y pagar");
        System.out.println("5. Crear pedido");
        System.out.println("6. Presentar queja");
        System.out.println("7. Ver datos del cliente");
        System.out.println("8. Ver resumen general del sistema");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    public static boolean volverAlMenu() {
        System.out.print("\n¿Desea volver al menú principal? (s/n): ");
        return "s".equalsIgnoreCase(scanner.nextLine().trim());
    }

    public static boolean tienePermiso(String rol, String opcion) {
        List<String> permisos;
        switch (rol) {
            case "Cliente":
                permisos = Arrays.asList("1", "2", "4", "6", "7", "8", "0");
                break;
            case "Agente de Depósito":
                permisos = Arrays.asList("1", "2", "5", "7", "8", "0");
                break;
            case "Gerente de Relaciones":
                permisos = Arrays.asList("1", "2", "3", "7", "8", "0");
                break;
            default:
                return false;
        }
        return permisos.contains(opcion);
    }

    public static void mostrarResumen(Cliente cliente, Direccion direccion,
                                      OrdenCompra ordenActual, Pago pagoActual,
                                      Pedido pedidoActual, Queja quejaActual) {
        System.out.println("\n========== RESUMEN DEL SISTEMA ==========");

        System.out.println("\n--- Cliente ---");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Dirección: " + direccion.obtenerDireccionCompleta());

        System.out.println("\n--- Orden actual ---");
        if (ordenActual == null) System.out.println("No hay orden creada.");
        else System.out.println("Estado: " + ordenActual.getEstado() + " | Total: " + ordenActual.getValorTotal());

        System.out.println("\n--- Pago actual ---");
        if (pagoActual == null) System.out.println("No hay pago registrado.");
        else System.out.println("Estado del pago: " + pagoActual.obtenerEstado());

        System.out.println("\n--- Pedido actual ---");
        if (pedidoActual == null) System.out.println("No hay pedido creado.");
        else System.out.println("Estado: " + pedidoActual.getEstado());

        System.out.println("\n--- Queja actual ---");
        if (quejaActual == null) System.out.println("No hay quejas registradas.");
        else System.out.println("Estado: " + quejaActual.getEstado());

        System.out.println("========================================");
    }

    public static void main(String[] args) {
        System.out.println("===== SISTEMA TELEVENTAS =====");

        String[] auth = autenticar();
        String usuarioAutenticado = auth[0];
        String rol = auth[1];

        if (usuarioAutenticado == null) return;

        // ===== OBJETOS DEL SISTEMA =====
        Cliente cliente = new Cliente(1, "Diunis Perez", "diunis@mail.com",
                "1234", "3001234567", "2026-03-24", "Activo");

        Direccion direccion = new Direccion("Calle 123", "Bogotá",
                "Cundinamarca", "110111", "Colombia");

        AgenteDeposito agente = new AgenteDeposito(2, "Carlos",
                "carlos@mail.com", "5678", "Deposito Central");

        GerenteRelaciones gerente = new GerenteRelaciones(3, "Laura",
                "laura@mail.com", "abcd");

        Catalogo catalogo = new Catalogo();

        // ===== PRODUCTOS INICIALES =====
        Producto producto1 = new Producto(1, "TV001", "Televisor 50 pulgadas", 1800000.0, 5);
        Producto producto2 = new Producto(2, "AUD001", "Audifonos inalambricos", 250000.0, 10);
        catalogo.agregarProducto(producto1);
        catalogo.agregarProducto(producto2);

        // ===== VARIABLES DE PROCESO =====
        DetallePedido detalleActual = null;
        OrdenCompra ordenActual = null;
        Pago pagoActual = null;
        Pedido pedidoActual = null;
        Queja quejaActual = null;

        while (true) {
            mostrarMenu(rol);
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if ("0".equals(opcion)) {
                System.out.println("Saliendo del sistema...");
                break;
            }

            if (!tienePermiso(rol, opcion)) {
                System.out.println("\nError: su rol no tiene permiso para realizar esta acción.");
                if (!volverAlMenu()) { System.out.println("Saliendo del sistema..."); break; }
                continue;
            }

            if ("1".equals(opcion)) {
                System.out.println("\n--- CATÁLOGO ---");
                catalogo.listarProductos();

            } else if ("2".equals(opcion)) {
                System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
                catalogo.listarProductos();
                System.out.print("\nIngrese el código del producto: ");
                String codigo = scanner.nextLine().trim();
                Producto producto = catalogo.buscarProducto(codigo);
                if (producto != null) { System.out.println("\nProducto encontrado:"); System.out.println(producto); }
                else System.out.println("No se encontró un producto con ese código.");

            } else if ("3".equals(opcion)) {
                try {
                    System.out.println("\n--- AGREGAR PRODUCTO AL CATÁLOGO ---");
                    gerente.login();
                    System.out.print("ID del producto: "); int idProd = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Código: "); String cod = scanner.nextLine().trim();
                    System.out.print("Descripción: "); String desc = scanner.nextLine().trim();
                    System.out.print("Precio: "); double precio = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Cantidad: "); int cant = Integer.parseInt(scanner.nextLine().trim());
                    catalogo.agregarProducto(new Producto(idProd, cod, desc, precio, cant));
                    System.out.println("Producto agregado correctamente al catálogo.");
                    gerente.logout();
                } catch (NumberFormatException e) {
                    System.out.println("Error: revise los valores numéricos ingresados.");
                }

            } else if ("4".equals(opcion)) {
                try {
                    System.out.println("\n--- COMPRAR PRODUCTO Y PAGAR ---");
                    System.out.println("Productos disponibles en el catálogo:");
                    catalogo.listarProductos();
                    System.out.print("\nIngrese el código del producto que desea comprar: ");
                    String codigo = scanner.nextLine().trim();
                    Producto producto = catalogo.buscarProducto(codigo);

                    if (producto == null) {
                        System.out.println("No se encontró el producto. Verifique el código e intente nuevamente.");
                    } else {
                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = Integer.parseInt(scanner.nextLine().trim());
                        detalleActual = new DetallePedido(1, cantidad, producto.getPrecio(), 0.0);
                        double subtotal = detalleActual.calcularSubtotal();
                        ordenActual = new OrdenCompra(1, subtotal, LocalDate.now(), "Pendiente");
                        System.out.println("\nOrden de compra creada correctamente.");
                        System.out.println("Descuento aplicado automáticamente: 0.0");
                        System.out.println("Subtotal calculado: " + subtotal);
                        System.out.println("Valor total: " + ordenActual.getValorTotal());
                        ordenActual.confirmar();

                        System.out.println("\n--- DATOS DE LA TARJETA ---");
                        System.out.print("Número de tarjeta: "); String numero = scanner.nextLine().trim();
                        System.out.print("Titular: "); String titular = scanner.nextLine().trim();
                        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
                        LocalDate fechaVenc = LocalDate.parse(scanner.nextLine().trim());
                        System.out.print("CVV: "); String cvv = scanner.nextLine().trim();

                        TarjetaCredito tarjeta = new TarjetaCredito(1, ordenActual.getValorTotal(),
                                LocalDate.now(), numero, titular, fechaVenc, cvv);
                        tarjeta.validar();
                        tarjeta.autorizar();
                        tarjeta.procesar();
                        pagoActual = tarjeta;

                        System.out.println("\nPago procesado correctamente.");
                        System.out.println("Estado del pago: " + pagoActual.obtenerEstado());
                    }
                } catch (Exception e) {
                    System.out.println("Error: revise la cantidad o el formato de la fecha.");
                }

            } else if ("5".equals(opcion)) {
                if (ordenActual == null || pagoActual == null) {
                    System.out.println("Primero debe existir una compra pagada para crear el pedido.");
                } else {
                    System.out.println("\n--- CREAR PEDIDO ---");
                    pedidoActual = new Pedido(1, LocalDate.now(), "Pendiente", "Servientrega");
                    agente.login();
                    agente.consultarOrdenes();
                    agente.crearPedido();
                    pedidoActual.armar();
                    pedidoActual.despachar();
                    agente.logout();
                    System.out.println("Pedido procesado correctamente.");
                }

            } else if ("6".equals(opcion)) {
                System.out.println("\n--- PRESENTAR QUEJA ---");
                System.out.print("Escriba la queja: ");
                String descripcion = scanner.nextLine().trim();
                quejaActual = new Queja(1, descripcion, LocalDate.now(), "Pendiente");
                quejaActual.enviarAGerente();
                gerente.login();
                System.out.println("El gerente de relaciones fue notificado.");
                gerente.logout();
                System.out.println("Queja registrada correctamente.");

            } else if ("7".equals(opcion)) {
                System.out.println("\n--- DATOS DEL CLIENTE ---");
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Dirección: " + direccion.obtenerDireccionCompleta());
                System.out.println("Usuario autenticado: " + usuarioAutenticado);
                System.out.println("Rol activo: " + rol);

            } else if ("8".equals(opcion)) {
                mostrarResumen(cliente, direccion, ordenActual, pagoActual, pedidoActual, quejaActual);

            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }

            if (!volverAlMenu()) { System.out.println("Saliendo del sistema..."); break; }
        }
    }
}
