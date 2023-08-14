package com.andresponce.deber03.util

class ValidadorDeCadenaNoVacia {
    companion object {
        @Throws(IllegalArgumentException::class)
        fun validar(cadena: String, nombre: String) {
            if (cadena.isBlank()) {
                throw IllegalArgumentException("La propiedad $nombre no puede estar vacía.")
            }
        }
    }
}