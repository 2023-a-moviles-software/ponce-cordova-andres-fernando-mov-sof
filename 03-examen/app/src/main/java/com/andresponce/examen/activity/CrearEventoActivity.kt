package com.andresponce.examen.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.examen.R
import com.andresponce.examen.configuracion.ConstantesPrecision
import com.andresponce.examen.configuracion.EstandarTiempo
import com.andresponce.examen.dao.DaoFactory
import com.andresponce.examen.modelo.Evento
import com.andresponce.examen.modelo.Lugar
import com.andresponce.examen.util.Claves
import com.andresponce.examen.util.ManejadorDateTimePickerDialog
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime

class CrearEventoActivity : AppCompatActivity() {
    private var lugar: Lugar? = null
    private var resultado = 0
    private val intentConRespuesta = Intent()
    private var mensaje = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_evento)

        findViewById<ImageButton>(R.id.btn_atras).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val idLugarSeleccionado = intent.getIntExtra(Claves.ID_LUGAR, -1)

        if (idLugarSeleccionado == -1) {
            mensaje = getString(R.string.id_perdido)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        lugar = DaoFactory.daoFactory.getLugarDao().obtenerPorId(idLugarSeleccionado)
        if (lugar == null) {
            mensaje = getString(R.string.no_encontrado)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        val tituloFormulario = findViewById<TextView>(R.id.tv_titulo_formulario)
        tituloFormulario.text = getString(R.string.nuevo_evento)

        val nombreField = findViewById<EditText>(R.id.input_nombre_evento)
        val descripcionField = findViewById<EditText>(R.id.input_descripcion)

        val inputFecha = findViewById<EditText>(R.id.input_fecha)
        inputFecha.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoFecha(this, inputFecha)
        }

        val inputHoraInicio = findViewById<EditText>(R.id.input_hora_inicio)
        inputHoraInicio.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoHora(this, inputHoraInicio)
        }

        val inputHoraFin = findViewById<EditText>(R.id.input_hora_fin)
        inputHoraFin.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoHora(this, inputHoraFin)
        }

        val inputPrecio = findViewById<EditText>(R.id.input_precio)

        val botonCrearEvento = findViewById<Button>(R.id.btn_guardar_evento)
        botonCrearEvento.setOnClickListener {
            val nuevoEvento: Evento
            try {
                nuevoEvento = Evento(
                    nombre = nombreField.text.toString(),
                    descripcion = descripcionField.text.toString(),
                    fecha = LocalDate.parse(
                        inputFecha.text.toString(),
                        EstandarTiempo.FORMATO_FECHA
                    ),
                    horaInicio = LocalTime.parse(
                        inputHoraInicio.text.toString(),
                        EstandarTiempo.FORMATO_HORA
                    ),
                    horaFin = LocalTime.parse(
                        inputHoraFin.text.toString(),
                        EstandarTiempo.FORMATO_HORA
                    ),
                    precioDeEntrada = BigDecimal(inputPrecio.text.toString()).setScale(
                        ConstantesPrecision.PRECISION_DINERO,
                        RoundingMode.CEILING
                    ),
                    lugar = lugar!!
                )
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } catch (e: NumberFormatException) {
                Toast.makeText(this, getString(R.string.precio_no_numero), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            try {
                DaoFactory.daoFactory.getEventoDao().insertar(nuevoEvento)
                mensaje = "${nuevoEvento.nombre} ${getString(R.string.registro_exito)}"
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