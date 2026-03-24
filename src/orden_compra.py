from datetime import date

class OrdenDeCompra:
    def __init__(self, id_orden: int, valor_total: float, fecha: date, estado: str):
        self.__id_orden = id_orden
        self.__valor_total = valor_total
        self.__fecha = fecha
        self.__estado = estado

    def confirmar(self):
        self.__estado = 'Confirmada'
        print("Su orden ha sido confirmada")

    def cancelar(self):
        self.__estado = 'Cancelada'
        print("Su orden ha sido cancelada")

# --- Getters ---
    def get_id_orden(self) ->int:
        return self.__id_orden

    def get_valor_total(self) ->float:
        return self.__valor_total

    def get_fecha(self) -> date:
        return self.__fecha

    def get_estado(self) -> str:
        return self.__estado




