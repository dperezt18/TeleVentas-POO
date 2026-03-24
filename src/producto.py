class Producto:

    def __init__(self, id_producto: int, codigo:str, descripcion: str, precio: float, cantidad: int):
        self.__id_producto = id_producto
        self.__codigo = codigo
        self.__descripcion = descripcion
        self.__precio = precio
        self.__cantidad = cantidad

    #Getter
    def get_id_producto(self) -> int:
        return self.__id_producto

    def get_codigo(self) -> str:
        return self.__codigo

    def get_descripcion(self) -> str:
        return self.__descripcion

    def get_precio(self) -> float:
        return self.__precio

    def get_cantidad(self) -> int:
        return self.__cantidad

    #Setter
    def set_id_producto(self, id_producto: int):
        self.__id_producto = id_producto

    def set_codigo(self, codigo: str):
        self.__codigo = codigo

    def set_descripcion(self, descripcion: str):
        self.__descripcion = descripcion

    def set_precio(self, precio: float):
        self.__precio = precio

    def set_cantidad(self, cantidad: int):
        self.__cantidad = cantidad

    def __str__(self) -> str:
        return (f"Codigo: {self.__codigo}\n"
        f"Descripcion: {self.__descripcion}\n"
        f"Precio: {self.__precio}\n"
        f"Cantidad: {self.__cantidad}")
