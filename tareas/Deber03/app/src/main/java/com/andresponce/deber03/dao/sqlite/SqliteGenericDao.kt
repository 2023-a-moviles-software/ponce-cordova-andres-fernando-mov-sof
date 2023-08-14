package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.GenericDao
import com.andresponce.deber03.util.Identificable

open class SqliteGenericDao<T: Identificable<ID>, ID>(val clase: Class<T>) : GenericDao<T, ID> {
    override fun insertar(nuevo: T) {
        TODO("Not yet implemented")
    }

    override fun actualizar(actualizado: T) {
        TODO("Not yet implemented")
    }

    override fun eliminar(id: ID) {
        TODO("Not yet implemented")
    }

    override fun obtenerPorId(id: ID): T? {
        TODO("Not yet implemented")
    }

    override fun obtenerTodos(): List<T> {
        TODO("Not yet implemented")
    }
}