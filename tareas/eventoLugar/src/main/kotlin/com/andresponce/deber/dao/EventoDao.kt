package com.andresponce.deber.dao

import com.andresponce.deber.modelo.Evento

interface EventoDao: GenericDao<Evento, Int> {
    fun encontrarPorIdDeLugar(id: Int): List<Evento>
}