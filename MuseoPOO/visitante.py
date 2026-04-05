"""Módulo que define la clase Visitante."""

from __future__ import annotations

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte
    from sala import Sala


class Visitante:
    """Representa un visitante del museo.

    Los visitantes pueden consultar las obras expuestas por sala
    sin necesidad de autenticación.
    """

    def consultar_obras_por_sala(
        self, sala: Sala,
    ) -> list[ObraDeArte]:
        """Consulta las obras expuestas en una sala."""
        obras = sala.listar_obras()
        print(f"\n{'=' * 60}")
        print(
            f"  Sala: {sala.get_nombre()} | "
            f"Ubicación: {sala.get_ubicacion()}"
        )
        print(f"{'=' * 60}")
        if not obras:
            print("  No hay obras en esta sala actualmente.")
        for obra in obras:
            print(f"  - {obra.consultar_info()}")
        print(f"{'=' * 60}\n")
        return obras
