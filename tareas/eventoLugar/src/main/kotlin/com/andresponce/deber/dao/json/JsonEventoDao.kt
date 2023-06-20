package com.andresponce.deber.dao.json

import com.andresponce.deber.configuracion.NombresArchivosJson
import com.andresponce.deber.dao.EventoDao
import com.andresponce.deber.modelo.Evento

class JsonEventoDao: JsonIdAutoIncrementalDao<Evento>(NombresArchivosJson.EVENTOS, Evento::class.java), EventoDao {
}