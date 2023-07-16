package com.andresponce.deber.modelo

import com.andresponce.deber.util.Identificable
import com.andresponce.deber.util.ValidadorDeCadenaNoVacia
import com.andresponce.deber.util.ValidadorDePrecio
import java.time.LocalDate
import java.time.LocalTime

class Evento(
    id: Int = 0,
    nombre: String = "Nombre",
    descripcion: String? = "",
    lugar: Lugar = Lugar(),
    fecha: LocalDate = LocalDate.MIN,
    horaInicio: LocalTime = LocalTime.MIN,
    duracion: LocalTime = LocalTime.MIN,
    precioDeEntrada: Double = 0.0
): Identificable<Int>(id) {
    var nombre: String = nombre
        @Throws(IllegalArgumentException::class)
        set(value) {
            ValidadorDeCadenaNoVacia.validar(value, "nombre")
            field = value
        }

    var descripcion: String?

    var lugar: Lugar

    var fecha: LocalDate

    var horaInicio: LocalTime

    var duracion: LocalTime

    var precioDeEntrada: Double = precioDeEntrada
        @Throws(IllegalArgumentException::class, UnsupportedOperationException::class)
        set(value) {
            ValidadorDePrecio.validar(value)
            field = value
        }

    init {
        this.nombre = nombre
        this.descripcion = descripcion
        this.lugar = lugar
        this.fecha = fecha
        this.horaInicio = horaInicio
        this.duracion = duracion
        this.precioDeEntrada = precioDeEntrada
    }

    override fun toString(): String {
        return "Evento(id=$id, nombre='$nombre', descripcion=$descripcion, lugar=$lugar, fecha=$fecha, horaInicio=$horaInicio, duracion=$duracion, precioDeEntrada=$precioDeEntrada)"
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if(other == null){
            return false
        }

        if (other is Evento) {
            return this.id == other.id
        }
        return false
    }
}