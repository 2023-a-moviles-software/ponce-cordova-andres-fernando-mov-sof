package com.andresponce.deber.util

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class ManejadorArchivos {
    companion object {
        @Throws(IOException::class, IllegalStateException::class)
        fun crearCarpeta(directorio: String, nombreCarpeta: String) {
            val carpeta = File(directorio, nombreCarpeta)
            val exito = carpeta.mkdir()
            if (!exito) {
                throw IOException("Error al crear la carpeta")
            }
        }

        @Throws(IOException::class)
        fun crearArchivo(directorio: String, nombreArchivo: String) {
            val archivo = File(directorio, nombreArchivo)
            val exito = archivo.createNewFile()
            if (!exito) {
                throw Exception("Error al crear el archivo")
            }
        }

        fun existeCarpeta(directorio: String, nombreCarpeta: String): Boolean {
            val carpeta = File(directorio, nombreCarpeta)
            return carpeta.exists()
        }

        fun existeArchivo(directorio: String, nombreArchivo: String): Boolean {
            val archivo = File(directorio, nombreArchivo)
            return archivo.exists()
        }

        @Throws(FileNotFoundException::class)
        fun leerArchivo(directorio: String, nombreArchivo: String): String {
            val archivo = File(directorio, nombreArchivo)
            return archivo.readText()
        }

        @Throws(FileNotFoundException::class)
        fun escribirArchivo(directorio: String, nombreArchivo: String, contenido: String) {
            val archivo = File(directorio, nombreArchivo)
            archivo.writeText(contenido)
        }
    }
}