from src.usuario import Usuario

class GerenteRelaciones(Usuario):
    def __init__(self, id_usuario: int, nombre: str,
                 email: str, contrasena: str):
        super().__init__(id_usuario, nombre, email, contrasena)

    def login(self) -> bool:
        print(f"{self.get_nombre()} ha iniciado sesión.")
        return True
    def logout(self) -> None:
        print(f"{self.get_nombre()} ha cerrado sesion.")

    def recibir_queja(self) -> None:
        print(f" {self.get_nombre()} ha recibido una queja.")