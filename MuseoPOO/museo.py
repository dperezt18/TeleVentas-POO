"""Módulo que define la clase Museo."""

from __future__ import annotations

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte


class Museo:
    """Representa un museo y su colección de obras."""

    def __init__(
        self,
        id_museo: int,
        nombre: str,
        direccion: str,
    ) -> None:
        self._id_museo = id_museo
        self._nombre = nombre
        self._direccion = direccion
        self._obras: list[ObraDeArte] = []
        self._museos_asociados: list[Museo] = []

    def get_id_museo(self) -> int:
        return self._id_museo

    def get_nombre(self) -> str:
        return self._nombre

    def get_direccion(self) -> str:
        return self._direccion

    def get_obras(self) -> list[ObraDeArte]:
        return list(self._obras)

    def get_museos_asociados(self) -> list[Museo]:
        return list(self._museos_asociados)

    def agregar_obra(self, obra: ObraDeArte) -> None:
        """Agrega una obra de arte a la colección del museo."""
        if obra not in self._obras:
            self._obras.append(obra)
            print(
                f"Obra '{obra.get_titulo()}' agregada al "
                f"museo '{self._nombre}'."
            )
        else:
            print(
                f"La obra '{obra.get_titulo()}' ya existe "
                f"en el museo."
            )

    def listar_obras(self) -> list[ObraDeArte]:
        """Retorna la lista completa de obras del museo."""
        return list(self._obras)

    def agregar_museo_asociado(self, museo: Museo) -> None:
        """Agrega un museo a la lista de museos asociados."""
        if museo not in self._museos_asociados:
            self._museos_asociados.append(museo)
            print(
                f"Museo '{museo.get_nombre()}' asociado con "
                f"'{self._nombre}'."
            )
        else:
            print(
                f"El museo '{museo.get_nombre()}' ya está asociado."
            )

    def calcular_valoracion_total(self) -> float:
        """Calcula la valoración total de todas las obras."""
        return sum(obra.get_valoracion() for obra in self._obras)

    def __str__(self) -> str:
        return (
            f"Museo(id={self._id_museo}, nombre='{self._nombre}', "
            f"obras={len(self._obras)})"
        )
