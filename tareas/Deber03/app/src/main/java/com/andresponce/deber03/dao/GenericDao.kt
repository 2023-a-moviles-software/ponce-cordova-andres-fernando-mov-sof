package com.andresponce.deber03.dao

interface GenericDao<T, ID> {
    fun insertar(nuevo: T)
    fun actualizar(actualizado: T)
    fun eliminar(id: ID)
    fun obtenerPorId(id: ID): T?
    fun obtenerTodos(): List<T>
}