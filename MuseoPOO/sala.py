"""Módulo que define la clase Sala."""

from __future__ import annotations

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte


class Sala:
    """Representa una sala de exposición del museo."""

    def __init__(
        self,
        id_sala: int,
        nombre: str,
        ubicacion: str,
    ) -> None:
        self._id_sala = id_sala
        self._nombre = nombre
        self._ubicacion = ubicacion
        self._obras: list[ObraDeArte] = []

    def get_id_sala(self) -> int:
        return self._id_sala

    def get_nombre(self) -> str:
        return self._nombre

    def get_ubicacion(self) -> str:
        return self._ubicacion

    def get_obras(self) -> list[ObraDeArte]:
        return list(self._obras)

    def agregar_obra(self, obra: ObraDeArte) -> None:
        """Agrega una obra de arte a la sala."""
        if obra.get_estado() != "expuesta":
            print(
                f"La obra '{obra.get_titulo()}' no puede exponerse. "
                f"Estado actual: '{obra.get_estado()}'."
            )
            return
        if obra not in self._obras:
            self._obras.append(obra)
            print(
                f"Obra '{obra.get_titulo()}' agregada a "
                f"sala '{self._nombre}'."
            )
        else:
            print(
                f"La obra '{obra.get_titulo()}' ya está en "
                f"esta sala."
            )

    def listar_obras(self) -> list[ObraDeArte]:
        """Retorna la lista de obras expuestas en la sala."""
        return list(self._obras)

    def __str__(self) -> str:
        return (
            f"Sala(id={self._id_sala}, nombre='{self._nombre}', "
            f"obras={len(self._obras)})"
        )
