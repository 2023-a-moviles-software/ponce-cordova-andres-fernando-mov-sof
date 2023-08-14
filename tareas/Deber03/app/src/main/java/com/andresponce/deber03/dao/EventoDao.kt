package com.andresponce.deber03.dao

import com.andresponce.deber03.modelo.Evento

interface EventoDao : GenericDao<Evento, Int> {
    fun encontrarPorIdDeLugar(id: Int): List<Evento>
}