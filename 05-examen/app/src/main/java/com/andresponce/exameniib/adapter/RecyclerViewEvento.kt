package com.andresponce.exameniib.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.exameniib.R
import com.andresponce.exameniib.activity.EventosActivity
import com.andresponce.exameniib.configuracion.ConstantesPrecision
import com.andresponce.exameniib.configuracion.EstandarTiempo
import com.andresponce.exameniib.modelo.Evento
import java.math.RoundingMode

class RecyclerViewEvento(
    private val contexto: EventosActivity,
    var lista: List<Evento>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerViewEvento.MyViewHolder>() {
    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view),
        View.OnCreateContextMenuListener {
        val nombreTextView: TextView
        val descripcionTextView: TextView
        val fechaTextView: TextView
        val precioTextView: TextView
        val horaInicioTextView: TextView
        val horaFinTextView: TextView
        val masOpcionesImageButton: ImageButton

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre_evento)
            descripcionTextView = view.findViewById(R.id.tv_descripcion_evento)
            fechaTextView = view.findViewById(R.id.tv_fecha)
            precioTextView = view.findViewById(R.id.tv_precio)
            horaInicioTextView = view.findViewById(R.id.tv_hora_inicio)
            horaFinTextView = view.findViewById(R.id.tv_hora_fin)
            masOpcionesImageButton = view.findViewById(R.id.ib_mas_eventos)

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
                R.layout.recycler_evento,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val eventoEncontrado = lista[position]
        holder.nombreTextView.text = eventoEncontrado.nombre
        holder.descripcionTextView.text = eventoEncontrado.descripcion
        holder.fechaTextView.text = eventoEncontrado.fecha.format(EstandarTiempo.FORMATO_FECHA)
        holder.precioTextView.text = "$ ${
            eventoEncontrado.precioDeEntrada.setScale(
                ConstantesPrecision.PRECISION_DINERO,
                RoundingMode.CEILING
            )
        }"
        holder.horaInicioTextView.text =
            eventoEncontrado.horaInicio.format(EstandarTiempo.FORMATO_HORA)
        holder.horaFinTextView.text = eventoEncontrado.horaFin.format(EstandarTiempo.FORMATO_HORA)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}