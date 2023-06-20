package com.andresponce.deber.util

class ValidadorNumeroPositivo {
    companion object {
        @Throws(IllegalArgumentException::class)
        fun validar(numero: Double, nombre: String) {
            if (numero < 0) {
                throw IllegalArgumentException("La propiedad $nombre no puede ser negativa.")
            }
        }
    }
}