package com.andresponce.deber.dao.memoria

import com.andresponce.deber.modelo.Evento
import com.andresponce.deber.modelo.Lugar
import java.lang.IllegalArgumentException
import java.math.BigDecimal

class BaseDatosMemoria {
    companion object {
        private val eventos = arrayListOf<Evento>()
        private val maxIdEvento = 0
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

        private val tablas = mutableMapOf<Class<*>, List<*>>(
            Evento::class.java to eventos,
            Lugar::class.java to lugares
        )

        private val indices = mutableMapOf<Class<*>, Int>(
            Evento::class.java to maxIdEvento,
            Lugar::class.java to maxIdLugar
        )

        fun <T> getTabla(clase: Class<T>): List<T> {
            if (!tablas.containsKey(clase)){
                throw IllegalArgumentException("No existe la tabla para la clase ${clase.name}")
            }
            return tablas[clase] as List<T>
        }

        fun <T> setTabla(clase: Class<T>, lista: List<T>) {
            if (!tablas.containsKey(clase)){
                throw IllegalArgumentException("No existe la tabla para la clase ${clase.name}")
            }
            tablas[clase] = lista
        }

        fun <T> getIndice(clase: Class<T>): Int {
            if (!indices.containsKey(clase)){
                throw IllegalArgumentException("No existe el indice para la clase ${clase.name}")
            }
            return indices[clase]!!
        }

        fun <T> incrementarIndice(clase: Class<T>) {
            if (!indices.containsKey(clase)){
                throw IllegalArgumentException("No existe el indice para la clase ${clase.name}")
            }
            indices[clase] = indices[clase]!! + 1
        }
    }
}