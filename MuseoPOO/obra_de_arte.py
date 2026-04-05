"""Módulo que define la clase abstracta ObraDeArte."""

from abc import ABC, abstractmethod
from datetime import date


class ObraDeArte(ABC):
    """Clase abstracta que representa una obra de arte."""

    ESTADOS_VALIDOS = ("expuesta", "en_restauracion", "cedida")

    def __init__(
        self,
        id_obra: int,
        titulo: str,
        autor: str,
        periodo: str,
        valoracion: float,
        fecha_creacion: date,
        fecha_entrada: date,
        estado: str = "expuesta",
    ) -> None:
        self._id_obra = id_obra
        self._titulo = titulo
        self._autor = autor
        self._periodo = periodo
        self._valoracion = valoracion
        self._fecha_creacion = fecha_creacion
        self._fecha_entrada = fecha_entrada
        self._estado = estado

    def get_id_obra(self) -> int:
        return self._id_obra

    def get_titulo(self) -> str:
        return self._titulo

    def get_autor(self) -> str:
        return self._autor

    def get_periodo(self) -> str:
        return self._periodo

    def get_valoracion(self) -> float:
        return self._valoracion

    def get_fecha_creacion(self) -> date:
        return self._fecha_creacion

    def get_fecha_entrada(self) -> date:
        return self._fecha_entrada

    def get_estado(self) -> str:
        return self._estado

    def set_estado(self, nuevo_estado: str) -> None:
        if nuevo_estado not in self.ESTADOS_VALIDOS:
            raise ValueError(
                f"Estado inválido: '{nuevo_estado}'. "
                f"Debe ser uno de {self.ESTADOS_VALIDOS}."
            )
        self._estado = nuevo_estado

    @abstractmethod
    def consultar_info(self) -> str:
        """Retorna información detallada de la obra de arte."""

    def __str__(self) -> str:
        return (
            f"{self.__class__.__name__}(id={self._id_obra}, "
            f"titulo='{self._titulo}', estado='{self._estado}')"
        )
