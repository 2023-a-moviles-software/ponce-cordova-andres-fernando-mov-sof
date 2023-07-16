package com.andresponce.examen.util

import java.math.BigDecimal

class ValidadorNumeroPositivo {
    companion object {
        @Throws(IllegalArgumentException::class)
        fun validar(numero: BigDecimal, nombre: String) {
            if (numero < BigDecimal("0")) {
                throw IllegalArgumentException("La propiedad $nombre no puede ser negativa.")
            }
        }
    }
}