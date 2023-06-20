package com.andresponce.deber.serializadores.jackson

import com.andresponce.deber.configuracion.EstandarTiempo.FORMATO_HORA
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.LocalTime

class LocalTimeDeserializador: StdDeserializer<LocalTime>(LocalTime::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalTime {
        var cadenaADeserializar: String
        cadenaADeserializar = "00:00:00"
        if(p != null){
            cadenaADeserializar = p.text.trim()
        }
        return LocalTime.parse(cadenaADeserializar, FORMATO_HORA)
    }
}