package com.andresponce.deber.dao.memoria

import com.andresponce.deber.dao.EventoDao
import com.andresponce.deber.modelo.Evento

class MemoriaEventoDao: MemoriaIdAutoincrementalDao<Evento>(Evento::class.java), EventoDao {
    override fun encontrarPorIdDeLugar(id: Int): List<Evento> {
        val eventosPorEvento = mutableListOf<Evento>()
        for (evento in BaseDatosMemoria.getTabla(clase)){
            if (evento.lugar.id == id){
                eventosPorEvento.add(evento)
            }
        }
        return eventosPorEvento
    }

}