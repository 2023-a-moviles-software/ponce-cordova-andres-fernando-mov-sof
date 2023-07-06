package com.example.movilessoftware2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?):SQLiteOpenHelper(
                            contexto,
                        "Moviles",
                        null,
                        1) {
    override fun onCreate(db: SQLiteDatabase) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre varchar(50),
                    descripcion varchar(50)
                )
            """.trimIndent()
        db.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(nombre: String, descripcion: String): Boolean{
        val db = this.writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = db.insert(
            "ENTRENADOR",
            null,
            valoresAGuardar
        )
        db.close()
        return resultadoGuardar.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(
        id: Int,
        nombre: String,
        descripcion: String
    ): Boolean{
        val db = this.writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val parametroConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizar = db.update(
            "ENTRENADOR",
            valoresAActualizar,
            "id=?",
            parametroConsultaActualizar
        )
        db.close()
        return resultadoActualizar.toInt() != -1
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean{
        val db = this.writableDatabase
        val parametroConsultaDelete = arrayOf(id.toString())
        val resultadoDelete = db.delete(
            "ENTRENADOR",
            "id=?",
            parametroConsultaDelete
        )
        db.close()
        return resultadoDelete.toInt() != -1
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador{
        val db = this.readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()

        val parametroConsulta = arrayOf(id.toString())

        val resultadosConsultaLectura = db.rawQuery(scriptConsultaLectura, parametroConsulta)

        //Lógica de búsqueda
        val existeUsuario = resultadosConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        val arreglo = arrayListOf<BEntrenador>()
        if (existeUsuario){
            do {
                val idEncontrado = resultadosConsultaLectura.getInt(0)
                val nombreEncontrado = resultadosConsultaLectura.getString(1)
                val descripcionEncontrado = resultadosConsultaLectura.getString(2)
                if (idEncontrado != null){
                    usuarioEncontrado.id = idEncontrado
                    usuarioEncontrado.nombre = nombreEncontrado
                    usuarioEncontrado.descripcion = descripcionEncontrado
                }
            }while (resultadosConsultaLectura.moveToNext())
        }
        resultadosConsultaLectura.close()
        db.close()
        return usuarioEncontrado
    }
}