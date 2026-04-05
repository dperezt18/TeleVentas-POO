"""Módulo que define la clase Director."""

from __future__ import annotations

from typing import TYPE_CHECKING

from usuario import Usuario

if TYPE_CHECKING:
    from museo import Museo
    from prestamo import Prestamo


class Director(Usuario):
    """Representa al director del museo."""

    def __init__(
        self,
        id_usuario: int,
        nombre: str,
        email: str,
        contrasena: str,
    ) -> None:
        super().__init__(id_usuario, nombre, email, contrasena)

    def gestionar_prestamo(self, prestamo: Prestamo) -> None:
        """Gestiona un préstamo de obra de arte."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para gestionar "
                "préstamos."
            )
            return
        prestamo.registrar_prestamo()

    def consultar_valoracion_total(self, museo: Museo) -> float:
        """Consulta la valoración total de las obras del museo."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para consultar "
                "valoraciones."
            )
            return 0.0
        total = museo.calcular_valoracion_total()
        print(
            f"Valoración total del museo "
            f"'{museo.get_nombre()}': ${total:,.2f}"
        )
        return total

    def consultar_estadisticas_restauracion(
        self, restauraciones: list,
    ) -> dict:
        """Consulta estadísticas de restauraciones."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para consultar "
                "estadísticas."
            )
            return {}
        en_curso = sum(
            1 for r in restauraciones
            if r.get_estado() == "en_curso"
        )
        finalizadas = sum(
            1 for r in restauraciones
            if r.get_estado() == "finalizada"
        )
        return {
            "total": len(restauraciones),
            "en_curso": en_curso,
            "finalizadas": finalizadas,
        }
