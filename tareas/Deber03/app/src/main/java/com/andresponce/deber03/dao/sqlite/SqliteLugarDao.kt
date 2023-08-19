package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.LugarDao
import com.andresponce.deber03.modelo.Lugar

class SqliteLugarDao : LugarDao, SqliteGenericDao<Lugar>(Lugar::class.java)