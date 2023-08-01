package com.andresponce.deber02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.deber02.adapters.TaskRecyclerView
import com.andresponce.deber02.data.Database
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var adaptador: TaskRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTaskLinearLayout: LinearLayout = findViewById<LinearLayout>(R.id.ll_add_task)
        addTaskLinearLayout.setOnClickListener {
            Database.addTask();
            this.updateRecyclerView()
        }

        val tituloTextView: TextView = findViewById(R.id.tv_titulo)
        tituloTextView.text = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_tasks)
        adaptador = TaskRecyclerView(
            this,
            Database.tasks,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        registerForContextMenu(recyclerView)
        updateRecyclerView()
    }

    fun updateRecyclerView() {
        adaptador.list = Database.tasks
        adaptador.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }

    override fun onRestart() {
        super.onRestart()
        updateRecyclerView()
    }
}