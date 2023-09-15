package com.andresponce.exameniib.modelo

import com.andresponce.exameniib.util.Identificable
import com.andresponce.exameniib.util.ValidadorDeCadenaNoVacia
import com.andresponce.exameniib.util.ValidadorDePrecio
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class Evento(
    id: String = "",
    nombre: String = "Nombre",
    descripcion: String? = "",
    lugar: Lugar = Lugar(),
    fecha: LocalDate = LocalDate.MIN,
    horaInicio: LocalTime = LocalTime.MIN,
    horaFin: LocalTime = LocalTime.MIN,
    precioDeEntrada: BigDecimal = BigDecimal("0.0")
) : Identificable<String>(id) {
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

    var horaFin: LocalTime

    var precioDeEntrada: BigDecimal = precioDeEntrada
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
        this.horaFin = horaFin
        this.precioDeEntrada = precioDeEntrada
    }

    override fun toString(): String {
        return "Evento(id=$id, nombre='$nombre', descripcion=$descripcion, lugar=$lugar, fecha=$fecha, horaInicio=$horaInicio, duracion=$horaFin, precioDeEntrada=$precioDeEntrada)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other is Evento) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result = 31 * result + (descripcion?.hashCode() ?: 0)
        result = 31 * result + lugar.hashCode()
        result = 31 * result + fecha.hashCode()
        result = 31 * result + horaInicio.hashCode()
        result = 31 * result + horaFin.hashCode()
        result = 31 * result + precioDeEntrada.hashCode()
        return result
    }
}