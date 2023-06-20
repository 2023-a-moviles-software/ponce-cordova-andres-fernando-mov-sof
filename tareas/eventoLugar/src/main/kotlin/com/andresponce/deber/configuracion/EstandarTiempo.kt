package com.andresponce.deber.configuracion

import java.time.format.DateTimeFormatter

object EstandarTiempo {
    val FORMATO_FECHA: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    val FORMATO_HORA: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
}