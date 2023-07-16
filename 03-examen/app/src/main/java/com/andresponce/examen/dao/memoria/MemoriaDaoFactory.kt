package com.andresponce.examen.dao.memoria

import com.andresponce.examen.dao.DaoFactory
import com.andresponce.examen.dao.EventoDao
import com.andresponce.examen.dao.LugarDao

class MemoriaDaoFactory : DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return MemoriaEventoDao()
    }

    override fun getLugarDao(): LugarDao {
        return MemoriaLugarDao()
    }
}