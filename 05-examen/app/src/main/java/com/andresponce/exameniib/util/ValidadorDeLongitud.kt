package com.andresponce.exameniib.util

import com.andresponce.exameniib.configuracion.ConstantesPrecision
import java.math.BigDecimal

class ValidadorDeLongitud {
    companion object {
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        fun validar(longitud: BigDecimal) {
            if (longitud !in BigDecimal(-180.0)..BigDecimal(180.0)) {
                throw IllegalArgumentException("La longitud debe estar entre -180 y 180.")
            }

            if (longitud.scale() > ConstantesPrecision.PRECISION_COORDENADAS) {
                throw UnsupportedOperationException("La longitud no puede tener más de ${ConstantesPrecision.PRECISION_COORDENADAS} decimales.")
            }
        }
    }
}