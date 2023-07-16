package com.andresponce.deber.modelo

import com.andresponce.deber.util.Identificable
import com.andresponce.deber.util.ValidadorDeCadenaNoVacia
import com.andresponce.deber.util.ValidadorDeLatitud
import com.andresponce.deber.util.ValidadorDeLongitud
import com.andresponce.deber.util.ValidadorNumeroPositivo
import java.math.BigDecimal

class Lugar(
    id: Int = 0,
    nombre: String = "Nombre",
    direccion: String = "Direccion",
    latitud: BigDecimal = BigDecimal(0.0),
    longitud: BigDecimal = BigDecimal(0.0),
    capacidad: Int = 0,
    tieneEstacionamiento: Boolean = false
): Identificable<Int>(id) {

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
            ValidadorNumeroPositivo.validar(value.toDouble(), "capacidad")
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

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null){
            return false
        }

        if (other is Lugar) {
            return this.id == other.id
        }
        return false
    }
}