package com.andresponce.examen.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.examen.R
import com.andresponce.examen.activity.EventosActivity
import com.andresponce.examen.activity.LugaresActivity
import com.andresponce.examen.modelo.Lugar
import com.andresponce.examen.util.Claves
import com.andresponce.examen.util.NavegarActividades

class RecyclerViewLugar(
    private val contexto: LugaresActivity,
    var lista: List<Lugar>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerViewLugar.MyViewHolder>() {
    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view),
        View.OnCreateContextMenuListener {
        val nombreTextView: TextView
        val direccionTextView: TextView
        val latitudTextView: TextView
        val longitudTextView: TextView
        val capacidadTextView: TextView
        val tieneEstacionamientoTextView: TextView
        val verEventosButton: Button
        val masOpcionesImageButton: ImageButton

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre_evento)
            direccionTextView = view.findViewById(R.id.tv_descripcion_evento)
            latitudTextView = view.findViewById(R.id.tv_latitud)
            longitudTextView = view.findViewById(R.id.tv_longitud)
            capacidadTextView = view.findViewById(R.id.tv_fecha)
            tieneEstacionamientoTextView = view.findViewById(R.id.tv_precio)
            verEventosButton = view.findViewById(R.id.btn_ver_eventos)
            masOpcionesImageButton = view.findViewById(R.id.ib_mas)

            view.setOnCreateContextMenuListener(this)

            masOpcionesImageButton.setOnClickListener {
                view.showContextMenu(it.x, it.y)
            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val inflater = contexto.menuInflater
            inflater.inflate(R.menu.ud_menu, menu)
            contexto.idItemSeleccionado = lista[adapterPosition].id
            contexto.indiceItemSeleccionado = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_lugar,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lugarActual = lista[position]
        holder.nombreTextView.text = lugarActual.nombre
        holder.direccionTextView.text = lugarActual.direccion
        holder.latitudTextView.text = lugarActual.latitud.toPlainString()
        holder.longitudTextView.text = lugarActual.longitud.toPlainString()
        holder.capacidadTextView.text = lugarActual.capacidad.toString()
        holder.tieneEstacionamientoTextView.text =
            if (lugarActual.tieneEstacionamiento)
                contexto.getString(R.string.si)
            else
                contexto.getString(R.string.no)
        holder.verEventosButton.setOnClickListener {
            val mapaValores = mapOf<String, Int>(
                Claves.ID_LUGAR to lugarActual.id,
            )
            NavegarActividades.irActividad(contexto, EventosActivity::class.java, mapaValores)
        }
    }
}