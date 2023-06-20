package com.andresponce.deber.dao.json

import com.andresponce.deber.util.Identificable

open class JsonIdAutoIncrementalDao<T: Identificable<Int>>(nombreArchivo: String, clase: Class<T>) :
    JsonGenericDao<T, Int>(nombreArchivo, clase){
    val idGenerador: IdGenerador<T> = IdGenerador(nombreArchivo)

    override fun insertar(nuevo: T) {
        nuevo.id = idGenerador.obtenerId() + 1
        super.insertar(nuevo)
        idGenerador.incrementarId()
    }
}