from datetime import date

class Pedido:
    def __init__(self, id_pedido: int, fecha: date, estado: str, empresa_transporte: str):
        self.__id_pedido = id_pedido
        self.__fecha = fecha
        self.__estado = estado
        self.__empresa_transporte = empresa_transporte

    def armar(self):
        self.__estado = "Armado"
        print("El pedido ha sido armado")

    def despachar(self):
        self.__estado = "Despachado"
        print("El pedido ha sido despachado con éxito")

# --- Getters ---
    def get_id_pedido(self) -> int:
        return self.__id_pedido

    def get_fecha(self) -> date:
        return self.__fecha

    def get_estado(self) -> str:
        return self.__estado

    def get_empresa_transporte(self) -> str:
        return self.__empresa_transporte

