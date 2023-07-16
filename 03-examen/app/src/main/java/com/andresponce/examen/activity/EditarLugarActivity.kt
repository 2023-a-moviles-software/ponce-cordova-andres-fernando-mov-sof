package com.andresponce.examen.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.examen.R
import com.andresponce.examen.configuracion.ConfiguracionMapa
import com.andresponce.examen.dao.DaoFactory
import com.andresponce.examen.modelo.Lugar
import com.andresponce.examen.util.Claves
import com.andresponce.examen.util.ManejadorDeMapa
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import java.math.BigDecimal

class EditarLugarActivity : AppCompatActivity() {
    private var resultado = 0
    private val intentConRespuesta = Intent()
    private var mensaje = ""

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

        val id = intent.getIntExtra(Claves.ID_LUGAR, -1)
        if (id == -1) {
            mensaje = getString(R.string.id_perdido)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        val encontrado = DaoFactory.daoFactory.getLugarDao().obtenerPorId(id)
        if (encontrado == null) {
            mensaje = getString(R.string.no_encontrado)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }
        nombreField.setText(encontrado.nombre)
        direccionField.setText(encontrado.direccion)
        capacidadField.setText(encontrado.capacidad.toString())
        tieneEstacionamientoSwitch.isChecked = encontrado.tieneEstacionamiento
        manejadorDeMapa.mostrarUbicacionInicial(
            encontrado.latitud,
            encontrado.longitud,
        )

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
                    latitud = BigDecimal(latitudLongitudTextView.text.toString().split(",")[0].trim()),
                    longitud = BigDecimal(latitudLongitudTextView.text.toString().split(",")[1].trim())
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
                DaoFactory.daoFactory.getLugarDao().actualizar(nuevoLugar)
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
}