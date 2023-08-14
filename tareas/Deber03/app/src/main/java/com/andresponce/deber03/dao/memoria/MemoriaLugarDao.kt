package com.andresponce.deber03.dao.memoria

import com.andresponce.deber03.dao.LugarDao
import com.andresponce.deber03.modelo.Lugar

class MemoriaLugarDao : LugarDao, MemoriaIdAutoincrementalDao<Lugar>(Lugar::class.java)