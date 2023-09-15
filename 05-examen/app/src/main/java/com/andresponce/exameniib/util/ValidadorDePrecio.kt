package com.andresponce.exameniib.util

import com.andresponce.exameniib.configuracion.ConstantesPrecision
import java.math.BigDecimal

class ValidadorDePrecio {
    companion object {
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        fun validar(dinero: BigDecimal) {
            ValidadorNumeroPositivo.validar(dinero, "precio")

            if (dinero.scale() > ConstantesPrecision.PRECISION_DINERO) {
                throw UnsupportedOperationException("El precio no puede tener más de ${ConstantesPrecision.PRECISION_DINERO} decimales.")
            }
        }
    }
}