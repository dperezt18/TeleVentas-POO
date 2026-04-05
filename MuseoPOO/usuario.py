"""Módulo que define la clase abstracta Usuario."""

from abc import ABC


class Usuario(ABC):
    """Clase abstracta que representa un usuario del sistema del museo."""

    def __init__(
        self,
        id_usuario: int,
        nombre: str,
        email: str,
        contrasena: str,
    ) -> None:
        self._id_usuario = id_usuario
        self._nombre = nombre
        self._email = email
        self._contrasena = contrasena
        self._autenticado = False

    def get_id_usuario(self) -> int:
        return self._id_usuario

    def get_nombre(self) -> str:
        return self._nombre

    def get_email(self) -> str:
        return self._email

    def get_autenticado(self) -> bool:
        return self._autenticado

    def login(self, contrasena: str) -> bool:
        """Autentica al usuario verificando la contraseña."""
        if contrasena == self._contrasena:
            self._autenticado = True
            print(f"Usuario '{self._nombre}' autenticado exitosamente.")
            return True
        print("Error: Contraseña incorrecta.")
        return False

    def logout(self) -> None:
        """Cierra la sesión del usuario."""
        self._autenticado = False
        print(f"Usuario '{self._nombre}' ha cerrado sesión.")

    def __str__(self) -> str:
        estado = "autenticado" if self._autenticado else "no autenticado"
        return (
            f"{self.__class__.__name__}(id={self._id_usuario}, "
            f"nombre='{self._nombre}', {estado})"
        )
