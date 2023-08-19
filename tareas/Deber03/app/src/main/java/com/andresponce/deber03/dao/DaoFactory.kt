package com.andresponce.deber03.dao

import com.andresponce.deber03.dao.sqlite.SqliteDaoFactory

abstract class DaoFactory {
    abstract fun getEventoDao(): EventoDao
    abstract fun getLugarDao(): LugarDao

    companion object {
        val daoFactory: DaoFactory = SqliteDaoFactory()
    }
}