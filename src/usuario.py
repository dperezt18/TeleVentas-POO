from abc import ABC, abstractmethod


class Usuario(ABC):

    def __init__(self, id_usuario: int, nombre: str, email: str, contrasena: str):
        self.__id = id_usuario
        self.__nombre = nombre
        self.__email = email
        self.__contrasena = contrasena

    @abstractmethod
    def login(self) -> bool:
        pass

    @abstractmethod
    def logout(self) -> None:
        pass

    # -- Getters --
    def get_id(self) -> int:
        return self.__id

    def get_nombre(self) -> str:
        return self.__nombre

    def get_email(self) -> str:
        return self.__email

    def get_contrasena(self) -> str:
        return self.__contrasena

    # -- Setters --
    def set_id(self, id_usuario: int):
        self.__id = id_usuario

    def set_nombre(self, nombre: str):
        self.__nombre = nombre

    def set_email(self, email: str):
        self.__email = email

    def set_contrasena(self, contrasena: str):
        self.__contrasena = contrasena
