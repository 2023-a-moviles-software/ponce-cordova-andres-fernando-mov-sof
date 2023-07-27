package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
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

    val callbackIntentPickUri =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if (result.resultCode === RESULT_OK){
                if(result.data != null){
                    if(result.data!!.data != null){
                        val uri:Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        "Teléfono: ${telefono}"
                    }
                    //Lógica del negocio
                    val uri = result.data?.data
                    val intentConRespuesta = Intent()
                    intentConRespuesta.putExtra("uri",uri.toString())
                    setResult(
                        RESULT_OK,
                        intentConRespuesta
                    )
                    finish()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Base de datos SQLite
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

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

        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonCrudSqlite = findViewById<Button>(
            R.id.btn_ir_crud_sqlite
        )
        botonCrudSqlite.setOnClickListener {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRecycleView = findViewById<Button>(
            R.id.btn_ir_recycle_view
        )
        botonRecycleView.setOnClickListener {
            irActividad(FRecyclerView::class.java)
        }

        val botonGoogleMaps = findViewById<Button>(
            R.id.btn_ir_google_maps
        )
        botonGoogleMaps.setOnClickListener {
            irActividad(GGoogleMaps::class.java)
        }

        val botonUiAut = findViewById<Button>(
            R.id.btn_intent_firebase_ui
        )
        botonUiAut.setOnClickListener {
            irActividad(HFirebaseUIAuth::class.java)
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