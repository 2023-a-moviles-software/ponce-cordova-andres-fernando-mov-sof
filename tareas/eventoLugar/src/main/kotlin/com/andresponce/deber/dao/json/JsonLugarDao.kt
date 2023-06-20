package com.andresponce.deber.dao.json

import com.andresponce.deber.configuracion.NombresArchivosJson
import com.andresponce.deber.dao.LugarDao
import com.andresponce.deber.modelo.Lugar

class JsonLugarDao: LugarDao, JsonIdAutoIncrementalDao<Lugar>(NombresArchivosJson.LUGARES, Lugar::class.java){

}