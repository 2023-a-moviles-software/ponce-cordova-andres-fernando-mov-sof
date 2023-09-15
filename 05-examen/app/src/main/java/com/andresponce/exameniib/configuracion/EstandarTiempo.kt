package com.andresponce.exameniib.configuracion

import java.time.format.DateTimeFormatter

object EstandarTiempo {
    val FORMATO_FECHA: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    val FORMATO_HORA: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
}