"""Módulo que define la clase Escultura."""

from datetime import date

from obra_de_arte import ObraDeArte


class Escultura(ObraDeArte):
    """Representa una escultura del museo."""

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
        material: str,
        estado: str = "expuesta",
    ) -> None:
        super().__init__(
            id_obra, titulo, autor, periodo,
            valoracion, fecha_creacion, fecha_entrada, estado,
        )
        self._estilo = estilo
        self._material = material

    def get_estilo(self) -> str:
        return self._estilo

    def get_material(self) -> str:
        return self._material

    def consultar_info(self) -> str:
        return (
            f"Escultura: '{self._titulo}' | Autor: {self._autor} | "
            f"Periodo: {self._periodo} | Estilo: {self._estilo} | "
            f"Material: {self._material} | "
            f"Valoración: ${self._valoracion:,.2f} | "
            f"Estado: {self._estado}"
        )
