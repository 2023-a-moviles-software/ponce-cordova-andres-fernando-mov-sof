package com.andresponce.deber.dao.json

import com.andresponce.deber.dao.DaoFactory
import com.andresponce.deber.dao.EventoDao
import com.andresponce.deber.dao.LugarDao

class JsonDaoFactory: DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return JsonEventoDao();
    }

    override fun getLugarDao(): LugarDao {
        return JsonLugarDao();
    }
}