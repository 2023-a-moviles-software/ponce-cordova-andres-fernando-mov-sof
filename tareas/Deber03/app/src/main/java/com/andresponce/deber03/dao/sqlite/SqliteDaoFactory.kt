package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.DaoFactory
import com.andresponce.deber03.dao.EventoDao
import com.andresponce.deber03.dao.LugarDao

class SqliteDaoFactory: DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return SqliteEventoDao()
    }

    override fun getLugarDao(): LugarDao {
        return SqliteLugarDao()
    }
}