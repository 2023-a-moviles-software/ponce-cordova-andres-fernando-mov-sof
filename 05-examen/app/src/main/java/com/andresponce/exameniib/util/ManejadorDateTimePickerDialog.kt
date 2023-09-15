package com.andresponce.exameniib.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.andresponce.exameniib.configuracion.EstandarTiempo
import java.time.LocalDate
import java.time.LocalTime

class ManejadorDateTimePickerDialog {
    companion object {
        fun mostrarDialogoFecha(contexto: AppCompatActivity, editText: EditText) {
            val dialogoFecha = DatePickerDialog(
                contexto,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    editText.setText(
                        LocalDate.of(year, month + 1, dayOfMonth)
                            .format(EstandarTiempo.FORMATO_FECHA)
                    )
                },
                LocalDate.now().year,
                LocalDate.now().monthValue - 1,
                LocalDate.now().dayOfMonth
            )
            dialogoFecha.show()
        }

        fun mostrarDialogoHora(contexto: AppCompatActivity, editText: EditText) {
            val dialogoHora = TimePickerDialog(
                contexto,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    editText.setText(
                        LocalTime.of(hourOfDay, minute).format(EstandarTiempo.FORMATO_HORA)
                    )
                },
                LocalTime.now().hour,
                LocalTime.now().minute,
                true
            )
            dialogoHora.show()
        }
    }
}