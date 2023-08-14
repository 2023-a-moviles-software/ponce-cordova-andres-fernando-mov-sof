package com.andresponce.deber03.dao.memoria

import com.andresponce.deber03.datos.BaseDatosMemoria
import com.andresponce.deber03.util.Identificable

open class MemoriaIdAutoincrementalDao<T : Identificable<Int>>(clase: Class<T>) :
    MemoriaGenericDao<T, Int>(clase) {

    override fun insertar(nuevo: T) {
        nuevo.id = BaseDatosMemoria.getIndice(clase)
        super.insertar(nuevo)
        BaseDatosMemoria.incrementarIndice(clase)
    }
}