package com.andresponce.examen.dao

import com.andresponce.examen.modelo.Evento

interface EventoDao : GenericDao<Evento, Int> {
    fun encontrarPorIdDeLugar(id: Int): List<Evento>
}