package com.andresponce.deber03.util

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class NavegarActividades {
    companion object {
        fun irActividad(
            contexto: AppCompatActivity,
            clase: Class<*>,
            atributos: Map<String, Serializable>? = null
        ) {
            val intent = Intent(contexto, clase)
            if (atributos != null) {
                for ((clave, valor) in atributos) {
                    intent.putExtra(clave, valor)
                }
            }
            contexto.startActivity(intent)
        }

        fun irActividadPorResultados(
            contexto: AppCompatActivity,
            clase: Class<*>,
            launcher: ActivityResultLauncher<Intent>,
            atributos: Map<String, Serializable>? = null,
        ) {
            val intent = Intent(contexto, clase)
            if (atributos != null) {
                for ((clave, valor) in atributos) {
                    intent.putExtra(clave, valor)
                }
            }
            launcher.launch(intent)
        }
    }
}