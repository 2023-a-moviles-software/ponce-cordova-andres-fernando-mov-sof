package com.andresponce.deber.util

class ValidadorDeCadenaNoVacia {
    companion object{
        @Throws(IllegalArgumentException::class)
        fun validar(cadena: String, nombre: String) {
            if (cadena.isBlank()) {
                throw IllegalArgumentException("La propiedad $nombre no puede estar vacía.")
            }
        }
    }
}