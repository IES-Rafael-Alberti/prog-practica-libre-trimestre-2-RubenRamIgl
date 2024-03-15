package org.practicatrim2

interface Retro {

    fun aumentoPrecio(precioOriginal: Float): Float
}

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

class Libro(id: String, nombre: String, var autor: String, var numPaginas: String, var año: String, precio: Float) :
    Producto(id, nombre, precio)

class Televisor(id: String, nombre: String, var modelo: String, precio: Float) :
    Producto(id, nombre, precio)

class Videojuego(id: String, nombre: String, var consola: String, var año: Int, precio: Float) :
    Producto(id, nombre, precio)

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

abstract class Persona(val dni: String, val nombre: String, val apellido1: String, val apellido2: String)

class Administrador(
    dni: String,
    nombre: String,
    apellido1: String,
    apellido2: String,
    private val usuario: String,
    private val contraseña: String
) :
    Persona(dni, nombre, apellido1, apellido2) {

    /**
     * Añade un producto a la lista de productos.
     *
     * @param listaProductos La lista de productos.
     */

    fun añadirProducto(listaProductos: MutableList<Producto>) {
        println("Ingrese el ID del producto:")
        val idProducto = readln()

        if (listaProductos.any { it.id == idProducto }) {
            println("Ya existe un producto con el ID '$idProducto'.")
            return
        }

        println("Seleccione el tipo de producto:")
        println("1. Libro")
        println("2. Televisor")
        println("3. Videojuego")
        println("4. Videojuego Retro")
        val tipoProducto = readln()

        val nombreProducto: String
        if (tipoProducto == "1" || tipoProducto == "2" || tipoProducto == "3" || tipoProducto == "4") {
            println("Ingrese el nombre del producto:")
            nombreProducto = readln()
        } else {
            println("Tipo de producto inválido.")
            return
        }

        val producto: Producto = when (tipoProducto) {
            // Para cada tipo de producto pedirá datos distintos
            "1" -> {
                // Detalles de los libros
                println("Ingrese el autor del libro:")
                val autor = readln()
                println("Ingrese el número de páginas del libro:")
                val numPaginas = readln()
                println("Ingrese el año del libro:")
                val año = readln()
                println("Ingrese el precio del libro:")
                val precio = readln().toFloat()
                Libro(idProducto, nombreProducto, autor, numPaginas, año, precio)
            }
            "2" -> {
                // Detalles de las tv
                println("Ingrese el modelo del televisor:")
                val modelo = readln()
                println("Ingrese el precio del televisor:")
                val precio = readln().toFloat()
                Televisor(idProducto, nombreProducto, modelo, precio)
            }
            "3" -> {
                // Detalles de los videojuegos
                println("Ingrese la consola del videojuego:")
                val consola = readln()
                println("Ingrese el año del videojuego:")
                val año = readln()
                println("Ingrese el precio del videojuego:")
                val precio = readln().toFloat()
                Videojuego(idProducto, nombreProducto, consola, año.toInt(), precio)
            }
            "4" -> {
                // Detalles de los videojuegos retro
                println("Ingrese la consola del videojuego retro:")
                val consola = readln()
                println("Ingrese el año del videojuego retro:")
                val año = readln()
                println("Ingrese el precio del videojuego retro:")
                val precio = readln().toFloat()
                val precioConAumento = (VideojuegoRetro(idProducto, nombreProducto, consola, año.toInt(), precio) as VideojuegoRetro).aumentoPrecio(precio)
                VideojuegoRetro(idProducto, nombreProducto, consola, año.toInt(), precioConAumento)
            }
            else -> {
                println("Tipo de producto inválido.")
                return
            }
        }

        listaProductos.add(producto)
        println("Producto '$nombreProducto' agregado exitosamente.")
    }


    /**
     * Elimina un producto de la lista de productos.
     *
     * @param nombreProducto El nombre del producto a eliminar.
     * @param listaProductos La lista de productos.
     */

    fun eliminarProducto(nombreProducto: String, listaProductos: MutableList<Producto>) {
        val producto = listaProductos.find { it.nombre == nombreProducto }
        if (producto != null) {
            listaProductos.remove(producto)
            println("Producto '$nombreProducto' eliminado exitosamente.")
        } else {
            println("Producto '$nombreProducto' no encontrado.")
        }
    }

    /**
     * Modifica un producto en la lista de productos según un criterio dado.
     *
     * @param listaProductos La lista de productos.
     */

    fun modificarPorCriterio(listaProductos: MutableList<Producto>) {
        println("Seleccione el criterio de modificación:")
        println("1. Por nombre")
        println("2. Por ID")
        val criterio = readln()

        when (criterio) {
            "1" -> {
                println("Ingrese el nombre del producto a modificar:")
                val nombre = readln()
                val producto = listaProductos.find { it.nombre.equals(nombre) }
                if (producto != null) {
                    println("Seleccione el atributo a modificar:")
                    println("1. Nombre")
                    println("2. Precio")

                    when (producto) {
                        is Libro -> println("3. Autor")
                        is Televisor -> println("3. Modelo")
                        is Videojuego -> println("3. Consola")
                        is VideojuegoRetro -> println("3. Consola")
                    }

                    when (readln()) {
                        "1" -> {
                            println("Ingrese el nuevo nombre para el producto:")
                            val nuevoNombre = readln()
                            producto.nombre = nuevoNombre
                            println("Nombre del producto '${producto.nombre}' modificado exitosamente.")
                        }
                        "2" -> {
                            println("Ingrese el nuevo precio para el producto:")
                            val nuevoPrecio = readln().toFloat()
                            producto.modificarPrecio(nuevoPrecio)
                            println("Precio del producto '${producto.nombre}' modificado exitosamente.")
                        }
                        "3" -> {
                            when (producto) {
                                is Libro -> {
                                    println("Ingrese el nuevo autor para el libro:")
                                    val nuevoAutor = readln()
                                    (producto as Libro).autor = nuevoAutor
                                    println("Autor del libro '${producto.nombre}' modificado exitosamente.")
                                }
                                is Televisor -> {
                                    println("Ingrese el nuevo modelo para el televisor:")
                                    val nuevoModelo = readln()
                                    (producto as Televisor).modelo = nuevoModelo
                                    println("Modelo del televisor '${producto.nombre}' modificado exitosamente.")
                                }
                                is Videojuego -> {
                                    println("Ingrese la nueva consola para el videojuego:")
                                    val nuevaConsola = readln()
                                    (producto as Videojuego).consola = nuevaConsola
                                    println("Consola del videojuego '${producto.nombre}' modificado exitosamente.")
                                }
                                is VideojuegoRetro -> {
                                    println("Ingrese la nueva consola para el videojuego retro:")
                                    val nuevaConsola = readln()
                                    (producto as VideojuegoRetro).consola = nuevaConsola
                                    println("Consola del videojuego retro '${producto.nombre}' modificado exitosamente.")
                                }
                            }
                        }
                        else -> println("Opción inválida.")
                    }
                } else {
                    println("No se encontró ningún producto con el nombre '$nombre'.")
                }
            }
            "2" -> {
                println("Ingrese el ID del producto a modificar:")
                val id = readln()
                val producto = listaProductos.find { it.id == id }
                if (producto != null) {
                    println("Seleccione el atributo a modificar:")
                    println("1. Nombre")
                    println("2. Precio")

                    when (producto) {
                        is Libro -> println("3. Autor")
                        is Televisor -> println("3. Modelo")
                        is Videojuego -> println("3. Consola")
                        is VideojuegoRetro -> println("3. Consola")
                    }

                    when (readln()) {
                        "1" -> {
                            println("Ingrese el nuevo nombre para el producto:")
                            val nuevoNombre = readln()
                            producto.nombre = nuevoNombre
                            println("Nombre del producto con ID '$id' modificado exitosamente.")
                        }
                        "2" -> {
                            println("Ingrese el nuevo precio para el producto:")
                            val nuevoPrecio = readln().toFloat()
                            producto.modificarPrecio(nuevoPrecio)
                            println("Precio del producto con ID '$id' modificado exitosamente.")
                        }
                        "3" -> {
                            when (producto) {
                                is Libro -> {
                                    println("Ingrese el nuevo autor para el libro:")
                                    val nuevoAutor = readln()
                                    producto.autor = nuevoAutor
                                    println("Autor del libro con ID '$id' modificado exitosamente.")
                                }
                                is Televisor -> {
                                    println("Ingrese el nuevo modelo para el televisor:")
                                    val nuevoModelo = readln()
                                    producto.modelo = nuevoModelo
                                    println("Modelo del televisor con ID '$id' modificado exitosamente.")
                                }
                                is Videojuego -> {
                                    println("Ingrese la nueva consola para el videojuego:")
                                    val nuevaConsola = readln()
                                    producto.consola = nuevaConsola
                                    println("Consola del videojuego con ID '$id' modificado exitosamente.")
                                }
                                is VideojuegoRetro -> {
                                    println("Ingrese la nueva consola para el videojuego retro:")
                                    val nuevaConsola = readln()
                                    producto.consola = nuevaConsola
                                    println("Consola del videojuego retro con ID '$id' modificado exitosamente.")
                                }
                            }
                        }
                        else -> println("Opción inválida.")
                    }
                } else {
                    println("No se encontró ningún producto con el ID '$id'.")
                }
            }
            else -> println("Criterio de modificación inválido.")
        }
    }

    /**
     * Muestra los productos de la lista de productos.
     *
     * @param listaProductos La lista de productos.
     */

    fun mostrarProductos(listaProductos: List<Producto>) {
        if (listaProductos.isEmpty()) {
            println("No hay productos para mostrar.")
        } else {
            println("Lista de productos:")
            for ((index, producto) in listaProductos.withIndex()) {
                when (producto) {
                    is Libro -> println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Autor: ${producto.autor} - Páginas: ${producto.numPaginas} - Año: ${producto.año} - Precio: ${producto.precio}")
                    is Televisor -> println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Modelo: ${producto.modelo} - Precio: ${producto.precio}")
                    is Videojuego -> println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Consola: ${producto.consola} - Año: ${producto.año} - Precio: ${producto.precio}")
                    is VideojuegoRetro -> println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Consola: ${producto.consola} - Año: ${producto.año} - Precio: ${producto.precio}")
                    else -> println("${index + 1}. Producto desconocido")
                }
            }
        }
    }

    /**
     * Busca productos en la lista de productos según un criterio dado.
     *
     * @param listaProductos La lista de productos.
     */

    fun buscarPorCriterio(listaProductos: MutableList<Producto>) {
        println("Seleccione el criterio de búsqueda:")
        println("1. Por nombre")
        println("2. Por ID")
        println("3. Por precio")
        val criterio = readln()

        when (criterio) {
            "1" -> {
                println("Ingrese el nombre del producto:")
                val nombre = readln()
                val productosEncontrados = listaProductos.filter { it.nombre.equals(nombre, ignoreCase = true) }
                if (productosEncontrados.isNotEmpty()) {
                    println("Productos encontrados:")
                    for ((index, producto) in productosEncontrados.withIndex()) {
                        println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Precio: ${producto.precio}")
                    }
                } else {
                    println("No se encontraron productos con el nombre '$nombre'.")
                }
            }
            "2" -> {
                println("Ingrese el ID del producto:")
                val id = readln()
                val productoEncontrado = listaProductos.find { it.id == id }
                if (productoEncontrado != null) {
                    println("Producto encontrado:")
                    println("ID: ${productoEncontrado.id} - Nombre: ${productoEncontrado.nombre} - Precio: ${productoEncontrado.precio}")
                } else {
                    println("No se encontró ningún producto con el ID '$id'.")
                }
            }
            "3" -> {
                println("Ingrese el rango de precios (precio mínimo - precio máximo):")
                val (precioMin, precioMax) = readln().split("-").map { it.trim().toFloat() }
                val productosEncontrados = listaProductos.filter { it.precio in precioMin..precioMax }
                if (productosEncontrados.isNotEmpty()) {
                    println("Productos encontrados:")
                    for ((index, producto) in productosEncontrados.withIndex()) {
                        println("${index + 1}. ID: ${producto.id} - Nombre: ${producto.nombre} - Precio: ${producto.precio}")
                    }
                } else {
                    println("No se encontraron productos dentro del rango de precios especificado.")
                }
            }
            else -> println("Criterio de búsqueda inválido.")
        }
    }
}

class Programa {
    companion object {
        /**
         * Inicia el programa con un administrador dado.
         *
         * @param admin El administrador que inicia el programa.
         */
        fun iniciar(admin: Administrador) {
            val listaProductos = mutableListOf<Producto>() // Se define la lista de productos

            var continuar = true

            while (continuar) {
                println("Menú:")
                println("1. Añadir producto")
                println("2. Modificar producto por criterio")
                println("3. Eliminar producto")
                println("4. Buscar producto por criterio")
                println("5. Mostrar Productos")
                println("6. Salir")
                println("Seleccione una opción:")

                // En todos se pasa la lista de productos

                when (readln()) {
                    "1" -> {
                        admin.añadirProducto(listaProductos)
                    }
                    "2" -> {
                        println("Modificar producto por criterio:")
                        admin.modificarPorCriterio(listaProductos)
                    }
                    "3" -> {
                        println("Eliminar producto:")
                        println("Ingrese el nombre del producto a eliminar:")
                        val nombreProducto = readLine() ?: ""
                        admin.eliminarProducto(nombreProducto, listaProductos)
                    }
                    "4" -> {
                        println("Buscar producto por criterio:")
                        admin.buscarPorCriterio(listaProductos)
                    }
                    "5" -> {
                        println("Mostrar productos:")
                        admin.mostrarProductos(listaProductos)
                    }
                    "6" -> continuar = false
                    else -> println("Opción inválida.")
                }

            }
        }
    }
}

/**
 * Función principal que ejecuta el programa.
 *
 * @param args Los argumentos de la línea de comandos.
 */

fun main(args: Array<String>) {
    val admin = Administrador("12345678A", "Manuel", "Martínez", "Leal", "admin", "1234")
    Programa.iniciar(admin)
}