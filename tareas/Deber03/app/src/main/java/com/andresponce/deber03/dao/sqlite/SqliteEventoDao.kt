package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.EventoDao
import com.andresponce.deber03.modelo.Evento
import com.andresponce.deber03.modelo.Lugar
import com.andresponce.deber03.util.SqliteUtils.Companion.cursorToList

class SqliteEventoDao : SqliteGenericDao<Evento>(Evento::class.java), EventoDao {
    override fun encontrarPorIdDeLugar(id: Int): List<Evento> {
        val resultado: List<Evento>
        val readableDatabase = this.db.readableDatabase
        val selectQuery =
            "SELECT  * FROM ${Evento::class.java.simpleName} WHERE ${Lugar::class.java.simpleName} = ?"
        val cursor = readableDatabase.rawQuery(selectQuery, arrayOf(id.toString()))
        resultado = cursorToList(cursor, Evento::class.java, columnas)
        cursor.close()
        readableDatabase.close()
        return resultado
    }
}