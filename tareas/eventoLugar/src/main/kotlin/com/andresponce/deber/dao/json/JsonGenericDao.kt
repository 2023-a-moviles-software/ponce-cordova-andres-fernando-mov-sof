package com.andresponce.deber.dao.json

import com.andresponce.deber.configuracion.DirectoriosArchivos
import com.andresponce.deber.dao.GenericDao
import com.andresponce.deber.util.Identificable
import com.andresponce.deber.util.ManejadorArchivos
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

open class JsonGenericDao<T: Identificable<ID>, ID>(nombreArchivo: String, clase: Class<T>): GenericDao<T, ID> {
    private val clase: Class<T>;
    private val nombreArchivo: String
    private val mapeador: ObjectMapper

    init {
        this.nombreArchivo = nombreArchivo
        this.clase = clase
        if(!ManejadorArchivos.existeArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo)) {
            ManejadorArchivos.crearArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo)
            ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo, "[]")
        }

        if(ManejadorArchivos.leerArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo).isBlank()) {
            ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo, "[]")
        }

        this.mapeador = jacksonObjectMapper()
        this.mapeador.enable(SerializationFeature.INDENT_OUTPUT)
    }

    override fun insertar(nuevo: T) {
        val lista: MutableList<T> = obtenerTodos().toMutableList()
        lista.add(nuevo)
        val nuevaLista: String = mapeador.writeValueAsString(lista)
        ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo, nuevaLista)
    }

    override fun actualizar(actualizado: T) {
        val lista: MutableList<T> = obtenerTodos().toMutableList()
        val indice: Int = lista.indexOfFirst { it == actualizado }
        lista[indice] = actualizado
        val nuevaLista: String = mapeador.writeValueAsString(lista)
        ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo, nuevaLista)
    }

    override fun eliminar(id: ID) {
        val lista: MutableList<T> = obtenerTodos().toMutableList()
        val indice: Int = lista.indexOfFirst { it.id == id }
        lista.removeAt(indice)
        val nuevaLista: String = mapeador.writeValueAsString(lista)
        ManejadorArchivos.escribirArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo, nuevaLista)
    }

    override fun obtenerPorId(id: ID): T? {
        return obtenerTodos().firstOrNull { it.id == id }
    }

    override fun obtenerTodos(): List<T> {
        return mapeador.readValue(ManejadorArchivos.leerArchivo(DirectoriosArchivos.DIRECTORIO_DATOS, nombreArchivo), mapeador.typeFactory.constructCollectionType(List::class.java, clase))
    }
}