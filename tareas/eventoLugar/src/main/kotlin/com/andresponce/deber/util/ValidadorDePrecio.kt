package com.andresponce.deber.util

import com.andresponce.deber.configuracion.ConstantesPrecision
import java.math.BigDecimal

class ValidadorDePrecio {
    companion object{
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        fun validar(dinero: Double){
            ValidadorNumeroPositivo.validar(dinero, "precio")

            if (BigDecimal(dinero).scale() > ConstantesPrecision.PRECISION_DINERO) {
                throw UnsupportedOperationException("El precio no puede tener más de ${ConstantesPrecision.PRECISION_DINERO} decimales.")
            }
        }
    }
}