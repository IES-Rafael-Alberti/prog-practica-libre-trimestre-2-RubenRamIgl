
/**
 * Clase abstracta que representa un producto genérico.
 *
 * @property id El identificador único del producto.
 * @property nombre El nombre del producto.
 * @property precio El precio del producto.
 */

abstract class Producto(val id: String, var nombre: String, var precio: Float) {

    /**
     * Modifica el precio del producto.
     *
     * @param nuevoPrecio El nuevo precio del producto.
     */

    fun modificarPrecio(nuevoPrecio: Float) {
        precio = nuevoPrecio
    }
}