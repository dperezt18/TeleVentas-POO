from abc import ABC, abstractmethod
from datetime import date

class Pago(ABC):
    def __init__(self, id_pago: int, monto: float, fecha: date):
        self.__id_pago = id_pago
        self.__monto = monto
        self.__fecha = fecha

    @abstractmethod
    def procesar(self) -> bool:
        pass

    @abstractmethod
    def obtener_estado(self) -> str:
        pass

#getter
    def get_id_pago(self) -> int:
        return self.__id_pago

    def get_monto(self) -> float:
        return self.__monto

    def get_fecha(self) -> date:
        return self.__fecha


