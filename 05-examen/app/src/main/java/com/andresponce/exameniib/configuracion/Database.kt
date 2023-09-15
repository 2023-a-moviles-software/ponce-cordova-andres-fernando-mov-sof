package com.andresponce.exameniib.configuracion

import com.andresponce.exameniib.modelo.Evento
import com.andresponce.exameniib.modelo.Lugar
import com.andresponce.exameniib.util.Identificable

object Database {
    const val DATABASE_NAME = "deber03"
    const val DATABASE_VERSION = 1
    val DATABASE_ENTIDADES: List<Class<out Identificable<String>>> = listOf(
        Lugar::class.java,
        Evento::class.java
    )
}