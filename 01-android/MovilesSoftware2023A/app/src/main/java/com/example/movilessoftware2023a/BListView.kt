package com.example.movilessoftware2023a

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class BListView : AppCompatActivity() {

    val listaEntrenadores: ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    var itemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        //Obtener referencia a ListView
        val listView = findViewById<ListView>(
            R.id.lv_list_view
        )
        //Adaptador del arreglo de entrenadores
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaEntrenadores
        )
        //Seteo el adaptador a List View
        listView.adapter = adaptador
        //Se refresca el adaptador con cambios
        adaptador.notifyDataSetChanged()

        //Botón para agregar Entrenador
        val botonAgregarEntrenador = findViewById<Button>(
            R.id.btn_anadir_list_view
        )
        botonAgregarEntrenador.setOnClickListener {
            agregarEntrenador(adaptador)
        }
        //Registrar el ListView para el menú contextual
        registerForContextMenu(listView)
    }

    fun agregarEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        listaEntrenadores.add(
            BEntrenador(
                9,
                "Ash Ketchum",
                "Pueblo Paleta",
            )
        )
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar -> {
                "Hacer algo con: ${itemSeleccionado}"
                return true
            }
            R.id.mi_eliminar -> {
                "Hacer algo con: ${itemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                dialog, which -> //Alguna cosa
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(R.array.opciones_dialogo)
        val seleccionPrevia = booleanArrayOf(
            true,
            false,
            false,
            false,
            false,
            false,
            false
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                dialog, which, isChecked ->
                "Dio clic en el item ${which}"
            }
        )

        val dialogo = builder.create()
        dialogo.show()
    }
}