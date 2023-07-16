package com.andresponce.deber.dao.memoria

import com.andresponce.deber.dao.LugarDao
import com.andresponce.deber.modelo.Lugar

class MemoriaLugarDao:  LugarDao, MemoriaIdAutoincrementalDao<Lugar>(Lugar::class.java){
}