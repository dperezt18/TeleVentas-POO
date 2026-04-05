"""Módulo que define la clase Prestamo."""

from __future__ import annotations

from datetime import date
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from museo import Museo
    from obra_de_arte import ObraDeArte


class Prestamo:
    """Representa un préstamo/cesión de una obra a otro museo."""

    def __init__(
        self,
        id_prestamo: int,
        obra: ObraDeArte,
        museo_origen: Museo,
        museo_destino: Museo,
        importe: float,
        fecha_inicio: date,
        fecha_fin: date,
    ) -> None:
        self._id_prestamo = id_prestamo
        self._obra = obra
        self._museo_origen = museo_origen
        self._museo_destino = museo_destino
        self._importe = importe
        self._fecha_inicio = fecha_inicio
        self._fecha_fin = fecha_fin
        self._estado = "pendiente"

    def get_id_prestamo(self) -> int:
        return self._id_prestamo

    def get_obra(self) -> ObraDeArte:
        return self._obra

    def get_museo_origen(self) -> Museo:
        return self._museo_origen

    def get_museo_destino(self) -> Museo:
        return self._museo_destino

    def get_importe(self) -> float:
        return self._importe

    def get_fecha_inicio(self) -> date:
        return self._fecha_inicio

    def get_fecha_fin(self) -> date:
        return self._fecha_fin

    def get_estado(self) -> str:
        return self._estado

    def registrar_prestamo(self) -> None:
        """Registra y activa el préstamo."""
        if self._obra.get_estado() == "cedida":
            print(
                f"La obra '{self._obra.get_titulo()}' ya está "
                "cedida. No se puede registrar el préstamo."
            )
            return
        if self._obra.get_estado() == "en_restauracion":
            print(
                f"La obra '{self._obra.get_titulo()}' está en "
                "restauración. No se puede ceder."
            )
            return
        self._obra.set_estado("cedida")
        self._estado = "activo"
        print(
            f"Préstamo registrado: '{self._obra.get_titulo()}' "
            f"cedida de '{self._museo_origen.get_nombre()}' a "
            f"'{self._museo_destino.get_nombre()}' "
            f"por ${self._importe:,.2f}."
        )

    def finalizar_prestamo(self) -> None:
        """Finaliza el préstamo y devuelve la obra."""
        if self._estado != "activo":
            print("El préstamo no está activo.")
            return
        self._obra.set_estado("expuesta")
        self._estado = "finalizado"
        print(
            f"Préstamo finalizado: '{self._obra.get_titulo()}' "
            f"devuelta a '{self._museo_origen.get_nombre()}'."
        )

    def __str__(self) -> str:
        return (
            f"Prestamo(id={self._id_prestamo}, "
            f"obra='{self._obra.get_titulo()}', "
            f"destino='{self._museo_destino.get_nombre()}', "
            f"estado='{self._estado}')"
        )
