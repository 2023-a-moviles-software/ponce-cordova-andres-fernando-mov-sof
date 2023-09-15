package com.andresponce.exameniib.dao

import com.andresponce.exameniib.modelo.Evento

interface EventoDao : GenericDao<Evento, String> {
    fun encontrarPorIdDeLugar(id: String): List<Evento>
}