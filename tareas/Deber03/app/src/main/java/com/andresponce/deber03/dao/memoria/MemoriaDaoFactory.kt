package com.andresponce.deber03.dao.memoria

import com.andresponce.deber03.dao.DaoFactory
import com.andresponce.deber03.dao.EventoDao
import com.andresponce.deber03.dao.LugarDao

class MemoriaDaoFactory : DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return MemoriaEventoDao()
    }

    override fun getLugarDao(): LugarDao {
        return MemoriaLugarDao()
    }
}