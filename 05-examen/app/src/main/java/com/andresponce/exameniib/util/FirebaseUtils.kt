package com.andresponce.exameniib.util

import com.andresponce.exameniib.configuracion.EstandarTiempo
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

object FirebaseUtils {
    fun <T : Identificable<String>> T.toHashMap(): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()

        val properties = this::class.java.declaredFields
        for (property in properties) {
            property.isAccessible = true

            when (val value = property.get(this)) {
                is String, is Int, is Long, is Double, is Float, is BigDecimal ->
                    hashMap[property.name] = value.toString()
                is Boolean -> hashMap[property.name] = value
                is LocalDate -> hashMap[property.name] = value.format(EstandarTiempo.FORMATO_FECHA)
                is LocalTime -> hashMap[property.name] = value.format(EstandarTiempo.FORMATO_HORA)
                is Identificable<*> -> hashMap[property.name] = (value as Identificable<String>).id ?: ""
            }
        }
        return hashMap
    }
}