package com.andresponce.examen.dao.memoria

import com.andresponce.examen.dao.LugarDao
import com.andresponce.examen.modelo.Lugar

class MemoriaLugarDao : LugarDao, MemoriaIdAutoincrementalDao<Lugar>(Lugar::class.java)