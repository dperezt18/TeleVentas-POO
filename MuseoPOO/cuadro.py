"""Módulo que define la clase Cuadro."""

from datetime import date

from obra_de_arte import ObraDeArte


class Cuadro(ObraDeArte):
    """Representa un cuadro del museo."""

    def __init__(
        self,
        id_obra: int,
        titulo: str,
        autor: str,
        periodo: str,
        valoracion: float,
        fecha_creacion: date,
        fecha_entrada: date,
        estilo: str,
        tecnica: str,
        estado: str = "expuesta",
    ) -> None:
        super().__init__(
            id_obra, titulo, autor, periodo,
            valoracion, fecha_creacion, fecha_entrada, estado,
        )
        self._estilo = estilo
        self._tecnica = tecnica

    def get_estilo(self) -> str:
        return self._estilo

    def get_tecnica(self) -> str:
        return self._tecnica

    def consultar_info(self) -> str:
        return (
            f"Cuadro: '{self._titulo}' | Autor: {self._autor} | "
            f"Periodo: {self._periodo} | Estilo: {self._estilo} | "
            f"Técnica: {self._tecnica} | "
            f"Valoración: ${self._valoracion:,.2f} | "
            f"Estado: {self._estado}"
        )
