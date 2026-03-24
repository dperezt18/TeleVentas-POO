from src.usuario import Usuario

class AgenteDeposito(Usuario):

    def __init__(self, id_usuario: int, nombre: str,
                 email: str, contrasena: str, deposito: str):
        super().__init__(id_usuario, nombre, email, contrasena)
        self.__deposito = deposito

    def login(self) -> bool:
        print(f"{self.get_nombre()} ha iniciado sesión")
        return True

    def logout(self) -> None:
        print(f"{self.get_nombre()} ha cerrado sesión")

    def consultar_ordenes(self) -> list:
        print(f"{self.get_nombre()} está consultando órdenes")
        return []

    def crear_pedido(self):
        print(f"{self.get_nombre()} está creando un pedido")

    # --- Getter ---
    def get_deposito(self) -> str:
        return self.__deposito

    # --- Setter ---
    def set_deposito(self, deposito: str):
        self.__deposito = deposito

