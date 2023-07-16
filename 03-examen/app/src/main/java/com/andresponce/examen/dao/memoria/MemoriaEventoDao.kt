package com.andresponce.examen.dao.memoria

import com.andresponce.examen.dao.EventoDao
import com.andresponce.examen.datos.BaseDatosMemoria
import com.andresponce.examen.modelo.Evento

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