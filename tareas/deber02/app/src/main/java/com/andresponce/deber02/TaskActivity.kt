package com.andresponce.deber02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.deber02.adapters.SubtaskRecyclerView
import com.andresponce.deber02.adapters.TaskRecyclerView
import com.andresponce.deber02.data.Database
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskActivity : AppCompatActivity() {
    private lateinit var adaptador: SubtaskRecyclerView
    var taskIndex: Int = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        this.taskIndex = intent.getIntExtra("index", -1)

        val categoryTextView: TextView = findViewById(R.id.tv_task_activity_category)
        categoryTextView.text = Database.tasks[taskIndex].category

        val nameTextView: TextView = findViewById(R.id.tv_task_activity_name)
        nameTextView.text = Database.tasks[taskIndex].name

        val dateTextView: TextView = findViewById(R.id.tv_task_activity_date)
        dateTextView.text = LocalDate.now().format(DateTimeFormatter.ISO_DATE)


        val addSubtaskLinearLayout: LinearLayout = findViewById<LinearLayout>(R.id.ll_add_subtask)
        addSubtaskLinearLayout.setOnClickListener {
            Database.addSubtaskToTaskAtIndex(taskIndex);
            this.updateRecyclerView()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_subtasks)
        adaptador = SubtaskRecyclerView(
            this,
            Database.tasks[taskIndex].subtasks,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        registerForContextMenu(recyclerView)
        updateRecyclerView()
    }

    fun updateRecyclerView() {
        adaptador.list = Database.tasks[taskIndex].subtasks
        adaptador.notifyDataSetChanged()
    }
}