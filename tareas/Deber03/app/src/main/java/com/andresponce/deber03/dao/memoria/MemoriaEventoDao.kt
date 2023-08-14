package com.andresponce.deber03.dao.memoria

import com.andresponce.deber03.dao.EventoDao
import com.andresponce.deber03.datos.BaseDatosMemoria
import com.andresponce.deber03.modelo.Evento

class MemoriaEventoDao : MemoriaIdAutoincrementalDao<Evento>(Evento::class.java), EventoDao {
    override fun encontrarPorIdDeLugar(id: Int): List<Evento> {
        val eventosPorLugar = mutableListOf<Evento>()
        for (evento in BaseDatosMemoria.getTabla(clase)) {
            if (evento.lugar.id == id) {
                eventosPorLugar.add(evento)
            }
        }
        return eventosPorLugar
    }
}