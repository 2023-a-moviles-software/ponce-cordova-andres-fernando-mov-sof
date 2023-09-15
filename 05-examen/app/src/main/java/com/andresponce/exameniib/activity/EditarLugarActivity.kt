package com.andresponce.exameniib.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.exameniib.R
import com.andresponce.exameniib.dao.DaoFactory
import com.andresponce.exameniib.modelo.Lugar
import com.andresponce.exameniib.util.Claves
import com.andresponce.exameniib.util.ManejadorDeMapa
import com.mapbox.maps.MapView
import java.math.BigDecimal

class EditarLugarActivity : AppCompatActivity(), RecyclerViewCallback {
    private var resultado = 0
    private val intentConRespuesta = Intent()
    private var mensaje = ""
    private lateinit var lugarRef: Lugar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_lugar)

        findViewById<ImageButton>(R.id.btn_atras).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val titulo = findViewById<TextView>(R.id.tv_titulo_formulario)
        titulo.text = getString(R.string.modificando_lugar)

        val nombreField = findViewById<EditText>(R.id.input_nombre_lugar)
        val direccionField = findViewById<EditText>(R.id.input_direccion)
        val capacidadField = findViewById<EditText>(R.id.input_capacidad)
        val tieneEstacionamientoSwitch = findViewById<Switch>(R.id.sw_estacionamiento)
        val latitudLongitudTextView = findViewById<TextView>(R.id.tv_lat_long)

        val mapView = findViewById<MapView>(R.id.input_mapa)
        val manejadorDeMapa = ManejadorDeMapa(
            this,
            mapView,
            latitudLongitudTextView
        )

        val id = intent.getStringExtra(Claves.ID_LUGAR)
        if (id == null) {
            mensaje = getString(R.string.id_perdido)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        DaoFactory.daoFactory(this).getLugarDao().obtenerPorId(id)

        val botonEditarLugar = findViewById<TextView>(R.id.btn_guardar_lugar)
        botonEditarLugar.setOnClickListener {
            val nuevoLugar: Lugar
            try {
                nuevoLugar = Lugar(
                    id = id,
                    nombre = nombreField.text.toString(),
                    direccion = direccionField.text.toString(),
                    capacidad = capacidadField.text.toString().toInt(),
                    tieneEstacionamiento = tieneEstacionamientoSwitch.isChecked,
                    latitud = BigDecimal(
                        latitudLongitudTextView.text.toString().split(",")[0].trim()
                    ),
                    longitud = BigDecimal(
                        latitudLongitudTextView.text.toString().split(",")[1].trim()
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } catch (e: NumberFormatException) {
                Toast.makeText(this, getString(R.string.capacidad_no_numero), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            try {
                DaoFactory.daoFactory(this).getLugarDao().actualizar(nuevoLugar)
                mensaje = "${nuevoLugar.nombre} ${getString(R.string.edicion_exito)}"
                this.resultado = RESULT_OK
            } catch (t: Throwable) {
                this.resultado = RESULT_CANCELED
                this.mensaje = getString(R.string.error, t.message)
            }
            retornarRespuesta()
        }
    }

    private fun retornarRespuesta() {
        setResult(
            this.resultado,
            this.intentConRespuesta.putExtra(
                Claves.MENSAJE_RESULTADO,
                mensaje
            )
        )
        finish()
    }

    override fun alRecibirDatos() {
        val nombreField = findViewById<EditText>(R.id.input_nombre_lugar)
        val direccionField = findViewById<EditText>(R.id.input_direccion)
        val capacidadField = findViewById<EditText>(R.id.input_capacidad)
        val tieneEstacionamientoSwitch = findViewById<Switch>(R.id.sw_estacionamiento)
        val latitudLongitudTextView = findViewById<TextView>(R.id.tv_lat_long)

        val mapView = findViewById<MapView>(R.id.input_mapa)
        val manejadorDeMapa = ManejadorDeMapa(
            this,
            mapView,
            latitudLongitudTextView
        )
        nombreField.setText(lugarRef.nombre)
        direccionField.setText(lugarRef.direccion)
        capacidadField.setText(lugarRef.capacidad.toString())
        tieneEstacionamientoSwitch.isChecked = lugarRef.tieneEstacionamiento
        manejadorDeMapa.mostrarUbicacionInicial(
            lugarRef.latitud,
            lugarRef.longitud,
        )

    }

    override fun setData(data: List<Any>) {
        this.lugarRef = data[0] as Lugar
    }

    override fun setParent(data: Any) {}
}