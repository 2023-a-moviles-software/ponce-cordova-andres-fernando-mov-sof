package com.andresponce.deber03.util

import android.content.ContentValues
import android.database.Cursor
import com.andresponce.deber03.configuracion.ConstantesPrecision
import com.andresponce.deber03.configuracion.EstandarTiempo
import com.andresponce.deber03.dao.sqlite.SqliteGenericDao
import java.lang.reflect.Field
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime

class SqliteUtils {
    companion object {
        fun <T : Identificable<Int>> T.toContentValues(): ContentValues {
            val contentValues = ContentValues()

            val properties = this::class.java.declaredFields
            for (property in properties) {
                property.isAccessible = true

                when (val value = property.get(this)) {
                    is String, is Int, is Long, is Double, is Float, is BigDecimal ->
                        contentValues.put(property.name, value.toString())
                    is Boolean -> contentValues.put(property.name, if (value) 1 else 0)
                    is LocalDate -> contentValues.put(
                        property.name, value.format(EstandarTiempo.FORMATO_FECHA)
                    )
                    is LocalTime -> contentValues.put(
                        property.name, value.format(EstandarTiempo.FORMATO_HORA)
                    )
                    is Identificable<*> -> contentValues.put(property.name, (value as Identificable<Int>).id)
                }
            }
            return contentValues
        }

        inline fun <reified T : Identificable<Int>> Class<out T>.toCreateTableScript(): String {
            val properties = getAllDeclaredFields(this)
            val tableName = this.simpleName
            var foreignKeys: MutableMap<String, String> = mutableMapOf()

            val createTableScript = buildString {
                appendLine("CREATE TABLE IF NOT EXISTS $tableName (")

                for (property in properties) {
                    property.isAccessible = true
                    val columnName: String = property.name
                    val dataType: String = when {
                        Identificable::class.java.isAssignableFrom(property.type) -> {
                            foreignKeys[columnName] = property.type.simpleName
                            "INTEGER"
                        }

                        property.type in setOf(Int::class.java, Boolean::class.java) -> "INTEGER"

                        property.type == Object::class.java -> "INTEGER PRIMARY KEY AUTOINCREMENT"

                        property.type in setOf(
                            LocalDate::class.java,
                            LocalTime::class.java,
                            String::class.java
                        ) -> "TEXT"

                        property.type == BigDecimal::class.java -> "REAL"
                        else -> "TEXT"
                    }
                    appendLine("\t$columnName $dataType,")
                }
                for ((columna, referencia) in foreignKeys) {
                    appendLine("\tFOREIGN KEY ($columna) REFERENCES $referencia(${Identificable::class.java.declaredFields[0].name}) ON DELETE CASCADE,")
                }
                deleteRange(length - 2, length - 1)
                appendLine(");")
            }

            return createTableScript
        }

        fun getAllDeclaredFields(clazz: Class<*>): List<Field> {
            val fields = mutableListOf<Field>()
            fields.addAll(clazz.declaredFields)

            val superClass = clazz.superclass
            if (superClass != null) {
                fields.addAll(getAllDeclaredFields(superClass))
            }

            return fields
        }

        fun <T : Identificable<Int>> cursorToList(
            cursor: Cursor,
            clazz: Class<T>,
            columnNamesTypes: Map<String, String>
        ): List<T> {
            val resultList = mutableListOf<T>()
            val columnNames = columnNamesTypes.keys
            if (cursor.moveToFirst()) {
                do {
                    val item = clazz.newInstance()
                    for (columnName in columnNames) {
                        val columnIndex = cursor.getColumnIndex(columnName)
                        val value = when (columnNamesTypes[columnName]!!.uppercase()) {
                            "INTEGER" -> cursor.getInt(columnIndex)
                            "REAL" -> cursor.getDouble(columnIndex)
                            "TEXT" -> cursor.getString(columnIndex)
                            "BLOB" -> cursor.getBlob(columnIndex)
                            else -> 0
                        }
                        setFieldValue(item, columnName, value)
                    }
                    resultList.add(item)
                } while (cursor.moveToNext())
            }

            return resultList
        }

        fun <T : Identificable<Int>> cursorToFirstResult(
            cursor: Cursor,
            clazz: Class<T>,
            columnNamesTypes: Map<String, String>
        ): T? {
            val columnNames = columnNamesTypes.keys
            if (cursor.moveToFirst()) {
                val item = clazz.newInstance()
                for (columnName in columnNames) {
                    val columnIndex = cursor.getColumnIndex(columnName)
                    val value: Any = when (columnNamesTypes[columnName]?.uppercase()) {
                        "INTEGER" -> cursor.getInt(columnIndex)
                        "REAL" -> cursor.getDouble(columnIndex)
                        "TEXT" -> cursor.getString(columnIndex)
                        "BLOB" -> cursor.getBlob(columnIndex)
                        else -> 0
                    }
                    setFieldValue(item, columnName, value)
                }
                return item
            }
            return null
        }

        private fun <T : Identificable<Int>> setFieldValue(
            obj: T,
            fieldName: String,
            value: Any
        ) {
            val field = getDeclaredField(fieldName, obj::class.java)!!
            field.isAccessible = true

            when {
                field.type == Int::class.java -> field.setInt(obj, value as Int)
                field.type == String::class.java -> field.set(obj, value as String)
                field.type == Boolean::class.java -> field.setBoolean(obj, (value as Int) == 1)
                field.type == LocalDate::class.java -> field.set(
                    obj,
                    LocalDate.parse(value as String, EstandarTiempo.FORMATO_FECHA)
                )
                field.type == LocalTime::class.java -> field.set(
                    obj,
                    LocalTime.parse(value as String, EstandarTiempo.FORMATO_HORA)
                )
                field.type == BigDecimal::class.java -> field.set(
                    obj,
                    BigDecimal(value.toString()).setScale(
                        ConstantesPrecision.PRECISION_COORDENADAS,
                        RoundingMode.CEILING
                    )
                )
                Identificable::class.java.isAssignableFrom(field.type) -> field.set(
                    obj,
                    SqliteGenericDao(field.type as Class<out Identificable<Int>>).obtenerPorId(value as Int)
                )
                else -> field.set(obj, value)
            }
        }

        private fun getDeclaredField(fieldName: String, clazz: Class<*>): Field? {
            val fields = getAllDeclaredFields(clazz)
            for (field in fields) {
                if (field.name == fieldName) {
                    return field
                }
            }
            return null
        }
    }
}