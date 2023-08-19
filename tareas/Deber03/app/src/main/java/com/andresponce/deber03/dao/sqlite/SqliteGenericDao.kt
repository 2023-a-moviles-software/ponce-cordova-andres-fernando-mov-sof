package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.dao.GenericDao
import com.andresponce.deber03.datos.BaseDatosHelper
import com.andresponce.deber03.util.Identificable
import com.andresponce.deber03.util.SqliteUtils
import com.andresponce.deber03.util.SqliteUtils.Companion.cursorToFirstResult
import com.andresponce.deber03.util.SqliteUtils.Companion.cursorToList
import com.andresponce.deber03.util.SqliteUtils.Companion.toContentValues

open class SqliteGenericDao<T : Identificable<Int>>(private val clase: Class<T>) :
    GenericDao<T, Int> {

    protected val db = BaseDatosHelper.baseDeDatos
    protected val columnas: Map<String, String>

    init {
        columnas = classAttributesToMap()
    }

    override fun insertar(nuevo: T) {
        val writableDatabase = this.db.writableDatabase
        val contentValues = nuevo.toContentValues()
        writableDatabase.insert(
            clase.simpleName,
            null,
            contentValues
        )
        writableDatabase?.close()
    }

    override fun actualizar(actualizado: T) {
        val writableDatabase = this.db.writableDatabase
        val contentValues = actualizado.toContentValues()
        writableDatabase.update(
            clase.simpleName,
            contentValues,
            "${Identificable::class.java.declaredFields[0].name} = ?",
            arrayOf(actualizado.id.toString())
        )
        writableDatabase.close()
    }

    override fun eliminar(id: Int) {
        val writableDatabase = this.db.writableDatabase
        writableDatabase.delete(
            clase.simpleName,
            "${Identificable::class.java.declaredFields[0].name} = ?",
            arrayOf(id.toString())
        )
        writableDatabase.close()
    }

    override fun obtenerPorId(id: Int): T? {
        val resultado: T?
        val selectQuery =
            "SELECT * FROM ${clase.simpleName} WHERE ${Identificable::class.java.declaredFields[0].name} = ?"
        val readableDatabase = this.db.readableDatabase
        val cursor = readableDatabase.rawQuery(selectQuery, arrayOf(id.toString()))
        resultado = cursorToFirstResult(cursor, clase, columnas)
        cursor.close()
        readableDatabase.close()
        return resultado
    }

    override fun obtenerTodos(): List<T> {
        val resultado: List<T>
        val selectQuery = "SELECT * FROM ${clase.simpleName}"
        val readableDatabase = this.db.readableDatabase
        val cursor = readableDatabase.rawQuery(selectQuery, null)
        resultado = cursorToList(cursor, clase, columnas)
        cursor.close()
        readableDatabase.close()
        return resultado
    }

    private fun getColumnType(columnName: String): String? {
        val readableDatabase = this.db.readableDatabase
        val query = "PRAGMA table_info(${this.clase.simpleName})"
        val cursor = readableDatabase.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                if (name == columnName) {
                    cursor.close()
                    readableDatabase.close()
                    return type
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        readableDatabase.close()
        return null
    }

    private fun classAttributesToMap(): Map<String, String> {
        val resultado = mutableMapOf<String, String>()
        SqliteUtils.getAllDeclaredFields(this.clase).forEach {
            val name = it.name
            val type = getColumnType(it.name) ?: ""
            resultado[name] = type
        }
        return resultado
    }
}
