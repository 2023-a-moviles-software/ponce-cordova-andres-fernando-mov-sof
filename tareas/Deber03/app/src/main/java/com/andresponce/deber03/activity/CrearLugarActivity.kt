package com.andresponce.deber03.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.deber03.R
import com.andresponce.deber03.configuracion.ConfiguracionMapa
import com.andresponce.deber03.dao.DaoFactory
import com.andresponce.deber03.modelo.Lugar
import com.andresponce.deber03.util.Claves
import com.andresponce.deber03.util.ManejadorDeMapa
import com.mapbox.maps.MapView
import java.math.BigDecimal

class CrearLugarActivity : AppCompatActivity() {

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
        titulo.text = getString(R.string.nuevo_lugar)

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
        manejadorDeMapa.mostrarUbicacionInicial(
            ConfiguracionMapa.latitud,
            ConfiguracionMapa.longitud
        )

        val botonCrearLugar = findViewById<TextView>(R.id.btn_guardar_lugar)

        botonCrearLugar.setOnClickListener {
            val nuevoLugar: Lugar
            try {
                nuevoLugar = Lugar(
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
                DaoFactory.daoFactory.getLugarDao().insertar(nuevoLugar)
                mensaje = "${nuevoLugar.nombre} ${getString(R.string.registro_exito)}"
                this.resultado = RESULT_OK
            } catch (t: Throwable) {
                this.resultado = RESULT_CANCELED
                this.mensaje = getString(R.string.error, t.message)
            }
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
}