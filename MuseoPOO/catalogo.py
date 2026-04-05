"""Módulo que define la clase Catalogo."""

from __future__ import annotations

from typing import TYPE_CHECKING, Optional

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte


class Catalogo:
    """Representa el catálogo de obras del museo."""

    def __init__(self) -> None:
        self._obras: list[ObraDeArte] = []

    def get_obras(self) -> list[ObraDeArte]:
        return list(self._obras)

    def agregar_obra(self, obra: ObraDeArte) -> None:
        """Agrega una obra al catálogo."""
        if obra not in self._obras:
            self._obras.append(obra)
            print(
                f"Obra '{obra.get_titulo()}' registrada "
                f"en el catálogo."
            )
        else:
            print(
                f"La obra '{obra.get_titulo()}' ya está "
                f"en el catálogo."
            )

    def buscar_obra(self, titulo: str) -> Optional[ObraDeArte]:
        """Busca una obra en el catálogo por su título."""
        for obra in self._obras:
            if obra.get_titulo().lower() == titulo.lower():
                return obra
        print(
            f"Obra con título '{titulo}' no encontrada "
            f"en el catálogo."
        )
        return None

    def listar_obras(self) -> list[ObraDeArte]:
        """Retorna todas las obras del catálogo."""
        return list(self._obras)

    def __str__(self) -> str:
        return f"Catalogo(obras={len(self._obras)})"
