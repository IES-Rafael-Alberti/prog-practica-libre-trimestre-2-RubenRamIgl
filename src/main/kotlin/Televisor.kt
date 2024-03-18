
/**
 * Clase que representa un televisor.
 *
 * @property modelo El modelo del televisor.
 */

class Televisor(id: String, nombre: String, var modelo: String, precio: Float) :
    Producto(id, nombre, precio)