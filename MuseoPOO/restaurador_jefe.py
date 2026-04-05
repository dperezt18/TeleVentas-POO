"""Módulo que define la clase RestauradorJefe."""

from __future__ import annotations

from datetime import date
from typing import TYPE_CHECKING, Optional

from usuario import Usuario

if TYPE_CHECKING:
    from obra_de_arte import ObraDeArte
    from restauracion import Restauracion


class RestauradorJefe(Usuario):
    """Representa al restaurador jefe del museo."""

    def __init__(
        self,
        id_usuario: int,
        nombre: str,
        email: str,
        contrasena: str,
    ) -> None:
        super().__init__(id_usuario, nombre, email, contrasena)
        self._restauraciones: list[Restauracion] = []

    def get_restauraciones(self) -> list[Restauracion]:
        return list(self._restauraciones)

    def iniciar_restauracion(
        self, restauracion: Restauracion,
    ) -> None:
        """Inicia un proceso de restauración."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para iniciar "
                "restauraciones."
            )
            return
        restauracion.registrar_inicio()
        self._restauraciones.append(restauracion)

    def finalizar_restauracion(
        self,
        restauracion: Restauracion,
        fecha_fin: Optional[date] = None,
    ) -> None:
        """Finaliza un proceso de restauración."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para finalizar "
                "restauraciones."
            )
            return
        restauracion.registrar_fin(fecha_fin)

    def consultar_restauraciones(
        self, obra: ObraDeArte,
    ) -> list[Restauracion]:
        """Consulta restauraciones de una obra por antigüedad."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para consultar "
                "restauraciones."
            )
            return []
        restauraciones_obra = [
            r for r in self._restauraciones
            if r.get_obra().get_id_obra() == obra.get_id_obra()
        ]
        restauraciones_obra.sort(
            key=lambda r: r.get_fecha_inicio(),
        )
        print(
            f"Restauraciones de '{obra.get_titulo()}' "
            f"({len(restauraciones_obra)} encontradas):"
        )
        for rest in restauraciones_obra:
            fin = rest.get_fecha_fin() or "En curso"
            print(
                f"  - [{rest.get_tipo_restauracion()}] "
                f"Inicio: {rest.get_fecha_inicio()} | Fin: {fin}"
            )
        return restauraciones_obra

    def verificar_obras_para_restauracion(
        self, obras: list[ObraDeArte],
    ) -> list[ObraDeArte]:
        """Verifica qué obras necesitan restauración (5+ años)."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para verificar obras."
            )
            return []
        hoy = date.today()
        obras_a_restaurar: list[ObraDeArte] = []
        for obra in obras:
            if obra.get_estado() == "en_restauracion":
                continue
            ultima = self._obtener_ultima_restauracion(obra)
            if ultima:
                fecha_ref = ultima.get_fecha_fin() or hoy
            else:
                fecha_ref = obra.get_fecha_entrada()
            dias = (hoy - fecha_ref).days
            if dias >= 5 * 365:
                obras_a_restaurar.append(obra)
        return obras_a_restaurar

    def _obtener_ultima_restauracion(
        self, obra: ObraDeArte,
    ) -> Optional[Restauracion]:
        """Obtiene la última restauración finalizada."""
        restauraciones_obra = [
            r for r in self._restauraciones
            if r.get_obra().get_id_obra() == obra.get_id_obra()
            and r.get_estado() == "finalizada"
        ]
        if not restauraciones_obra:
            return None
        return max(
            restauraciones_obra,
            key=lambda r: r.get_fecha_fin(),
        )
