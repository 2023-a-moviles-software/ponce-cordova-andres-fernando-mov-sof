package com.andresponce.deber03.datos

import com.andresponce.deber03.modelo.Evento
import com.andresponce.deber03.modelo.Lugar
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class BaseDatosMemoria {
    companion object {
        private val lugares = arrayListOf<Lugar>(
            Lugar(
                0,
                "Las Poliburguer",
                "Andalucía",
                BigDecimal("-0.20509"),
                BigDecimal("-78.48939"),
                15,
                false
            ),
            Lugar(
                1,
                "Casa de la Abuela",
                "Av. El Inca",
                BigDecimal("-0.15439"),
                BigDecimal("-78.47357"),
                40,
                true
            )
        )
        private val maxIdLugar = 2

        private val eventos = arrayListOf<Evento>(
            Evento(
                id = 1,
                nombre = "Festival de Música",
                descripcion = "Disfruta de un festival musical lleno de ritmo y energía.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 8, 20),
                horaInicio = LocalTime.of(16, 0),
                horaFin = LocalTime.of(23, 0),
                precioDeEntrada = BigDecimal("30.00")
            ),

            Evento(
                id = 2,
                nombre = "Taller de Cocina",
                descripcion = "Aprende los secretos de la cocina gourmet de la mano de expertos chefs.",
                lugar = lugares[1],
                fecha = LocalDate.of(2022, 9, 10),
                horaInicio = LocalTime.of(10, 0),
                horaFin = LocalTime.of(14, 0),
                precioDeEntrada = BigDecimal("50.00")
            ),

            Evento(
                id = 3,
                nombre = "Exhibición de Autos",
                descripcion = "Descubre los últimos modelos de autos de lujo en una increíble exhibición.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 10, 5),
                horaInicio = LocalTime.of(9, 0),
                horaFin = LocalTime.of(17, 0),
                precioDeEntrada = BigDecimal("25.00")
            ),

            Evento(
                id = 4,
                nombre = "Conferencia de Tecnología",
                descripcion = "Descubre las últimas tendencias y avances en el mundo de la tecnología.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 11, 15),
                horaInicio = LocalTime.of(14, 0),
                horaFin = LocalTime.of(18, 0),
                precioDeEntrada = BigDecimal("15.00")
            ),

            Evento(
                id = 5,
                nombre = "Feria del Libro",
                descripcion = "Sumérgete en el mundo de la literatura con una amplia selección de libros y actividades relacionadas.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 12, 1),
                horaInicio = LocalTime.of(10, 0),
                horaFin = LocalTime.of(20, 0),
                precioDeEntrada = BigDecimal("10.00")
            ),

            Evento(
                id = 6,
                nombre = "Carrera de Bicicletas",
                descripcion = "Participa en una emocionante carrera de bicicletas y disfruta del deporte al aire libre.",
                lugar = lugares[1],
                fecha = LocalDate.of(2022, 8, 30),
                horaInicio = LocalTime.of(8, 0),
                horaFin = LocalTime.of(12, 0),
                precioDeEntrada = BigDecimal("5.00")
            ),

            Evento(
                id = 7,
                nombre = "Espectáculo de Magia",
                descripcion = "Déjate sorprender por increíbles trucos de magia y ilusiones impresionantes.",
                lugar = lugares[1],
                fecha = LocalDate.of(2022, 9, 20),
                horaInicio = LocalTime.of(17, 0),
                horaFin = LocalTime.of(19, 0),
                precioDeEntrada = BigDecimal("8.00")
            ),

            Evento(
                id = 8,
                nombre = "Festival de Cine",
                descripcion = "Disfruta de las mejores películas en un festival de cine lleno de emoción y entretenimiento.",
                lugar = lugares[1],
                fecha = LocalDate.of(2022, 10, 10),
                horaInicio = LocalTime.of(15, 0),
                horaFin = LocalTime.of(23, 0),
                precioDeEntrada = BigDecimal("12.00")
            ),

            Evento(
                id = 9,
                nombre = "Concierto Acústico",
                descripcion = "Vive la experiencia íntima de un concierto acústico con tu artista favorito.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 11, 25),
                horaInicio = LocalTime.of(19, 0),
                horaFin = LocalTime.of(21, 0),
                precioDeEntrada = BigDecimal("18.00")
            ),

            Evento(
                id = 10,
                nombre = "Exposición de Fotografía",
                descripcion = "Descubre las increíbles historias detrás de cada fotografía en esta exposición fotográfica.",
                lugar = lugares[0],
                fecha = LocalDate.of(2022, 12, 15),
                horaInicio = LocalTime.of(16, 0),
                horaFin = LocalTime.of(18, 0),
                precioDeEntrada = BigDecimal("8.00")
            )
        )
        private val maxIdEvento = 11

        private val tablas = mutableMapOf<Class<*>, List<*>>(
            Evento::class.java to eventos,
            Lugar::class.java to lugares
        )

        private val indices = mutableMapOf<Class<*>, Int>(
            Evento::class.java to maxIdEvento,
            Lugar::class.java to maxIdLugar
        )

        fun <T> getTabla(clase: Class<T>): List<T> {
            if (!tablas.containsKey(clase)) {
                throw IllegalArgumentException("No existe la tabla para la clase ${clase.name}")
            }
            return tablas[clase] as List<T>
        }

        fun <T> setTabla(clase: Class<T>, lista: List<T>) {
            if (!tablas.containsKey(clase)) {
                throw IllegalArgumentException("No existe la tabla para la clase ${clase.name}")
            }
            tablas[clase] = lista
        }

        fun <T> getIndice(clase: Class<T>): Int {
            if (!indices.containsKey(clase)) {
                throw IllegalArgumentException("No existe el indice para la clase ${clase.name}")
            }
            return indices[clase]!!
        }

        fun <T> incrementarIndice(clase: Class<T>) {
            if (!indices.containsKey(clase)) {
                throw IllegalArgumentException("No existe el indice para la clase ${clase.name}")
            }
            indices[clase] = indices[clase]!! + 1
        }
    }
}