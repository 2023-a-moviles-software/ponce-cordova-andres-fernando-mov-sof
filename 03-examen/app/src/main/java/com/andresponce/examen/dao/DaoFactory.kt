package com.andresponce.examen.dao

import com.andresponce.examen.dao.memoria.MemoriaDaoFactory

abstract class DaoFactory {
    abstract fun getEventoDao(): EventoDao
    abstract fun getLugarDao(): LugarDao

    companion object {
        val daoFactory: DaoFactory = MemoriaDaoFactory()
    }
}