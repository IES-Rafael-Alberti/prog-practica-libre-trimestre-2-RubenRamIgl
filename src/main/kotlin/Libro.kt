
/**
 * Clase que representa un libro.
 *
 * @property autor El autor del libro.
 * @property numPaginas El número de páginas del libro.
 * @property año El año de publicación del libro.
 */

class Libro(id: String, nombre: String, var autor: String, var numPaginas: String, var año: String, precio: Float) :
    Producto(id, nombre, precio)