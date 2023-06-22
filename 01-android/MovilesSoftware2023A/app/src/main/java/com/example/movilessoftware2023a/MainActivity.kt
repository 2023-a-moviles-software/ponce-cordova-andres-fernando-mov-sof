package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //Lógica del negocio
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se crea una variable para el botón ciclo de vida
        val botonACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        //Se añade un listener al botón ciclo de vida
        botonACicloVida.setOnClickListener {
            irActividad(AACicloVida::class.java)
        }

        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )

        botonIrListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar parámetros
        //(aceptamos primitivas)
        intentExplicito.putExtra("nombre","Adrian")
        intentExplicito.putExtra("apellido","Eguez")
        intentExplicito.putExtra("edad",30)
        //Enviamos el intent con RESPUESTA
        //RECIBIMOS RESPUESTA
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}