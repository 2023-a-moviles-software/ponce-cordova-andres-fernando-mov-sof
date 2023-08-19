package com.andresponce.deber03.datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.andresponce.deber03.configuracion.Database
import com.andresponce.deber03.util.SqliteUtils.Companion.toCreateTableScript

class BaseDatosHelper(contexto: Context?) : SQLiteOpenHelper(
    contexto,
    Database.DATABASE_NAME,
    null,
    Database.DATABASE_VERSION
) {
    companion object {
        var baseDeDatos: BaseDatosHelper = BaseDatosHelper(null)
    }

    override fun onCreate(db: SQLiteDatabase) {
        for (entidad in Database.DATABASE_ENTIDADES) {
            val scriptTabla = entidad.toCreateTableScript()
            db.execSQL(scriptTabla)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun onConfigure(db: SQLiteDatabase) {
        db.setForeignKeyConstraintsEnabled(true)
    }
}