"""Programa principal del sistema TeleVentas."""

from datetime import date

from TeleVentas.Clases.cliente import Cliente
from TeleVentas.Clases.direccion import Direccion
from TeleVentas.Clases.producto import Producto
from TeleVentas.Clases.catalogo import Catalogo
from TeleVentas.Clases.detalle_pedido import DetallePedido
from TeleVentas.Clases.orden_compra import OrdenDeCompra
from TeleVentas.Clases.tarjeta_credito import TarjetaDeCredito
from TeleVentas.Clases.pedido import Pedido
from TeleVentas.Clases.queja import Queja
from TeleVentas.Clases.agente_deposito import AgenteDeposito
from TeleVentas.Clases.gerente_relaciones import GerenteRelaciones


def mostrar_usuarios_prueba():
    print("\n========== USUARIOS DISPONIBLES ==========")
    print("1. Cliente")
    print("   Usuario: Diunis Perez")
    print("   Contraseña: 1234")
    print()
    print("2. Agente de Depósito")
    print("   Usuario: Carlos")
    print("   Contraseña: 5678")
    print()
    print("3. Gerente de Relaciones")
    print("   Usuario: Laura")
    print("   Contraseña: abcd")
    print("==========================================\n")


def autenticar():
    usuarios_validos = {
        "Diunis Perez": {
            "contrasena": "1234", "rol": "Cliente",
        },
        "Carlos": {
            "contrasena": "5678", "rol": "Agente de Depósito",
        },
        "Laura": {
            "contrasena": "abcd", "rol": "Gerente de Relaciones",
        },
    }

    print("===== INICIO DE SESIÓN =====")
    mostrar_usuarios_prueba()

    usuario = input("Usuario: ").strip()
    contrasena = input("Contraseña: ").strip()

    if (usuario in usuarios_validos
            and usuarios_validos[usuario]["contrasena"]
            == contrasena):
        print(f"\nBienvenido/a {usuario}")
        print(f"Rol: {usuarios_validos[usuario]['rol']}")
        return usuario, usuarios_validos[usuario]["rol"]

    print("\nUsuario o contraseña incorrectos.")
    return None, None


def mostrar_menu(rol):
    print("\n========== MENÚ TELEVENTAS ==========")
    print(f"Rol activo: {rol}")
    print("1. Ver catálogo")
    print("2. Buscar producto por código")
    print("3. Agregar producto al catálogo")
    print("4. Comprar producto y pagar")
    print("5. Crear pedido")
    print("6. Presentar queja")
    print("7. Ver datos del cliente")
    print("8. Ver resumen general del sistema")
    print("0. Salir")
    print("====================================")


def volver_al_menu():
    respuesta = input(
        "\n¿Desea volver al menú principal? (s/n): "
    ).strip().lower()
    return respuesta == "s"


def tiene_permiso(rol, opcion):
    permisos = {
        "Cliente": ["1", "2", "4", "6", "7", "8", "0"],
        "Agente de Depósito": ["1", "2", "5", "7", "8", "0"],
        "Gerente de Relaciones": ["1", "2", "3", "7", "8", "0"],
    }
    return opcion in permisos.get(rol, [])


def mostrar_resumen(cliente, direccion, orden_actual,
                    pago_actual, pedido_actual,
                    queja_actual):
    print("\n========== RESUMEN DEL SISTEMA ==========")

    print("\n--- Cliente ---")
    print("Nombre:", cliente.get_nombre())
    print("Email:", cliente.get_email())
    print(
        "Dirección:", direccion.obtener_direccion_completa()
    )

    print("\n--- Orden actual ---")
    if orden_actual is None:
        print("No hay orden creada.")
    else:
        print(orden_actual)

    print("\n--- Pago actual ---")
    if pago_actual is None:
        print("No hay pago registrado.")
    else:
        try:
            print(
                "Estado del pago:",
                pago_actual.obtener_estado(),
            )
        except Exception:
            print(pago_actual)

    print("\n--- Pedido actual ---")
    if pedido_actual is None:
        print("No hay pedido creado.")
    else:
        print(pedido_actual)

    print("\n--- Queja actual ---")
    if queja_actual is None:
        print("No hay quejas registradas.")
    else:
        print(queja_actual)

    print("========================================")


def main():
    print("===== SISTEMA TELEVENTAS =====")

    usuario_autenticado, rol = autenticar()
    if usuario_autenticado is None:
        return

    # ===== OBJETOS DEL SISTEMA =====
    cliente = Cliente(
        1,
        "Diunis Perez",
        "diunis@mail.com",
        "1234",
        "3001234567",
        "2026-03-24",
        "Activo"
    )

    direccion = Direccion(
        "Calle 123",
        "Bogotá",
        "Cundinamarca",
        "110111",
        "Colombia"
    )

    agente = AgenteDeposito(
        2,
        "Carlos",
        "carlos@mail.com",
        "5678",
        "Deposito Central"
    )

    gerente = GerenteRelaciones(
        3,
        "Laura",
        "laura@mail.com",
        "abcd"
    )

    catalogo = Catalogo()

    # ===== PRODUCTOS INICIALES =====
    producto1 = Producto(
        1, "TV001", "Televisor 50 pulgadas", 1800000.0, 5,
    )
    producto2 = Producto(
        2, "AUD001", "Audifonos inalambricos", 250000.0, 10,
    )

    catalogo.agregar_producto(producto1)
    catalogo.agregar_producto(producto2)

    # ===== VARIABLES DE PROCESO =====
    detalle_actual = None
    orden_actual = None
    pago_actual = None
    pedido_actual = None
    queja_actual = None

    while True:
        mostrar_menu(rol)
        opcion = input("Seleccione una opción: ").strip()

        if opcion == "0":
            print("Saliendo del sistema...")
            break

        if not tiene_permiso(rol, opcion):
            print(
                "\nError: su rol no tiene permiso "
                "para realizar esta acción."
            )
            if not volver_al_menu():
                print("Saliendo del sistema...")
                break
            continue

        if opcion == "1":
            print("\n--- CATÁLOGO ---")
            catalogo.listar_productos()

        elif opcion == "2":
            print("\n--- PRODUCTOS DISPONIBLES ---")
            catalogo.listar_productos()

            codigo = input(
                "\nIngrese el código del producto: "
            ).strip()
            producto = catalogo.buscar_producto(codigo)

            if producto:
                print("\nProducto encontrado:")
                print(producto)
            else:
                print(
                    "No se encontró un producto con ese código."
                )

        elif opcion == "3":
            try:
                print("\n--- AGREGAR PRODUCTO AL CATÁLOGO ---")
                gerente.login()

                id_producto = int(input("ID del producto: "))
                codigo = input("Código: ").strip()
                descripcion = input("Descripción: ").strip()
                precio = float(input("Precio: "))
                cantidad = int(input("Cantidad: "))

                nuevo_producto = Producto(
                    id_producto, codigo, descripcion,
                    precio, cantidad,
                )
                catalogo.agregar_producto(nuevo_producto)

                print(
                    "Producto agregado correctamente "
                    "al catálogo."
                )

                gerente.logout()

            except ValueError:
                print(
                    "Error: revise los valores numéricos "
                    "ingresados."
                )

        elif opcion == "4":
            try:
                print("\n--- COMPRAR PRODUCTO Y PAGAR ---")
                print("Productos disponibles en el catálogo:")
                catalogo.listar_productos()

                codigo = input(
                    "\nIngrese el código del producto "
                    "que desea comprar: "
                ).strip()
                producto = catalogo.buscar_producto(codigo)

                if producto is None:
                    print(
                        "No se encontró el producto. "
                        "Verifique el código e intente "
                        "nuevamente."
                    )
                else:
                    cantidad = int(
                        input("Ingrese la cantidad: ")
                    )
                    descuento = 0.0

                    detalle_actual = DetallePedido(
                        1,
                        cantidad,
                        producto.get_precio(),
                        descuento
                    )

                    subtotal = detalle_actual.calcular_subtotal()

                    orden_actual = OrdenDeCompra(
                        1,
                        subtotal,
                        date.today(),
                        "Pendiente"
                    )

                    print(
                        "\nOrden de compra creada "
                        "correctamente."
                    )
                    print(
                        "Descuento aplicado "
                        "automáticamente: 0.0"
                    )
                    print("Subtotal calculado:", subtotal)
                    print(
                        "Valor total:",
                        orden_actual.get_valor_total(),
                    )

                    orden_actual.confirmar()

                    print("\n--- DATOS DE LA TARJETA ---")
                    numero = input(
                        "Número de tarjeta: "
                    ).strip()
                    titular = input("Titular: ").strip()
                    fecha_vencimiento = date.fromisoformat(
                        input(
                            "Fecha de vencimiento "
                            "(AAAA-MM-DD): "
                        ).strip()
                    )
                    cvv = input("CVV: ").strip()

                    pago_actual = TarjetaDeCredito(
                        1,
                        orden_actual.get_valor_total(),
                        date.today(),
                        numero,
                        titular,
                        fecha_vencimiento,
                        cvv
                    )

                    pago_actual.validar()
                    pago_actual.autorizar()
                    pago_actual.procesar()

                    print("\nPago procesado correctamente.")
                    print(
                        "Estado del pago:",
                        pago_actual.obtener_estado(),
                    )

            except ValueError:
                print(
                    "Error: revise la cantidad o "
                    "el formato de la fecha."
                )

        elif opcion == "5":
            if orden_actual is None or pago_actual is None:
                print(
                    "Primero debe existir una compra "
                    "pagada para crear el pedido."
                )
            else:
                print("\n--- CREAR PEDIDO ---")

                pedido_actual = Pedido(
                    1,
                    date.today(),
                    "Pendiente",
                    "Servientrega"
                )

                agente.login()
                agente.consultar_ordenes()
                agente.crear_pedido()
                pedido_actual.armar()
                pedido_actual.despachar()
                agente.logout()

                print("Pedido procesado correctamente.")

        elif opcion == "6":
            print("\n--- PRESENTAR QUEJA ---")
            descripcion = input(
                "Escriba la queja: "
            ).strip()

            queja_actual = Queja(
                1,
                descripcion,
                date.today(),
                "Pendiente"
            )

            queja_actual.enviar_a_gerente()

            try:
                gerente.login()
                print(
                    "El gerente de relaciones "
                    "fue notificado."
                )
                gerente.logout()
            except Exception:
                print("La queja fue enviada al gerente.")

            print("Queja registrada correctamente.")

        elif opcion == "7":
            print("\n--- DATOS DEL CLIENTE ---")
            print("Nombre:", cliente.get_nombre())
            print("Email:", cliente.get_email())
            print(
                "Dirección:",
                direccion.obtener_direccion_completa(),
            )
            print(
                "Usuario autenticado:",
                usuario_autenticado,
            )
            print("Rol activo:", rol)

        elif opcion == "8":
            mostrar_resumen(
                cliente,
                direccion,
                orden_actual,
                pago_actual,
                pedido_actual,
                queja_actual
            )

        else:
            print("Opción no válida. Intente nuevamente.")

        if not volver_al_menu():
            print("Saliendo del sistema...")
            break


if __name__ == "__main__":
    main()
