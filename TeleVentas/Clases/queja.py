"""Módulo que define la clase Queja."""

from datetime import date


class Queja:
    """Representa una queja de un cliente."""

    def __init__(self, id_queja: int, descripcion: str,
                 fecha: date, estado: str):
        self.__id_queja = id_queja
        self.__descripcion = descripcion
        self.__fecha = fecha
        self.__estado = estado

    def enviar_a_gerente(self):
        self.__estado = 'Enviada al gerente'
        print('La queja fue enviada correctamente al gerente')

    # --- Getters ---
    def get_id_queja(self) -> int:
        return self.__id_queja

    def get_descripcion(self) -> str:
        return self.__descripcion

    def get_fecha(self) -> date:
        return self.__fecha

    def get_estado(self) -> str:
        return self.__estado
