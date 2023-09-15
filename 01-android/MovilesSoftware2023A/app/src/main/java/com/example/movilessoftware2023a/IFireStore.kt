package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class IFireStore : AppCompatActivity() {
    val query: Query? = null
    val arreglo: ArrayList<ICiudades> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifire_store)

        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener {
            crearDatosPrueba()
        }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener {
            consultarConOrderBy(adaptador)
        }

        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener {
            obtenerDocumento(adaptador)
        }

        val botonIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonIndiceCompuesto.setOnClickListener {
            consultarIndiceCompuesto(adaptador)
        }

        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener {
            crearEjemplo()
        }

        val botonEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminar.setOnClickListener {
            eliminar()
        }
    }

    private fun eliminar() {
        val db = Firebase.firestore
        val ejemplo = db.collection("ejemplo")
        ejemplo
            .document("12345678")
            .delete()
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    private fun crearEjemplo() {
        val db = Firebase.firestore
        val ejemplo = db.collection("ejemplo")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Adrian",
            "graduado" to false,
            "promedio" to 14.0,
            "direccion" to hashMapOf(
                "dirección" to "Mitad del mundo",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )

        //Identificador quemado
        ejemplo
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }

        //Identificador quemado pero autogenerado
        ejemplo
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }

        //Sin identificador
        ejemplo
            .add(datosEstudiante)
            .addOnCompleteListener{ }
            .addOnFailureListener { }
    }

    private fun consultarIndiceCompuesto(adapter: ArrayAdapter<ICiudades>) {
        val db = Firebase.firestore
        val cities = db.collection("cities")
        limpiarArreglo()
        adapter.notifyDataSetChanged()
        cities
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    agregarAArregloCiudad(arreglo, ciudad)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {}
    }

    private fun obtenerDocumento(adapter: ArrayAdapter<ICiudades>) {
        val db = Firebase.firestore
        val cities = db.collection("cities")
        limpiarArreglo()
        adapter.notifyDataSetChanged()
        cities
            .document("BJ")
            .get()
            .addOnSuccessListener {
                arreglo
                    .add(
                        ICiudades(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Number?,
                            it.data?.get("regions") as List<String>
                        )
                    )
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

            }
    }

    private fun consultarConOrderBy(adapter: ArrayAdapter<ICiudades>) {
        val db = Firebase.firestore
        val cities = db.collection("cities")
        limpiarArreglo()
        adapter.notifyDataSetChanged()
        cities
            .orderBy("population", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    ciudad.id
                    agregarAArregloCiudad(arreglo, ciudad)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

            }
    }

    private fun agregarAArregloCiudad(
        arregloNuevo: ArrayList<ICiudades>,
        ciudad: QueryDocumentSnapshot
    ) {
        arregloNuevo.add(
            ICiudades(
                ciudad.data["name"] as String?,
                ciudad.data["state"] as String?,
                ciudad.data["country"] as String?,
                ciudad.data["capital"] as Boolean?,
                ciudad.data["population"] as Number?,
                ciudad.data["regions"] as List<String>
            )
        )
    }

    private fun limpiarArreglo() {
        arreglo.clear()
    }

    private fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")
        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)
        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)
        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)
        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)
        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }
}