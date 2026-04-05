"""Programa principal interactivo del sistema de gestión del museo."""

from datetime import date

from catalogo import Catalogo
from cuadro import Cuadro
from director import Director
from encargado_catalogo import EncargadoCatalogo
from escultura import Escultura
from museo import Museo
from otro_objeto import OtroObjeto
from prestamo import Prestamo
from proceso_restauracion_automatica import (
    ProcesoRestauracionAutomatica,
)
from restauracion import Restauracion
from restaurador_jefe import RestauradorJefe
from sala import Sala
from visitante import Visitante


# ==================================================================
# DATOS PRECARGADOS DEL SISTEMA
# ==================================================================

def crear_datos_iniciales():
    """Crea y retorna todos los objetos iniciales del sistema."""
    # --- Museos ---
    museo_principal = Museo(1, "Museo Nacional de Arte", "Calle 123, Bogotá")
    museo_asociado_1 = Museo(2, "Museo de Arte Moderno", "Av Reforma 456, México")
    museo_asociado_2 = Museo(3, "Museo del Prado", "Calle Ruiz 23, Madrid")
    museo_principal.agregar_museo_asociado(museo_asociado_1)
    museo_principal.agregar_museo_asociado(museo_asociado_2)

    # --- Salas ---
    sala_pintura = Sala(1, "Sala de Pintura Clásica", "Piso 1 - Ala Norte")
    sala_escultura = Sala(2, "Sala de Esculturas", "Piso 1 - Ala Sur")
    sala_contemporaneo = Sala(3, "Sala de Arte Contemporáneo", "Piso 2")

    # --- Obras ---
    cuadro_1 = Cuadro(
        1, "La Última Cena", "Leonardo da Vinci", "Renacimiento",
        450_000_000.00, date(1498, 1, 1), date(2018, 6, 15),
        "Renacimiento italiano", "Temple y óleo sobre yeso",
    )
    cuadro_2 = Cuadro(
        2, "La Noche Estrellada", "Vincent van Gogh", "Postimpresionismo",
        100_000_000.00, date(1889, 6, 1), date(2019, 3, 20),
        "Postimpresionista", "Óleo sobre lienzo",
    )
    escultura_1 = Escultura(
        3, "El Pensador", "Auguste Rodin", "Impresionismo",
        15_000_000.00, date(1904, 1, 1), date(2017, 9, 10),
        "Impresionista", "Bronce",
    )
    escultura_2 = Escultura(
        4, "David", "Miguel Ángel", "Renacimiento",
        200_000_000.00, date(1504, 1, 1), date(2020, 1, 5),
        "Renacentista", "Mármol blanco",
    )
    otro_objeto = OtroObjeto(
        5, "Vasija Ceremonial Muisca", "Artesano Muisca", "Precolombino",
        5_000_000.00, date(1200, 1, 1), date(2020, 11, 1),
        "Vasija de cerámica con motivos geométricos rituales",
    )

    obras = [cuadro_1, cuadro_2, escultura_1, escultura_2, otro_objeto]

    # --- Registrar obras en museo y catálogo ---
    catalogo = Catalogo()
    for obra in obras:
        museo_principal.agregar_obra(obra)
        catalogo.agregar_obra(obra)

    # --- Asignar obras a salas ---
    sala_pintura.agregar_obra(cuadro_1)
    sala_pintura.agregar_obra(cuadro_2)
    sala_escultura.agregar_obra(escultura_1)
    sala_escultura.agregar_obra(escultura_2)
    sala_contemporaneo.agregar_obra(otro_objeto)

    # --- Usuarios ---
    director = Director(
        1, "Carlos Méndez", "carlos@museo.edu.co", "dir123",
    )
    encargado = EncargadoCatalogo(
        2, "Laura Gómez", "laura@museo.edu.co", "cat123",
    )
    restaurador = RestauradorJefe(
        3, "Pedro Ramírez", "pedro@museo.edu.co", "res123",
    )

    salas = [sala_pintura, sala_escultura, sala_contemporaneo]
    museos_asociados = [museo_asociado_1, museo_asociado_2]

    return {
        "museo": museo_principal,
        "museos_asociados": museos_asociados,
        "salas": salas,
        "obras": obras,
        "catalogo": catalogo,
        "director": director,
        "encargado": encargado,
        "restaurador": restaurador,
        "prestamos": [],
    }


# ==================================================================
# FUNCIONES DE MENÚ
# ==================================================================

def mostrar_usuarios():
    """Muestra los usuarios disponibles para login."""
    print("\n========== USUARIOS DISPONIBLES ==========")
    print("1. Director       | Carlos Méndez  | Clave: dir123")
    print("2. Encargado Cat. | Laura Gómez    | Clave: cat123")
    print("3. Restaurador    | Pedro Ramírez  | Clave: res123")
    print("4. Visitante      | (sin login)")
    print("==========================================\n")


def autenticar(datos):
    """Autentica un usuario y retorna su rol."""
    mostrar_usuarios()
    opcion = input("Seleccione su rol (1-4): ").strip()

    if opcion == "4":
        print("\nBienvenido, visitante.")
        return "visitante", None

    usuarios = {
        "1": ("director", datos["director"]),
        "2": ("encargado", datos["encargado"]),
        "3": ("restaurador", datos["restaurador"]),
    }

    if opcion not in usuarios:
        print("Opción inválida.")
        return None, None

    rol, usuario = usuarios[opcion]
    contrasena = input("Contraseña: ").strip()

    if usuario.login(contrasena):
        return rol, usuario

    return None, None


def volver_al_menu():
    """Pregunta si desea volver al menú."""
    respuesta = input(
        "\n¿Volver al menú? (s/n): "
    ).strip().lower()
    return respuesta == "s"


# ==================================================================
# MENÚ DEL DIRECTOR
# ==================================================================

def menu_director(datos, director):
    """Menú interactivo para el Director."""
    museo = datos["museo"]
    museos_asociados = datos["museos_asociados"]
    restaurador = datos["restaurador"]
    id_prestamo = len(datos["prestamos"]) + 1

    while True:
        print("\n========== MENÚ DIRECTOR ==========")
        print("1. Consultar valoración total del museo")
        print("2. Gestionar préstamo de obra")
        print("3. Finalizar préstamo activo")
        print("4. Consultar estadísticas de restauración")
        print("5. Ver museos asociados")
        print("0. Cerrar sesión")
        print("====================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "0":
            director.logout()
            break

        elif opcion == "1":
            director.consultar_valoracion_total(museo)

        elif opcion == "2":
            print("\n--- GESTIONAR PRÉSTAMO ---")
            obras = museo.listar_obras()
            print("\nObras disponibles:")
            for i, obra in enumerate(obras):
                print(
                    f"  {i + 1}. {obra.get_titulo()} "
                    f"(Estado: {obra.get_estado()})"
                )

            try:
                idx = int(
                    input("\nNúmero de la obra a prestar: ")
                ) - 1
                obra_seleccionada = obras[idx]
            except (ValueError, IndexError):
                print("Selección inválida.")
                if not volver_al_menu():
                    director.logout()
                    break
                continue

            print("\nMuseos asociados:")
            for i, m in enumerate(museos_asociados):
                print(f"  {i + 1}. {m.get_nombre()}")

            try:
                idx_museo = int(
                    input("Número del museo destino: ")
                ) - 1
                museo_destino = museos_asociados[idx_museo]
            except (ValueError, IndexError):
                print("Selección inválida.")
                if not volver_al_menu():
                    director.logout()
                    break
                continue

            try:
                importe = float(input("Importe del préstamo: $"))
                dias = int(input("Días de préstamo: "))
            except ValueError:
                print("Valor inválido.")
                if not volver_al_menu():
                    director.logout()
                    break
                continue

            from datetime import timedelta
            fecha_fin = date.today() + timedelta(days=dias)

            prestamo = Prestamo(
                id_prestamo,
                obra_seleccionada,
                museo,
                museo_destino,
                importe,
                date.today(),
                fecha_fin,
            )
            director.gestionar_prestamo(prestamo)
            datos["prestamos"].append(prestamo)
            id_prestamo += 1

        elif opcion == "3":
            activos = [
                p for p in datos["prestamos"]
                if p.get_estado() == "activo"
            ]
            if not activos:
                print("No hay préstamos activos.")
            else:
                print("\n--- PRÉSTAMOS ACTIVOS ---")
                for i, p in enumerate(activos):
                    print(f"  {i + 1}. {p}")
                try:
                    idx = int(
                        input("Número del préstamo a finalizar: ")
                    ) - 1
                    activos[idx].finalizar_prestamo()
                except (ValueError, IndexError):
                    print("Selección inválida.")

        elif opcion == "4":
            restauraciones = restaurador.get_restauraciones()
            stats = director.consultar_estadisticas_restauracion(
                restauraciones,
            )
            print(f"\nTotal: {stats.get('total', 0)}")
            print(f"En curso: {stats.get('en_curso', 0)}")
            print(f"Finalizadas: {stats.get('finalizadas', 0)}")

        elif opcion == "5":
            print("\n--- MUSEOS ASOCIADOS ---")
            for m in museos_asociados:
                print(
                    f"  - {m.get_nombre()} "
                    f"({m.get_direccion()})"
                )

        else:
            print("Opción no válida.")

        if not volver_al_menu():
            director.logout()
            break


# ==================================================================
# MENÚ DEL ENCARGADO DE CATÁLOGO
# ==================================================================

def menu_encargado(datos, encargado):
    """Menú interactivo para el Encargado del Catálogo."""
    catalogo = datos["catalogo"]
    museo = datos["museo"]
    id_obra = len(datos["obras"]) + 1

    while True:
        print("\n======= MENÚ ENCARGADO CATÁLOGO =======")
        print("1. Ver catálogo completo")
        print("2. Buscar obra por título")
        print("3. Registrar nuevo cuadro")
        print("4. Registrar nueva escultura")
        print("5. Registrar otro objeto")
        print("0. Cerrar sesión")
        print("========================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "0":
            encargado.logout()
            break

        elif opcion == "1":
            encargado.actualizar_catalogo(catalogo)

        elif opcion == "2":
            titulo = input("Título a buscar: ").strip()
            resultado = catalogo.buscar_obra(titulo)
            if resultado:
                print(resultado.consultar_info())

        elif opcion == "3":
            print("\n--- REGISTRAR CUADRO ---")
            titulo = input("Título: ").strip()
            autor = input("Autor: ").strip()
            periodo = input("Periodo: ").strip()
            estilo = input("Estilo: ").strip()
            tecnica = input("Técnica: ").strip()
            try:
                valoracion = float(input("Valoración: $"))
            except ValueError:
                print("Valor inválido.")
                if not volver_al_menu():
                    encargado.logout()
                    break
                continue

            nueva_obra = Cuadro(
                id_obra, titulo, autor, periodo,
                valoracion, date.today(), date.today(),
                estilo, tecnica,
            )
            encargado.registrar_obra(catalogo, nueva_obra)
            museo.agregar_obra(nueva_obra)
            datos["obras"].append(nueva_obra)
            id_obra += 1

        elif opcion == "4":
            print("\n--- REGISTRAR ESCULTURA ---")
            titulo = input("Título: ").strip()
            autor = input("Autor: ").strip()
            periodo = input("Periodo: ").strip()
            estilo = input("Estilo: ").strip()
            material = input("Material: ").strip()
            try:
                valoracion = float(input("Valoración: $"))
            except ValueError:
                print("Valor inválido.")
                if not volver_al_menu():
                    encargado.logout()
                    break
                continue

            nueva_obra = Escultura(
                id_obra, titulo, autor, periodo,
                valoracion, date.today(), date.today(),
                estilo, material,
            )
            encargado.registrar_obra(catalogo, nueva_obra)
            museo.agregar_obra(nueva_obra)
            datos["obras"].append(nueva_obra)
            id_obra += 1

        elif opcion == "5":
            print("\n--- REGISTRAR OTRO OBJETO ---")
            titulo = input("Título: ").strip()
            autor = input("Autor: ").strip()
            periodo = input("Periodo: ").strip()
            descripcion = input("Descripción: ").strip()
            try:
                valoracion = float(input("Valoración: $"))
            except ValueError:
                print("Valor inválido.")
                if not volver_al_menu():
                    encargado.logout()
                    break
                continue

            nueva_obra = OtroObjeto(
                id_obra, titulo, autor, periodo,
                valoracion, date.today(), date.today(),
                descripcion,
            )
            encargado.registrar_obra(catalogo, nueva_obra)
            museo.agregar_obra(nueva_obra)
            datos["obras"].append(nueva_obra)
            id_obra += 1

        else:
            print("Opción no válida.")

        if not volver_al_menu():
            encargado.logout()
            break


# ==================================================================
# MENÚ DEL RESTAURADOR JEFE
# ==================================================================

def menu_restaurador(datos, restaurador):
    """Menú interactivo para el Restaurador Jefe."""
    museo = datos["museo"]
    id_restauracion = 1

    while True:
        print("\n======= MENÚ RESTAURADOR JEFE =======")
        print("1. Iniciar restauración de emergencia")
        print("2. Finalizar restauración en curso")
        print("3. Consultar historial de restauraciones")
        print("4. Verificar obras para restauración (5 años)")
        print("5. Ejecutar restauración automática")
        print("0. Cerrar sesión")
        print("======================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "0":
            restaurador.logout()
            break

        elif opcion == "1":
            print("\n--- RESTAURACIÓN DE EMERGENCIA ---")
            obras = museo.listar_obras()
            print("\nObras del museo:")
            for i, obra in enumerate(obras):
                print(
                    f"  {i + 1}. {obra.get_titulo()} "
                    f"(Estado: {obra.get_estado()})"
                )

            try:
                idx = int(
                    input("\nNúmero de la obra: ")
                ) - 1
                obra_sel = obras[idx]
            except (ValueError, IndexError):
                print("Selección inválida.")
                if not volver_al_menu():
                    restaurador.logout()
                    break
                continue

            tipo = input(
                "Tipo (emergencia/preventiva): "
            ).strip()

            restauracion = Restauracion(
                id_restauracion,
                obra_sel,
                tipo,
                date.today(),
            )
            restaurador.iniciar_restauracion(restauracion)
            id_restauracion += 1

        elif opcion == "2":
            print("\n--- FINALIZAR RESTAURACIÓN ---")
            restauraciones = restaurador.get_restauraciones()
            en_curso = [
                r for r in restauraciones
                if r.get_estado() == "en_curso"
            ]
            if not en_curso:
                print("No hay restauraciones en curso.")
            else:
                for i, r in enumerate(en_curso):
                    print(
                        f"  {i + 1}. "
                        f"{r.get_obra().get_titulo()} - "
                        f"{r.get_tipo_restauracion()}"
                    )
                try:
                    idx = int(
                        input("Número de la restauración: ")
                    ) - 1
                    restaurador.finalizar_restauracion(
                        en_curso[idx], date.today(),
                    )
                except (ValueError, IndexError):
                    print("Selección inválida.")

        elif opcion == "3":
            print("\n--- HISTORIAL DE RESTAURACIONES ---")
            obras = museo.listar_obras()
            for i, obra in enumerate(obras):
                print(f"  {i + 1}. {obra.get_titulo()}")

            try:
                idx = int(
                    input("Número de la obra: ")
                ) - 1
                restaurador.consultar_restauraciones(
                    obras[idx],
                )
            except (ValueError, IndexError):
                print("Selección inválida.")

        elif opcion == "4":
            print("\n--- VERIFICACIÓN AUTOMÁTICA ---")
            obras_rest = (
                restaurador.verificar_obras_para_restauracion(
                    museo.get_obras(),
                )
            )
            if obras_rest:
                print(
                    f"\n{len(obras_rest)} obra(s) necesitan "
                    f"restauración."
                )
                for obra in obras_rest:
                    print(f"  - {obra.get_titulo()}")
            else:
                print("Ninguna obra requiere restauración.")

        elif opcion == "5":
            print("\n--- RESTAURACIÓN AUTOMÁTICA ---")
            proceso = ProcesoRestauracionAutomatica(
                restauraciones=(
                    restaurador.get_restauraciones()
                ),
            )
            obras_rest = (
                proceso.verificar_obras_para_restauracion(
                    museo.get_obras(),
                )
            )
            if obras_rest:
                confirmar = input(
                    "¿Enviar a restauración? (s/n): "
                ).strip().lower()
                if confirmar == "s":
                    proceso.actualizar_estado_obras(
                        obras_rest,
                    )
            else:
                print("No hay obras que requieran restauración.")

        else:
            print("Opción no válida.")

        if not volver_al_menu():
            restaurador.logout()
            break


# ==================================================================
# MENÚ DEL VISITANTE
# ==================================================================

def menu_visitante(datos):
    """Menú interactivo para el Visitante."""
    salas = datos["salas"]
    visitante = Visitante()

    while True:
        print("\n========== MENÚ VISITANTE ==========")
        print("1. Consultar obras por sala")
        print("2. Ver todas las salas")
        print("0. Salir")
        print("====================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "0":
            print("Gracias por su visita.")
            break

        elif opcion == "1":
            print("\nSalas disponibles:")
            for i, sala in enumerate(salas):
                print(
                    f"  {i + 1}. {sala.get_nombre()} "
                    f"({sala.get_ubicacion()})"
                )

            try:
                idx = int(
                    input("Número de la sala: ")
                ) - 1
                visitante.consultar_obras_por_sala(
                    salas[idx],
                )
            except (ValueError, IndexError):
                print("Selección inválida.")

        elif opcion == "2":
            print("\n--- TODAS LAS SALAS ---")
            for sala in salas:
                visitante.consultar_obras_por_sala(sala)

        else:
            print("Opción no válida.")

        if not volver_al_menu():
            print("Gracias por su visita.")
            break


# ==================================================================
# FUNCIÓN PRINCIPAL
# ==================================================================

def main():
    """Función principal del sistema de gestión del museo."""
    print("\n" + "=" * 50)
    print("  SISTEMA DE GESTIÓN - MUSEO NACIONAL DE ARTE")
    print("=" * 50)

    datos = crear_datos_iniciales()

    while True:
        print("\n========== INICIO DE SESIÓN ==========")
        rol, usuario = autenticar(datos)

        if rol is None:
            reintentar = input(
                "¿Reintentar? (s/n): "
            ).strip().lower()
            if reintentar != "s":
                break
            continue

        if rol == "director":
            menu_director(datos, usuario)
        elif rol == "encargado":
            menu_encargado(datos, usuario)
        elif rol == "restaurador":
            menu_restaurador(datos, usuario)
        elif rol == "visitante":
            menu_visitante(datos)

        continuar = input(
            "\n¿Iniciar sesión con otro usuario? (s/n): "
        ).strip().lower()
        if continuar != "s":
            break

    print("\n" + "=" * 50)
    print("  SISTEMA FINALIZADO. ¡HASTA PRONTO!")
    print("=" * 50 + "\n")


if __name__ == "__main__":
    main()
