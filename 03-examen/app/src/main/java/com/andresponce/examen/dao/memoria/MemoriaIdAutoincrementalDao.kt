package com.andresponce.examen.dao.memoria

import com.andresponce.examen.datos.BaseDatosMemoria
import com.andresponce.examen.util.Identificable

open class MemoriaIdAutoincrementalDao<T : Identificable<Int>>(clase: Class<T>) :
    MemoriaGenericDao<T, Int>(clase) {

    override fun insertar(nuevo: T) {
        nuevo.id = BaseDatosMemoria.getIndice(clase)
        super.insertar(nuevo)
        BaseDatosMemoria.incrementarIndice(clase)
    }
}