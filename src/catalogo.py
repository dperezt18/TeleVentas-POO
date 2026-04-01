"""Módulo que define la clase Catalogo."""


class Catalogo:
    """Representa el catálogo de productos de TeleVentas."""

    def __init__(self):
        self._productos = []

    def listar_productos(self):
        for producto in self._productos:
            print(producto)

    def agregar_producto(self, producto):
        self._productos.append(producto)

    def buscar_producto(self, codigo):
        for producto in self._productos:
            if producto.get_codigo() == codigo:
                return producto
        return None

    def enviar_por_correo(self, correo):
        print(f"El catálogo fue enviado al correo {correo}")
