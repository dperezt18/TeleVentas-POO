"""Módulo que define la clase TarjetaDeCredito."""

from TeleVentas.Clases.pago import Pago
from datetime import date


class TarjetaDeCredito(Pago):
    """Representa un pago con tarjeta de crédito."""

    def __init__(self, id_pago: int, monto: float,
                 fecha: date, numero: str, titular: str,
                 fecha_vencimiento: date, cvv: str):
        super().__init__(id_pago, monto, fecha)
        self.__numero = numero
        self.__titular = titular
        self.__fecha_vencimiento = fecha_vencimiento
        self.__cvv = cvv

    def procesar(self) -> bool:
        print("Pago procesado correctamente")
        return True

    def obtener_estado(self) -> str:
        return "Aprobado"

    def validar(self) -> bool:
        print("Tarjeta validada correctamente")
        return True

    def autorizar(self) -> bool:
        print("Pago autorizado")
        return True
