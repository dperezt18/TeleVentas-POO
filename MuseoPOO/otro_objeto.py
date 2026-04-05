"""Módulo que define la clase OtroObjeto."""

from datetime import date

from obra_de_arte import ObraDeArte


class OtroObjeto(ObraDeArte):
    """Representa otro tipo de objeto artístico del museo."""

    def __init__(
        self,
        id_obra: int,
        titulo: str,
        autor: str,
        periodo: str,
        valoracion: float,
        fecha_creacion: date,
        fecha_entrada: date,
        descripcion: str,
        estado: str = "expuesta",
    ) -> None:
        super().__init__(
            id_obra, titulo, autor, periodo,
            valoracion, fecha_creacion, fecha_entrada, estado,
        )
        self._descripcion = descripcion

    def get_descripcion(self) -> str:
        return self._descripcion

    def consultar_info(self) -> str:
        return (
            f"Objeto: '{self._titulo}' | Autor: {self._autor} | "
            f"Periodo: {self._periodo} | "
            f"Descripción: {self._descripcion} | "
            f"Valoración: ${self._valoracion:,.2f} | "
            f"Estado: {self._estado}"
        )
