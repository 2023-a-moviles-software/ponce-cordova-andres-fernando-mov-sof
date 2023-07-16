package com.andresponce.deber.dao

import com.andresponce.deber.dao.memoria.MemoriaDaoFactory

abstract class DaoFactory {
    abstract fun getEventoDao(): EventoDao
    abstract fun getLugarDao(): LugarDao

    companion object{
        val daoFactory: DaoFactory = MemoriaDaoFactory();
    }
}