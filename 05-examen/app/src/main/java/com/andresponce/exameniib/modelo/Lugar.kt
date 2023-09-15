package com.andresponce.exameniib.modelo

import com.andresponce.exameniib.util.Identificable
import com.andresponce.exameniib.util.ValidadorDeCadenaNoVacia
import com.andresponce.exameniib.util.ValidadorDeLatitud
import com.andresponce.exameniib.util.ValidadorDeLongitud
import com.andresponce.exameniib.util.ValidadorNumeroPositivo
import java.math.BigDecimal

class Lugar(
    id: String = "",
    nombre: String = "Nombre",
    direccion: String = "Direccion",
    latitud: BigDecimal = BigDecimal(0.0),
    longitud: BigDecimal = BigDecimal(0.0),
    capacidad: Int = 0,
    tieneEstacionamiento: Boolean = false
) : Identificable<String>(id) {

    var nombre: String = nombre
        @Throws(IllegalArgumentException::class)
        set(value) {
            ValidadorDeCadenaNoVacia.validar(value, "nombre")
            field = value
        }

    var direccion: String = direccion
        @Throws(IllegalArgumentException::class)
        set(value) {
            ValidadorDeCadenaNoVacia.validar(value, "direccion")
            field = value
        }

    var latitud: BigDecimal = latitud
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        set(value) {
            ValidadorDeLatitud.validar(value)
            field = value
        }

    var longitud: BigDecimal = longitud
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        set(value) {
            ValidadorDeLongitud.validar(value)
            field = value
        }

    var capacidad: Int = capacidad
        @Throws(IllegalArgumentException::class)
        set(value) {
            ValidadorNumeroPositivo.validar(BigDecimal(value), "capacidad")
            field = value
        }

    var tieneEstacionamiento: Boolean

    init {
        this.nombre = nombre
        this.direccion = direccion
        this.capacidad = capacidad
        this.tieneEstacionamiento = tieneEstacionamiento
        this.latitud = latitud
        this.longitud = longitud
    }

    override fun toString(): String {
        return "Lugar(id=$id, nombre='$nombre', direccion='$direccion', latitud=$latitud, longitud=$longitud, capacidad=$capacidad, tieneEstacionamiento=$tieneEstacionamiento)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other is Lugar) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result = 31 * result + direccion.hashCode()
        result = 31 * result + latitud.hashCode()
        result = 31 * result + longitud.hashCode()
        result = 31 * result + capacidad
        result = 31 * result + tieneEstacionamiento.hashCode()
        return result
    }
}