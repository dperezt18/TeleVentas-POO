class Direccion:

    def __init__ (self, calle: str, ciudad: str, departamento: str, codigo_postal: str, pais: str):
        self.__calle = calle
        self.__ciudad = ciudad
        self.__departamento = departamento
        self.__codigo_postal = codigo_postal
        self.__pais = pais

# --- Getters ---
    def get_calle(self) -> str:
        return self.__calle

    def get_ciudad(self) -> str:
        return self.__ciudad

    def get_departamento(self) -> str:
        return self.__departamento

    def get_codigo_postal(self) -> str:
        return self.__codigo_postal

    def get_pais(self) -> str:
        return self.__pais

#Setters
    def set_calle(self, calle: str):
        self.__calle = calle

    def set_ciudad(self, ciudad: str):
        self.__ciudad = ciudad

    def set_departamento(self, departamento: str):
        self.__departamento = departamento

    def set_codigo_postal(self, codigo_postal: str):
        self.__codigo_postal = codigo_postal

    def set_pais(self, pais: str):
        self.__pais = pais

    def obtener_direccion_completa(self):
        return f"{self.__calle}, {self.__ciudad}, {self.__departamento}, {self.__codigo_postal}, {self.__pais}"