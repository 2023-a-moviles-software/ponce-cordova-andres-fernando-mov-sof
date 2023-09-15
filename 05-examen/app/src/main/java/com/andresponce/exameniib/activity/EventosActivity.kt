package com.andresponce.exameniib.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.exameniib.R
import com.andresponce.exameniib.adapter.RecyclerViewEvento
import com.andresponce.exameniib.dao.DaoFactory
import com.andresponce.exameniib.modelo.Evento
import com.andresponce.exameniib.modelo.Lugar
import com.andresponce.exameniib.util.Claves
import com.andresponce.exameniib.util.NavegarActividades
import java.io.Serializable

class EventosActivity : AppCompatActivity(), RecyclerViewCallback {
    var idItemSeleccionado = ""
    var indiceItemSeleccionado = 0
    private lateinit var adaptador: RecyclerViewEvento
    //private var lugar: Lugar? = null
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.data != null) {
            val mensaje = it.data?.getStringExtra(Claves.MENSAJE_RESULTADO)
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }
    private var idLugarSeleccionado: String? = null
    private var nombreLugar: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)

        findViewById<ImageButton>(R.id.btn_atras).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        this.idLugarSeleccionado = intent.getStringExtra(Claves.ID_LUGAR)
        this.nombreLugar = intent.getStringExtra(Claves.NOMBRE_LUGAR)

        if (idLugarSeleccionado == null) {
            finish()
            return
        }

        val tituloTextView = findViewById<TextView>(R.id.tv_titulo_lugar)
        val eventoEnTextView = findViewById<TextView>(R.id.tv_eventos_en_lugar)
        tituloTextView.text = nombreLugar!!
        eventoEnTextView.text = getString(R.string.eventos_en, nombreLugar!!)

        val botonCrearEvento = findViewById<Button>(R.id.btn_nuevo_evento)
        botonCrearEvento.setOnClickListener {
            val mapaValores = mapOf<String, String>(
                Claves.ID_LUGAR to idLugarSeleccionado!!
            )
            NavegarActividades.irActividadPorResultados(
                this,
                CrearEventoActivity::class.java,
                launcher,
                mapaValores
            )
        }

        iniciarRecyclerView()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                val claves = mapOf<String, String>(
                    Claves.ID_LUGAR to idLugarSeleccionado!!,
                    Claves.ID_EVENTO to idItemSeleccionado,
                )
                NavegarActividades.irActividadPorResultados(
                    this,
                    EditarEventoActivity::class.java,
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
                        DaoFactory.daoFactory(this).getEventoDao().eliminar(idItemSeleccionado)
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

    private fun iniciarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_eventos)
        adaptador = RecyclerViewEvento(
            this,
            DaoFactory.daoFactory(this).getEventoDao().encontrarPorIdDeLugar(this.idLugarSeleccionado!!),
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        registerForContextMenu(recyclerView)
        actualizarRecyclerView()
    }

    private fun actualizarRecyclerView() {
        adaptador.lista = DaoFactory.daoFactory(this).getEventoDao().encontrarPorIdDeLugar(this.idLugarSeleccionado!!)
        adaptador.notifyDataSetChanged()
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
        adaptador.lista = data as List<Evento>
    }

    override fun setParent(data: Any) {
        this.idLugarSeleccionado = (data as Lugar).id
    }
}