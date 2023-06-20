package com.andresponce.deber.dao.json

import com.andresponce.deber.configuracion.DirectoriosArchivos
import com.andresponce.deber.util.ManejadorArchivos

class IdGenerador<T>(nombreArchivo: String) {
    val nombreArchivo: String
    init {
        this.nombreArchivo = "$nombreArchivo.id"

        if(!ManejadorArchivos.existeArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo)) {
            ManejadorArchivos.crearArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo)
            ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo, "0")
        }

        if(ManejadorArchivos.leerArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo).isBlank()) {
            ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo, "0")
        }
    }

    fun incrementarId() {
        val id: Int = ManejadorArchivos.leerArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo).toInt()
        ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo, "${id + 1}")
    }

    fun obtenerId(): Int {
        return ManejadorArchivos.leerArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, this.nombreArchivo).toInt()
    }
}