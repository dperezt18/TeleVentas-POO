import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // ==================================================================
    // DATOS PRECARGADOS DEL SISTEMA
    // ==================================================================

    public static Map<String, Object> crearDatosIniciales() {
        // --- Museos ---
        Museo museoPrincipal = new Museo(1, "Museo Nacional de Arte", "Calle 123, Bogotá");
        Museo museoAsociado1 = new Museo(2, "Museo de Arte Moderno", "Av Reforma 456, México");
        Museo museoAsociado2 = new Museo(3, "Museo del Prado", "Calle Ruiz 23, Madrid");
        museoPrincipal.agregarMuseoAsociado(museoAsociado1);
        museoPrincipal.agregarMuseoAsociado(museoAsociado2);

        // --- Salas ---
        Sala salaPintura = new Sala(1, "Sala de Pintura Clásica", "Piso 1 - Ala Norte");
        Sala salaEscultura = new Sala(2, "Sala de Esculturas", "Piso 1 - Ala Sur");
        Sala salaContemporaneo = new Sala(3, "Sala de Arte Contemporáneo", "Piso 2");

        // --- Obras ---
        Cuadro cuadro1 = new Cuadro(1, "La Última Cena", "Leonardo da Vinci", "Renacimiento",
                450_000_000.00, LocalDate.of(1498, 1, 1), LocalDate.of(2018, 6, 15),
                "Renacimiento italiano", "Temple y óleo sobre yeso");
        Cuadro cuadro2 = new Cuadro(2, "La Noche Estrellada", "Vincent van Gogh", "Postimpresionismo",
                100_000_000.00, LocalDate.of(1889, 6, 1), LocalDate.of(2019, 3, 20),
                "Postimpresionista", "Óleo sobre lienzo");
        Escultura escultura1 = new Escultura(3, "El Pensador", "Auguste Rodin", "Impresionismo",
                15_000_000.00, LocalDate.of(1904, 1, 1), LocalDate.of(2017, 9, 10),
                "Impresionista", "Bronce");
        Escultura escultura2 = new Escultura(4, "David", "Miguel Ángel", "Renacimiento",
                200_000_000.00, LocalDate.of(1504, 1, 1), LocalDate.of(2020, 1, 5),
                "Renacentista", "Mármol blanco");
        OtroObjeto otroObjeto = new OtroObjeto(5, "Vasija Ceremonial Muisca", "Artesano Muisca", "Precolombino",
                5_000_000.00, LocalDate.of(1200, 1, 1), LocalDate.of(2020, 11, 1),
                "Vasija de cerámica con motivos geométricos rituales");

        List<ObraDeArte> obras = new ArrayList<>();
        obras.add(cuadro1); obras.add(cuadro2);
        obras.add(escultura1); obras.add(escultura2);
        obras.add(otroObjeto);

        // --- Registrar obras en museo y catálogo ---
        Catalogo catalogo = new Catalogo();
        for (ObraDeArte obra : obras) {
            museoPrincipal.agregarObra(obra);
            catalogo.agregarObra(obra);
        }

        // --- Asignar obras a salas ---
        salaPintura.agregarObra(cuadro1);
        salaPintura.agregarObra(cuadro2);
        salaEscultura.agregarObra(escultura1);
        salaEscultura.agregarObra(escultura2);
        salaContemporaneo.agregarObra(otroObjeto);

        // --- Usuarios ---
        Director director = new Director(1, "Carlos Méndez", "carlos@museo.edu.co", "dir123");
        EncargadoCatalogo encargado = new EncargadoCatalogo(2, "Laura Gómez", "laura@museo.edu.co", "cat123");
        RestauradorJefe restaurador = new RestauradorJefe(3, "Pedro Ramírez", "pedro@museo.edu.co", "res123");

        List<Sala> salas = new ArrayList<>();
        salas.add(salaPintura); salas.add(salaEscultura); salas.add(salaContemporaneo);

        List<Museo> museosAsociados = new ArrayList<>();
        museosAsociados.add(museoAsociado1); museosAsociados.add(museoAsociado2);

        Map<String, Object> datos = new HashMap<>();
        datos.put("museo", museoPrincipal);
        datos.put("museosAsociados", museosAsociados);
        datos.put("salas", salas);
        datos.put("obras", obras);
        datos.put("catalogo", catalogo);
        datos.put("director", director);
        datos.put("encargado", encargado);
        datos.put("restaurador", restaurador);
        datos.put("prestamos", new ArrayList<Prestamo>());
        return datos;
    }

    // ==================================================================
    // FUNCIONES DE MENÚ
    // ==================================================================

    @SuppressWarnings("unchecked")
    public static void mostrarUsuarios() {
        System.out.println("\n========== USUARIOS DISPONIBLES ==========");
        System.out.println("1. Director       | Carlos Méndez  | Clave: dir123");
        System.out.println("2. Encargado Cat. | Laura Gómez    | Clave: cat123");
        System.out.println("3. Restaurador    | Pedro Ramírez  | Clave: res123");
        System.out.println("4. Visitante      | (sin login)");
        System.out.println("==========================================\n");
    }

    @SuppressWarnings("unchecked")
    public static Object[] autenticar(Map<String, Object> datos) {
        mostrarUsuarios();
        System.out.print("Seleccione su rol (1-4): ");
        String opcion = scanner.nextLine().trim();

        if ("4".equals(opcion)) {
            System.out.println("\nBienvenido, visitante.");
            return new Object[]{"visitante", null};
        }

        if ("1".equals(opcion) || "2".equals(opcion) || "3".equals(opcion)) {
            Usuario usuario = null;
            String rol = null;
            if ("1".equals(opcion)) { rol = "director"; usuario = (Director) datos.get("director"); }
            else if ("2".equals(opcion)) { rol = "encargado"; usuario = (EncargadoCatalogo) datos.get("encargado"); }
            else { rol = "restaurador"; usuario = (RestauradorJefe) datos.get("restaurador"); }

            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine().trim();
            if (usuario.login(contrasena)) {
                return new Object[]{rol, usuario};
            }
            return new Object[]{null, null};
        }

        System.out.println("Opción inválida.");
        return new Object[]{null, null};
    }

    public static boolean volverAlMenu() {
        System.out.print("\n¿Volver al menú? (s/n): ");
        return "s".equalsIgnoreCase(scanner.nextLine().trim());
    }

    // ==================================================================
    // MENÚ DEL DIRECTOR
    // ==================================================================

    @SuppressWarnings("unchecked")
    public static void menuDirector(Map<String, Object> datos, Director director) {
        Museo museo = (Museo) datos.get("museo");
        List<Museo> museosAsociados = (List<Museo>) datos.get("museosAsociados");
        RestauradorJefe restaurador = (RestauradorJefe) datos.get("restaurador");
        List<Prestamo> prestamos = (List<Prestamo>) datos.get("prestamos");

        while (true) {
            System.out.println("\n========== MENÚ DIRECTOR ==========");
            System.out.println("1. Consultar valoración total del museo");
            System.out.println("2. Gestionar préstamo de obra");
            System.out.println("3. Finalizar préstamo activo");
            System.out.println("4. Consultar estadísticas de restauración");
            System.out.println("5. Ver museos asociados");
            System.out.println("0. Cerrar sesión");
            System.out.println("====================================");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if ("0".equals(opcion)) { director.logout(); break; }

            else if ("1".equals(opcion)) {
                director.consultarValoracionTotal(museo);

            } else if ("2".equals(opcion)) {
                System.out.println("\n--- GESTIONAR PRÉSTAMO ---");
                List<ObraDeArte> obras = museo.listarObras();
                System.out.println("\nObras disponibles:");
                for (int i = 0; i < obras.size(); i++) {
                    ObraDeArte o = obras.get(i);
                    System.out.println("  " + (i + 1) + ". " + o.getTitulo() + " (Estado: " + o.getEstado() + ")");
                }
                try {
                    System.out.print("\nNúmero de la obra a prestar: ");
                    int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    ObraDeArte obraSeleccionada = obras.get(idx);

                    System.out.println("\nMuseos asociados:");
                    for (int i = 0; i < museosAsociados.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + museosAsociados.get(i).getNombre());
                    }
                    System.out.print("Número del museo destino: ");
                    int idxMuseo = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    Museo museoDestino = museosAsociados.get(idxMuseo);

                    System.out.print("Importe del préstamo: $");
                    double importe = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Días de préstamo: ");
                    int dias = Integer.parseInt(scanner.nextLine().trim());

                    LocalDate fechaFin = LocalDate.now().plusDays(dias);
                    Prestamo prestamo = new Prestamo(prestamos.size() + 1, obraSeleccionada,
                            museo, museoDestino, importe, LocalDate.now(), fechaFin);
                    director.gestionarPrestamo(prestamo);
                    prestamos.add(prestamo);
                } catch (Exception e) {
                    System.out.println("Selección o valor inválido.");
                }

            } else if ("3".equals(opcion)) {
                List<Prestamo> activos = new ArrayList<>();
                for (Prestamo p : prestamos) { if ("activo".equals(p.getEstado())) activos.add(p); }
                if (activos.isEmpty()) {
                    System.out.println("No hay préstamos activos.");
                } else {
                    System.out.println("\n--- PRÉSTAMOS ACTIVOS ---");
                    for (int i = 0; i < activos.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + activos.get(i));
                    }
                    try {
                        System.out.print("Número del préstamo a finalizar: ");
                        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        activos.get(idx).finalizarPrestamo();
                    } catch (Exception e) {
                        System.out.println("Selección inválida.");
                    }
                }

            } else if ("4".equals(opcion)) {
                Map<String, Integer> stats = director.consultarEstadisticasRestauracion(restaurador.getRestauraciones());
                System.out.println("\nTotal: " + stats.getOrDefault("total", 0));
                System.out.println("En curso: " + stats.getOrDefault("en_curso", 0));
                System.out.println("Finalizadas: " + stats.getOrDefault("finalizadas", 0));

            } else if ("5".equals(opcion)) {
                System.out.println("\n--- MUSEOS ASOCIADOS ---");
                for (Museo m : museosAsociados) {
                    System.out.println("  - " + m.getNombre() + " (" + m.getDireccion() + ")");
                }
            } else {
                System.out.println("Opción no válida.");
            }

            if (!volverAlMenu()) { director.logout(); break; }
        }
    }

    // ==================================================================
    // MENÚ DEL ENCARGADO DE CATÁLOGO
    // ==================================================================

    @SuppressWarnings("unchecked")
    public static void menuEncargado(Map<String, Object> datos, EncargadoCatalogo encargado) {
        Catalogo catalogo = (Catalogo) datos.get("catalogo");
        Museo museo = (Museo) datos.get("museo");
        List<ObraDeArte> obras = (List<ObraDeArte>) datos.get("obras");

        while (true) {
            System.out.println("\n======= MENÚ ENCARGADO CATÁLOGO =======");
            System.out.println("1. Ver catálogo completo");
            System.out.println("2. Buscar obra por título");
            System.out.println("3. Registrar nuevo cuadro");
            System.out.println("4. Registrar nueva escultura");
            System.out.println("5. Registrar otro objeto");
            System.out.println("0. Cerrar sesión");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if ("0".equals(opcion)) { encargado.logout(); break; }

            else if ("1".equals(opcion)) {
                encargado.actualizarCatalogo(catalogo);

            } else if ("2".equals(opcion)) {
                System.out.print("Título a buscar: ");
                String titulo = scanner.nextLine().trim();
                ObraDeArte resultado = catalogo.buscarObra(titulo);
                if (resultado != null) System.out.println(resultado.consultarInfo());

            } else if ("3".equals(opcion)) {
                System.out.println("\n--- REGISTRAR CUADRO ---");
                try {
                    System.out.print("Título: "); String titulo = scanner.nextLine().trim();
                    System.out.print("Autor: "); String autor = scanner.nextLine().trim();
                    System.out.print("Periodo: "); String periodo = scanner.nextLine().trim();
                    System.out.print("Estilo: "); String estilo = scanner.nextLine().trim();
                    System.out.print("Técnica: "); String tecnica = scanner.nextLine().trim();
                    System.out.print("Valoración: $"); double valoracion = Double.parseDouble(scanner.nextLine().trim());
                    Cuadro nuevaObra = new Cuadro(obras.size() + 1, titulo, autor, periodo,
                            valoracion, LocalDate.now(), LocalDate.now(), estilo, tecnica);
                    encargado.registrarObra(catalogo, nuevaObra);
                    museo.agregarObra(nuevaObra);
                    obras.add(nuevaObra);
                } catch (Exception e) { System.out.println("Valor inválido."); }

            } else if ("4".equals(opcion)) {
                System.out.println("\n--- REGISTRAR ESCULTURA ---");
                try {
                    System.out.print("Título: "); String titulo = scanner.nextLine().trim();
                    System.out.print("Autor: "); String autor = scanner.nextLine().trim();
                    System.out.print("Periodo: "); String periodo = scanner.nextLine().trim();
                    System.out.print("Estilo: "); String estilo = scanner.nextLine().trim();
                    System.out.print("Material: "); String material = scanner.nextLine().trim();
                    System.out.print("Valoración: $"); double valoracion = Double.parseDouble(scanner.nextLine().trim());
                    Escultura nuevaObra = new Escultura(obras.size() + 1, titulo, autor, periodo,
                            valoracion, LocalDate.now(), LocalDate.now(), estilo, material);
                    encargado.registrarObra(catalogo, nuevaObra);
                    museo.agregarObra(nuevaObra);
                    obras.add(nuevaObra);
                } catch (Exception e) { System.out.println("Valor inválido."); }

            } else if ("5".equals(opcion)) {
                System.out.println("\n--- REGISTRAR OTRO OBJETO ---");
                try {
                    System.out.print("Título: "); String titulo = scanner.nextLine().trim();
                    System.out.print("Autor: "); String autor = scanner.nextLine().trim();
                    System.out.print("Periodo: "); String periodo = scanner.nextLine().trim();
                    System.out.print("Descripción: "); String descripcion = scanner.nextLine().trim();
                    System.out.print("Valoración: $"); double valoracion = Double.parseDouble(scanner.nextLine().trim());
                    OtroObjeto nuevaObra = new OtroObjeto(obras.size() + 1, titulo, autor, periodo,
                            valoracion, LocalDate.now(), LocalDate.now(), descripcion);
                    encargado.registrarObra(catalogo, nuevaObra);
                    museo.agregarObra(nuevaObra);
                    obras.add(nuevaObra);
                } catch (Exception e) { System.out.println("Valor inválido."); }

            } else {
                System.out.println("Opción no válida.");
            }

            if (!volverAlMenu()) { encargado.logout(); break; }
        }
    }

    // ==================================================================
    // MENÚ DEL RESTAURADOR JEFE
    // ==================================================================

    @SuppressWarnings("unchecked")
    public static void menuRestaurador(Map<String, Object> datos, RestauradorJefe restaurador) {
        Museo museo = (Museo) datos.get("museo");
        int[] idRestauracion = {1};

        while (true) {
            System.out.println("\n======= MENÚ RESTAURADOR JEFE =======");
            System.out.println("1. Iniciar restauración de emergencia");
            System.out.println("2. Finalizar restauración en curso");
            System.out.println("3. Consultar historial de restauraciones");
            System.out.println("4. Verificar obras para restauración (5 años)");
            System.out.println("5. Ejecutar restauración automática");
            System.out.println("0. Cerrar sesión");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if ("0".equals(opcion)) { restaurador.logout(); break; }

            else if ("1".equals(opcion)) {
                System.out.println("\n--- RESTAURACIÓN DE EMERGENCIA ---");
                List<ObraDeArte> obras = museo.listarObras();
                System.out.println("\nObras del museo:");
                for (int i = 0; i < obras.size(); i++) {
                    ObraDeArte o = obras.get(i);
                    System.out.println("  " + (i + 1) + ". " + o.getTitulo() + " (Estado: " + o.getEstado() + ")");
                }
                try {
                    System.out.print("\nNúmero de la obra: ");
                    int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    System.out.print("Tipo (emergencia/preventiva): ");
                    String tipo = scanner.nextLine().trim();
                    Restauracion restauracion = new Restauracion(idRestauracion[0]++,
                            obras.get(idx), tipo, LocalDate.now());
                    restaurador.iniciarRestauracion(restauracion);
                } catch (Exception e) { System.out.println("Selección inválida."); }

            } else if ("2".equals(opcion)) {
                System.out.println("\n--- FINALIZAR RESTAURACIÓN ---");
                List<Restauracion> restauraciones = restaurador.getRestauraciones();
                List<Restauracion> enCurso = new ArrayList<>();
                for (Restauracion r : restauraciones) { if ("en_curso".equals(r.getEstado())) enCurso.add(r); }
                if (enCurso.isEmpty()) {
                    System.out.println("No hay restauraciones en curso.");
                } else {
                    for (int i = 0; i < enCurso.size(); i++) {
                        Restauracion r = enCurso.get(i);
                        System.out.println("  " + (i + 1) + ". " + r.getObra().getTitulo() + " - " + r.getTipoRestauracion());
                    }
                    try {
                        System.out.print("Número de la restauración: ");
                        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        restaurador.finalizarRestauracion(enCurso.get(idx), LocalDate.now());
                    } catch (Exception e) { System.out.println("Selección inválida."); }
                }

            } else if ("3".equals(opcion)) {
                System.out.println("\n--- HISTORIAL DE RESTAURACIONES ---");
                List<ObraDeArte> obras = museo.listarObras();
                for (int i = 0; i < obras.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + obras.get(i).getTitulo());
                }
                try {
                    System.out.print("Número de la obra: ");
                    int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    restaurador.consultarRestauraciones(obras.get(idx));
                } catch (Exception e) { System.out.println("Selección inválida."); }

            } else if ("4".equals(opcion)) {
                System.out.println("\n--- VERIFICACIÓN AUTOMÁTICA ---");
                List<ObraDeArte> obrasRest = restaurador.verificarObrasParaRestauracion(museo.getObras());
                if (!obrasRest.isEmpty()) {
                    System.out.println("\n" + obrasRest.size() + " obra(s) necesitan restauración.");
                    for (ObraDeArte o : obrasRest) System.out.println("  - " + o.getTitulo());
                } else {
                    System.out.println("Ninguna obra requiere restauración.");
                }

            } else if ("5".equals(opcion)) {
                System.out.println("\n--- RESTAURACIÓN AUTOMÁTICA ---");
                ProcesoRestauracionAutomatica proceso = new ProcesoRestauracionAutomatica(restaurador.getRestauraciones());
                List<ObraDeArte> obrasRest = proceso.verificarObrasParaRestauracion(museo.getObras());
                if (!obrasRest.isEmpty()) {
                    System.out.print("¿Enviar a restauración? (s/n): ");
                    if ("s".equalsIgnoreCase(scanner.nextLine().trim())) {
                        proceso.actualizarEstadoObras(obrasRest);
                    }
                } else {
                    System.out.println("No hay obras que requieran restauración.");
                }
            } else {
                System.out.println("Opción no válida.");
            }

            if (!volverAlMenu()) { restaurador.logout(); break; }
        }
    }

    // ==================================================================
    // MENÚ DEL VISITANTE
    // ==================================================================

    @SuppressWarnings("unchecked")
    public static void menuVisitante(Map<String, Object> datos) {
        List<Sala> salas = (List<Sala>) datos.get("salas");
        Visitante visitante = new Visitante();

        while (true) {
            System.out.println("\n========== MENÚ VISITANTE ==========");
            System.out.println("1. Consultar obras por sala");
            System.out.println("2. Ver todas las salas");
            System.out.println("0. Salir");
            System.out.println("====================================");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if ("0".equals(opcion)) { System.out.println("Gracias por su visita."); break; }

            else if ("1".equals(opcion)) {
                System.out.println("\nSalas disponibles:");
                for (int i = 0; i < salas.size(); i++) {
                    Sala s = salas.get(i);
                    System.out.println("  " + (i + 1) + ". " + s.getNombre() + " (" + s.getUbicacion() + ")");
                }
                try {
                    System.out.print("Número de la sala: ");
                    int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    visitante.consultarObrasPorSala(salas.get(idx));
                } catch (Exception e) { System.out.println("Selección inválida."); }

            } else if ("2".equals(opcion)) {
                System.out.println("\n--- TODAS LAS SALAS ---");
                for (Sala sala : salas) visitante.consultarObrasPorSala(sala);

            } else {
                System.out.println("Opción no válida.");
            }

            if (!volverAlMenu()) { System.out.println("Gracias por su visita."); break; }
        }
    }

    // ==================================================================
    // FUNCIÓN PRINCIPAL
    // ==================================================================

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  SISTEMA DE GESTIÓN - MUSEO NACIONAL DE ARTE");
        System.out.println("=".repeat(50));

        Map<String, Object> datos = crearDatosIniciales();

        while (true) {
            System.out.println("\n========== INICIO DE SESIÓN ==========");
            Object[] resultado = autenticar(datos);
            String rol = (String) resultado[0];
            Object usuario = resultado[1];

            if (rol == null) {
                System.out.print("¿Reintentar? (s/n): ");
                if (!"s".equalsIgnoreCase(scanner.nextLine().trim())) break;
                continue;
            }

            switch (rol) {
                case "director": menuDirector(datos, (Director) usuario); break;
                case "encargado": menuEncargado(datos, (EncargadoCatalogo) usuario); break;
                case "restaurador": menuRestaurador(datos, (RestauradorJefe) usuario); break;
                case "visitante": menuVisitante(datos); break;
            }

            System.out.print("\n¿Iniciar sesión con otro usuario? (s/n): ");
            if (!"s".equalsIgnoreCase(scanner.nextLine().trim())) break;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  SISTEMA FINALIZADO. ¡HASTA PRONTO!");
        System.out.println("=".repeat(50) + "\n");
    }
}
