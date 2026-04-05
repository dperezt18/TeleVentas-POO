"""Módulo que define la clase Restauracion."""

from __future__ import annotations

from datetime import date
from typing import TYPE_CHECKING, Optional

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte


class Restauracion:
    """Representa un proceso de restauración de una obra."""

    def __init__(
        self,
        id_restauracion: int,
        obra: ObraDeArte,
        tipo_restauracion: str,
        fecha_inicio: date,
    ) -> None:
        self._id_restauracion = id_restauracion
        self._obra = obra
        self._tipo_restauracion = tipo_restauracion
        self._fecha_inicio = fecha_inicio
        self._fecha_fin: Optional[date] = None
        self._estado = "en_curso"

    def get_id_restauracion(self) -> int:
        return self._id_restauracion

    def get_obra(self) -> ObraDeArte:
        return self._obra

    def get_tipo_restauracion(self) -> str:
        return self._tipo_restauracion

    def get_fecha_inicio(self) -> date:
        return self._fecha_inicio

    def get_fecha_fin(self) -> Optional[date]:
        return self._fecha_fin

    def get_estado(self) -> str:
        return self._estado

    def registrar_inicio(self) -> None:
        """Registra el inicio de la restauración."""
        self._obra.set_estado("en_restauracion")
        self._estado = "en_curso"
        print(
            f"Restauración iniciada para "
            f"'{self._obra.get_titulo()}' "
            f"- Tipo: {self._tipo_restauracion}."
        )

    def registrar_fin(self, fecha_fin: Optional[date] = None) -> None:
        """Registra la finalización de la restauración."""
        if self._estado != "en_curso":
            print("Esta restauración ya fue finalizada.")
            return
        self._fecha_fin = fecha_fin or date.today()
        self._estado = "finalizada"
        self._obra.set_estado("expuesta")
        print(
            f"Restauración finalizada para "
            f"'{self._obra.get_titulo()}' el {self._fecha_fin}."
        )

    def __str__(self) -> str:
        return (
            f"Restauracion(id={self._id_restauracion}, "
            f"obra='{self._obra.get_titulo()}', "
            f"tipo='{self._tipo_restauracion}', "
            f"estado='{self._estado}')"
        )
