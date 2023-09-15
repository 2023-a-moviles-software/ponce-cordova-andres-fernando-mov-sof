package com.andresponce.exameniib.dao.firebase

import com.andresponce.exameniib.activity.RecyclerViewCallback
import com.andresponce.exameniib.configuracion.ConstantesPrecision
import com.andresponce.exameniib.configuracion.EstandarTiempo
import com.andresponce.exameniib.dao.EventoDao
import com.andresponce.exameniib.modelo.Evento
import com.andresponce.exameniib.modelo.Lugar
import com.google.firebase.firestore.ktx.firestore
import com.andresponce.exameniib.util.FirebaseUtils.toHashMap
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime

class FirebaseEventoDao(private val context: RecyclerViewCallback) : EventoDao {

    private val db = Firebase.firestore

    override fun encontrarPorIdDeLugar(id: String): List<Evento> {
        val eventos = mutableListOf<Evento>()

        db.collection("lugares").document(id)
            .collection("eventos")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    if (document != null) {
                        val evento = resultadoToEvento(document)
                        eventos.add(evento)
                    }
                }
                context.setData(eventos)
                context.alRecibirDatos()
            }

        return eventos
    }


    override fun insertar(nuevo: Evento) {
        val evento = nuevo.toHashMap()
        evento["lugar"] = nuevo.lugar.id
        db.collection("lugares").document(nuevo.lugar.id)
            .collection("eventos")
            .add(evento)
    }


    override fun actualizar(actualizado: Evento) {
        val evento = actualizado.toHashMap()
        db.collection("lugares").document(actualizado.lugar.id)
            .collection("eventos")
            .document(actualizado.id)
            .set(evento)
    }


    override fun eliminar(id: String) {
        db.collection("eventos").document(id).delete()
    }

    override fun obtenerPorId(id: String): Evento? {
        var evento: Evento? = null
        db.collection("eventos").document(id).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    evento = resultadoToEvento(document)
                    context.setData(listOf(evento!!))
                    context.alRecibirDatos()
                }
            }
        return evento
    }

    override fun obtenerTodos(): List<Evento> {
        val eventos = mutableListOf<Evento>()
        db.collection("eventos").get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    if (document != null) {
                        val evento = resultadoToEvento(document)
                        eventos.add(evento)
                    }
                }
                context.setData(eventos)
                context.alRecibirDatos()
            }
        return eventos
    }

    private fun resultadoToEvento(resultado: DocumentSnapshot): Evento {
        return Evento(
            id = resultado.id,
            nombre = resultado.data?.get("nombre") as String,
            descripcion = resultado.data?.get("descripcion") as String,
            lugar = Lugar(id = resultado.data?.get("lugar") as String),
            fecha = LocalDate.parse(resultado.data?.get("fecha") as String, EstandarTiempo.FORMATO_FECHA),
            horaInicio = LocalTime.parse(resultado.data?.get("horaInicio") as String, EstandarTiempo.FORMATO_HORA),
            horaFin = LocalTime.parse(resultado.data?.get("horaFin") as String, EstandarTiempo.FORMATO_HORA),
            precioDeEntrada = BigDecimal(resultado.data?.get("precioDeEntrada") as String).setScale(
                ConstantesPrecision.PRECISION_DINERO, RoundingMode.CEILING
            )
        )
    }
}
