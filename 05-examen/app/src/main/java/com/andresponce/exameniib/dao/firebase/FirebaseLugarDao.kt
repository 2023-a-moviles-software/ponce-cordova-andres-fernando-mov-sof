package com.andresponce.exameniib.dao.firebase

import androidx.appcompat.app.AppCompatActivity
import com.andresponce.exameniib.activity.RecyclerViewCallback
import com.andresponce.exameniib.configuracion.ConstantesPrecision
import com.andresponce.exameniib.dao.LugarDao
import com.andresponce.exameniib.modelo.Lugar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.andresponce.exameniib.util.FirebaseUtils.toHashMap
import com.google.firebase.firestore.DocumentSnapshot
import java.math.BigDecimal
import java.math.RoundingMode

class FirebaseLugarDao(private val context: RecyclerViewCallback): LugarDao {
    
    private val db = Firebase.firestore

    override fun insertar(nuevo: Lugar) {
        val lugar = nuevo.toHashMap()
        db.collection("lugares").add(lugar)
    }

    override fun actualizar(actualizado: Lugar) {
        val lugar = actualizado.toHashMap()
        db.collection("lugares").document(actualizado.id.toString()).set(lugar)
    }

    override fun eliminar(id: String) {
        val lugar = db.collection("lugares").document(id.toString())
        lugar.delete()
    }

    override fun obtenerPorId(id: String): Lugar? {
        var lugar: Lugar? = null
        db.collection("lugares").document(id).get().addOnSuccessListener{
            if (it != null) {
                lugar = resultadoToLugar(it)
                context.setData(listOf(lugar!!))
                context.setParent(lugar!!)
                context.alRecibirDatos()
            }
        }
        return lugar
    }

    override fun obtenerTodos(): List<Lugar> {
        val lugares = mutableListOf<Lugar>()
        db.collection("lugares")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    if (document != null) {
                        val lugar = resultadoToLugar(document)
                        lugares.add(lugar)
                    }
                }
                context.setData(lugares)
                context.alRecibirDatos()
            }
        return lugares
    }

    private fun resultadoToLugar(resultado: DocumentSnapshot): Lugar {
        return Lugar(
            id = resultado.id,
            nombre = resultado.data?.get("nombre") as String,
            direccion = resultado.data?.get("direccion") as String,
            capacidad = (resultado.data?.get("capacidad") as String).toInt(),
            tieneEstacionamiento = resultado.data?.get("tieneEstacionamiento") as Boolean,
            latitud = BigDecimal(resultado.data?.get("latitud") as String).setScale(
                ConstantesPrecision.PRECISION_COORDENADAS, RoundingMode.CEILING),
            longitud = BigDecimal(resultado.data?.get("longitud") as String).setScale(
                ConstantesPrecision.PRECISION_COORDENADAS, RoundingMode.CEILING)
        )
    }
}