"""Módulo que define la clase Cliente."""

from src.usuario import Usuario


class Cliente(Usuario):
    """Representa un cliente de TeleVentas."""

    def __init__(self, id_usuario: int, nombre: str, email: str,
                 contrasena: str, telefono: str,
                 fecha_registro: str, estado: str):
        super().__init__(id_usuario, nombre, email, contrasena)
        self.__telefono = telefono
        self.__fecha_registro = fecha_registro
        self.__estado = estado

    def login(self) -> bool:
        print(f"{self.get_nombre()} ha iniciado sesión.")
        return True

    def logout(self) -> None:
        print(f"{self.get_nombre()} ha cerrado sesion.")

    # --- Getters ---
    def get_telefono(self) -> str:
        return self.__telefono

    def get_fecha_registro(self) -> str:
        return self.__fecha_registro

    def get_estado(self) -> str:
        return self.__estado

    # --- Setter ---
    def set_telefono(self, telefono: str):
        self.__telefono = telefono

    def set_fecha_registro(self, fecha_registro: str):
        self.__fecha_registro = fecha_registro

    def set_estado(self, estado: str):
        self.__estado = estado
