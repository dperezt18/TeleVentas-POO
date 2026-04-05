"""Módulo que define la clase EncargadoCatalogo."""

from __future__ import annotations

from typing import TYPE_CHECKING

from usuario import Usuario

if TYPE_CHECKING:
    from catalogo import Catalogo
    from obra_de_arte import ObraDeArte


class EncargadoCatalogo(Usuario):
    """Representa al encargado de gestionar el catálogo."""

    def __init__(
        self,
        id_usuario: int,
        nombre: str,
        email: str,
        contrasena: str,
    ) -> None:
        super().__init__(id_usuario, nombre, email, contrasena)

    def registrar_obra(
        self, catalogo: Catalogo, obra: ObraDeArte,
    ) -> None:
        """Registra una nueva obra en el catálogo."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para registrar obras."
            )
            return
        catalogo.agregar_obra(obra)

    def actualizar_catalogo(self, catalogo: Catalogo) -> None:
        """Actualiza y muestra el estado del catálogo."""
        if not self.get_autenticado():
            print(
                "Error: Debe autenticarse para actualizar "
                "el catálogo."
            )
            return
        obras = catalogo.listar_obras()
        print(
            f"Catálogo actualizado. Total de obras: {len(obras)}."
        )
        for obra in obras:
            print(f"  - {obra.consultar_info()}")
