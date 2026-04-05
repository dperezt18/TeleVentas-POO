"""Módulo que define el proceso automático de restauración."""

from __future__ import annotations

from datetime import date
from typing import TYPE_CHECKING, Optional

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte
    from restauracion import Restauracion


class ProcesoRestauracionAutomatica:
    """Proceso diario que verifica qué obras necesitan restauración.

    Las obras se restauran automáticamente cada cinco años desde
    su última restauración o fecha de entrada.
    """

    ANOS_PARA_RESTAURACION = 5

    def __init__(
        self,
        restauraciones: Optional[list[Restauracion]] = None,
    ) -> None:
        self._restauraciones = restauraciones or []

    def verificar_obras_para_restauracion(
        self, obras: list[ObraDeArte],
    ) -> list[ObraDeArte]:
        """Verifica qué obras requieren restauración automática."""
        hoy = date.today()
        obras_a_restaurar: list[ObraDeArte] = []

        for obra in obras:
            if obra.get_estado() != "expuesta":
                continue

            ultima_fecha = self._obtener_fecha_ultima(obra)
            fecha_ref = ultima_fecha or obra.get_fecha_entrada()
            dias = (hoy - fecha_ref).days
            anos = dias / 365.25

            if anos >= self.ANOS_PARA_RESTAURACION:
                obras_a_restaurar.append(obra)
                print(
                    f"Obra '{obra.get_titulo()}' requiere "
                    f"restauración ({anos:.1f} años desde "
                    f"última intervención)."
                )

        if not obras_a_restaurar:
            print(
                "No hay obras que requieran restauración "
                "automática hoy."
            )

        return obras_a_restaurar

    def actualizar_estado_obras(
        self, obras_a_restaurar: list[ObraDeArte],
    ) -> None:
        """Actualiza el estado de las obras que necesitan restauración."""
        for obra in obras_a_restaurar:
            obra.set_estado("en_restauracion")
            print(
                f"Obra '{obra.get_titulo()}' enviada a "
                f"restauración automática."
            )

    def _obtener_fecha_ultima(
        self, obra: ObraDeArte,
    ) -> Optional[date]:
        """Obtiene la fecha de la última restauración finalizada."""
        restauraciones_obra = [
            r for r in self._restauraciones
            if r.get_obra().get_id_obra() == obra.get_id_obra()
            and r.get_estado() == "finalizada"
        ]
        if not restauraciones_obra:
            return None
        ultima = max(
            restauraciones_obra,
            key=lambda r: r.get_fecha_fin(),
        )
        return ultima.get_fecha_fin()
