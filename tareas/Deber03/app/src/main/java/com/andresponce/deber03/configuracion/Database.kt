package com.andresponce.deber03.configuracion

import com.andresponce.deber03.modelo.Evento
import com.andresponce.deber03.modelo.Lugar
import com.andresponce.deber03.util.Identificable

object Database {
    const val DATABASE_NAME = "deber03"
    const val DATABASE_VERSION = 1
    val DATABASE_ENTIDADES: List<Class<out Identificable<Int>>> = listOf(
        Lugar::class.java,
        Evento::class.java
    )
}