
/**
 * Clase que representa un videojuego.
 *
 * @property consola La consola del videojuego.
 * @property año El año de lanzamiento del videojuego.
 */

class Videojuego(id: String, nombre: String, var consola: String, var año: Int, precio: Float) :
    Producto(id, nombre, precio)