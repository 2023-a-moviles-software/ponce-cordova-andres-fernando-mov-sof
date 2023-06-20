package com.andresponce.deber.serializadores.jackson

import com.andresponce.deber.configuracion.EstandarTiempo.FORMATO_FECHA
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate

class LocalDateSerializador: StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(value: LocalDate?, gen: JsonGenerator?, provider: SerializerProvider?) {
        var valorASerializar: LocalDate
        valorASerializar = LocalDate.MIN
        if (value != null){
            valorASerializar = value
        }

        gen?.writeString(valorASerializar.format(FORMATO_FECHA))
    }
}