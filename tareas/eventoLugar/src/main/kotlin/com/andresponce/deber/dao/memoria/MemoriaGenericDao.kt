package com.andresponce.deber.dao.memoria

import com.andresponce.deber.dao.GenericDao
import com.andresponce.deber.util.Identificable

open class MemoriaGenericDao<T: Identificable<ID>, ID>(clase: Class<T>)
    : GenericDao<T, ID> {

    protected val clase: Class<T>

    init {
        this.clase = clase
    }

    override fun insertar(nuevo: T) {
        val lista: MutableList<T> = BaseDatosMemoria.getTabla(clase).toMutableList()
        lista.add(nuevo)
        BaseDatosMemoria.setTabla(clase, lista)
    }

    override fun actualizar(actualizado: T) {
        val lista: MutableList<T> = BaseDatosMemoria.getTabla(clase).toMutableList()
        val indice: Int = lista.indexOfFirst { it == actualizado }
        lista[indice] = actualizado
        BaseDatosMemoria.setTabla(clase, lista)
    }

    override fun eliminar(id: ID) {
        val lista: MutableList<T> = BaseDatosMemoria.getTabla(clase).toMutableList()
        val indice: Int = lista.indexOfFirst { it.id == id }
        lista.removeAt(indice)
        BaseDatosMemoria.setTabla(clase, lista)
    }

    override fun obtenerPorId(id: ID): T? {
        return BaseDatosMemoria.getTabla(clase).firstOrNull { it.id == id }
    }

    override fun obtenerTodos(): List<T> {
        return BaseDatosMemoria.getTabla(clase)
    }
}