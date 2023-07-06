package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FRecyclerView : AppCompatActivity() {

    var totalNumeroLikes = 0
    val arreglo = BBaseDatosMemoria.arregloBEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frecycler_view)
        //Recycler View
        inicializarRecyclerView()
    }

    fun inicializarRecyclerView(){
        //Encontrar Recycler View
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )
        val adaptador = FReciclerViewAdaptadorNombreCedula(
            this,
            arreglo,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalNumeroLikes++
        val totalLikesTextView = findViewById<TextView>(
            R.id.tv_total_likes
        )
        totalLikesTextView.text = totalNumeroLikes.toString()
    }
}