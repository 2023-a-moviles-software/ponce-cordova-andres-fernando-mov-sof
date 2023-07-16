package com.andresponce.deber.dao.memoria

import com.andresponce.deber.dao.DaoFactory
import com.andresponce.deber.dao.EventoDao
import com.andresponce.deber.dao.LugarDao

class MemoriaDaoFactory: DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return MemoriaEventoDao()
    }

    override fun getLugarDao(): LugarDao {
        return MemoriaLugarDao()
    }
}