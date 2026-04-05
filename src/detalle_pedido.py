"""Módulo que define la clase DetallePedido."""


class DetallePedido:
    """Representa el detalle de un pedido."""

    def __init__(self, id_detalle: int, cantidad: int,
                 precio_unitario: float, descuento: float):
        self.__id_detalle = id_detalle
        self.__cantidad = cantidad
        self.__precio_unitario = precio_unitario
        self.__descuento = descuento

    def calcular_subtotal(self) -> float:
        return self.__cantidad * self.__precio_unitario - self.__descuento

    # --- Getters ---
    def get_id_detalle(self) -> int:
        return self.__id_detalle

    def get_cantidad(self) -> int:
        return self.__cantidad

    def get_precio_unitario(self) -> float:
        return self.__precio_unitario

    def get_descuento(self) -> float:
        return self.__descuento
