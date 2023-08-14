package com.andresponce.deber03.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.deber03.R
import com.andresponce.deber03.configuracion.ConstantesPrecision
import com.andresponce.deber03.configuracion.EstandarTiempo
import com.andresponce.deber03.dao.DaoFactory
import com.andresponce.deber03.modelo.Evento
import com.andresponce.deber03.modelo.Lugar
import com.andresponce.deber03.util.Claves
import com.andresponce.deber03.util.ManejadorDateTimePickerDialog
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime

class EditarEventoActivity : AppCompatActivity() {
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

        val titulo = findViewById<TextView>(R.id.tv_titulo_formulario)
        titulo.text = getString(R.string.modificando_evento)

        val idLugarSeleccionado = intent.getIntExtra(Claves.ID_LUGAR, -1)
        val idEventoSeleccionado = intent.getIntExtra(Claves.ID_EVENTO, -1)

        if (idLugarSeleccionado == -1 || idEventoSeleccionado == -1) {
            mensaje = getString(R.string.id_perdido)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        lugar = DaoFactory.daoFactory.getLugarDao().obtenerPorId(idLugarSeleccionado)
        val evento = DaoFactory.daoFactory.getEventoDao().obtenerPorId(idEventoSeleccionado)
        if (lugar == null || evento == null) {
            mensaje = getString(R.string.no_encontrado)
            resultado = RESULT_CANCELED
            retornarRespuesta()
            return
        }

        val nombreField = findViewById<EditText>(R.id.input_nombre_evento)
        nombreField.setText(evento.nombre)

        val descripcionField = findViewById<EditText>(R.id.input_descripcion)
        descripcionField.setText(evento.descripcion)

        val inputFecha = findViewById<EditText>(R.id.input_fecha)
        inputFecha.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoFecha(this, inputFecha)
        }
        inputFecha.setText(evento.fecha.format(EstandarTiempo.FORMATO_FECHA))

        val inputHoraInicio = findViewById<EditText>(R.id.input_hora_inicio)
        inputHoraInicio.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoHora(this, inputHoraInicio)
        }
        inputHoraInicio.setText(evento.horaInicio.format(EstandarTiempo.FORMATO_HORA))

        val inputHoraFin = findViewById<EditText>(R.id.input_hora_fin)
        inputHoraFin.setOnClickListener {
            ManejadorDateTimePickerDialog.mostrarDialogoHora(this, inputHoraFin)
        }
        inputHoraFin.setText(evento.horaFin.format(EstandarTiempo.FORMATO_HORA))

        val inputPrecio = findViewById<EditText>(R.id.input_precio)
        inputPrecio.setText(evento.precioDeEntrada.toPlainString())

        val botonEditarEvento = findViewById<Button>(R.id.btn_guardar_evento)
        botonEditarEvento.setOnClickListener {
            val nuevoEvento: Evento
            try {
                nuevoEvento = Evento(
                    id = evento.id,
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
                        ConstantesPrecision.PRECISION_DINERO, RoundingMode.CEILING
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
                DaoFactory.daoFactory.getEventoDao().actualizar(nuevoEvento)
                mensaje = "${nuevoEvento.nombre} ${getString(R.string.edicion_exito)}"
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