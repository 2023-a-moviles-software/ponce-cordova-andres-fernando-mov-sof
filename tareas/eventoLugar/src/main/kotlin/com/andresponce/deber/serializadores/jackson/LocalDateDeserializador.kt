package com.andresponce.deber.serializadores.jackson

import com.andresponce.deber.configuracion.EstandarTiempo.FORMATO_FECHA
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.LocalDate

class LocalDateDeserializador: StdDeserializer<LocalDate>(LocalDate::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate {
        var cadenaADeserializar: String
        cadenaADeserializar = "-999999999-01-01"
        if(p != null){
            cadenaADeserializar = p.text.trim()
        }
        return LocalDate.parse(cadenaADeserializar, FORMATO_FECHA)
    }
}