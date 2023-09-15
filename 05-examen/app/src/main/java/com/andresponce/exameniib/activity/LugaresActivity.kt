package com.andresponce.exameniib.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.exameniib.R
import com.andresponce.exameniib.adapter.RecyclerViewLugar
import com.andresponce.exameniib.dao.DaoFactory
import com.andresponce.exameniib.modelo.Lugar
import com.andresponce.exameniib.util.Claves
import com.andresponce.exameniib.util.NavegarActividades
import java.io.Serializable

class LugaresActivity : AppCompatActivity(), RecyclerViewCallback {
    var idItemSeleccionado = ""
    var indiceItemSeleccionado = 0
    private lateinit var adaptador: RecyclerViewLugar
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.data != null) {
            val mensaje = it.data?.getStringExtra(Claves.MENSAJE_RESULTADO)
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)

        val botonCrearLugar = findViewById<Button>(R.id.btn_nuevo_lugar)
        botonCrearLugar.setOnClickListener {
            NavegarActividades.irActividadPorResultados(
                this,
                CrearLugarActivity::class.java,
                launcher
            )
        }
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_lugares)
        adaptador = RecyclerViewLugar(
            this,
            DaoFactory.daoFactory(this).getLugarDao().obtenerTodos(),
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        registerForContextMenu(recyclerView)
        actualizarRecyclerView()
    }

    private fun actualizarRecyclerView() {
        adaptador.lista = DaoFactory.daoFactory(this).getLugarDao().obtenerTodos()
        adaptador.notifyDataSetChanged()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                val claves = mapOf<String, String>(
                    Claves.ID_LUGAR to idItemSeleccionado
                )
                NavegarActividades.irActividadPorResultados(
                    this,
                    EditarLugarActivity::class.java,
                    launcher,
                    claves
                )
                true
            }

            R.id.mi_borrar -> {
                val escogido = adaptador.lista[indiceItemSeleccionado]
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.titulo_eliminar) + escogido.nombre + getString(R.string.fin_pregunta))
                    .setMessage(getString(R.string.mensaje_eliminar))
                    .setPositiveButton(getString(R.string.si)) { _, _ ->
                        DaoFactory.daoFactory(this).getLugarDao().eliminar(idItemSeleccionado)
                        Toast.makeText(
                            this,
                            escogido.nombre + " " + getString(R.string.eliminar_exito),
                            Toast.LENGTH_LONG
                        ).show()
                        actualizarRecyclerView()
                    }
                    .setNegativeButton(getString(R.string.no), null)
                    .show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarRecyclerView()
    }

    override fun onRestart() {
        super.onRestart()
        actualizarRecyclerView()
    }

    override fun alRecibirDatos() {
        adaptador.notifyDataSetChanged()
    }

    override fun setData(data: List<Any>) {
        adaptador.lista = data as List<Lugar>
    }

    override fun setParent(data: Any) {}
}