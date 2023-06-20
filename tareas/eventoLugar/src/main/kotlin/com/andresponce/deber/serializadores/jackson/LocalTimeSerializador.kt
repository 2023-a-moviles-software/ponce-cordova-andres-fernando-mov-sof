package com.andresponce.deber.serializadores.jackson

import com.andresponce.deber.configuracion.EstandarTiempo.FORMATO_HORA
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalTime

class LocalTimeSerializador: StdSerializer<LocalTime>(LocalTime::class.java) {
    override fun serialize(value: LocalTime?, gen: JsonGenerator?, provider: SerializerProvider?) {
        var valorASerializar: LocalTime
        valorASerializar = LocalTime.MIN
        if (value != null){
            valorASerializar = value
        }

        gen?.writeString(valorASerializar.format(FORMATO_HORA))
    }
}