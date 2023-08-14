package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.EventoDao
import com.andresponce.deber03.modelo.Evento

class SqliteEventoDao: SqliteIdAutoincrementalDao<Evento>(Evento::class.java), EventoDao {
    override fun encontrarPorIdDeLugar(id: Int): List<Evento> {
        TODO("Not yet implemented")
    }
}