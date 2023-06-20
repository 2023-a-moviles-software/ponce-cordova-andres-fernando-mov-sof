package com.andresponce.deber.util

import com.andresponce.deber.configuracion.ConstantesPrecision
import java.math.BigDecimal

class ValidadorDeLatitud {
    companion object{
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        fun validar(latitud: BigDecimal) {
            if (latitud !in BigDecimal(-90.0)..BigDecimal(90.0)) {
                throw IllegalArgumentException("La latitud debe estar entre -90 y 90.")
            }

            if (latitud.scale() > ConstantesPrecision.PRECISION_COORDENADAS) {
                throw UnsupportedOperationException("La latitud no puede tener más de ${ConstantesPrecision.PRECISION_COORDENADAS} decimales.")
            }
        }
    }
}