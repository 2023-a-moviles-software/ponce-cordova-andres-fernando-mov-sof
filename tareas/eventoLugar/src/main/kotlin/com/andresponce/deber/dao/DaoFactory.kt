package com.andresponce.deber.dao

import com.andresponce.deber.dao.json.JsonDaoFactory

abstract class DaoFactory {
    abstract fun getEventoDao(): EventoDao
    abstract fun getLugarDao(): LugarDao

    companion object{
        val daoFactory: DaoFactory = JsonDaoFactory();
    }
}