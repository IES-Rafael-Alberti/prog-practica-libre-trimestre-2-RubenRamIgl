
/**
 * Clase que representa un videojuego retro.
 *
 * @property consola La consola del videojuego retro.
 * @property año El año de lanzamiento del videojuego retro.
 */

class VideojuegoRetro(id: String, nombre: String, var consola: String, var año: Int, precio: Float) :
    Producto(id, nombre, precio), Retro{

    /**
     * Aumenta el precio original en un 50%.
     *
     * @param precioOriginal El precio original del producto.
     * @return El precio aumentado.
     */

    override fun aumentoPrecio(precioOriginal: Float): Float {
        return precioOriginal + (precioOriginal / 2)
    }
}