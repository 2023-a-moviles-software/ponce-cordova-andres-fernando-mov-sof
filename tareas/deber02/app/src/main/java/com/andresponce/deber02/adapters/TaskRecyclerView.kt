package com.andresponce.deber02.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.deber02.MainActivity
import com.andresponce.deber02.R
import com.andresponce.deber02.TaskActivity
import com.andresponce.deber02.data.Database
import com.andresponce.deber02.models.Task
import com.andresponce.deber02.util.IntentRouter

class TaskRecyclerView(
    private val context: MainActivity,
    var list: ArrayList<Task>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<TaskRecyclerView.MyViewHolder>() {

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val completedImageView: ImageView;
        val taskNameTextView: TextView;
        val completedSubtasksTextView: TextView;
        val categoryTextView: TextView;
        val taskLinearLayout: LinearLayout;

        init {
            completedImageView = view.findViewById(R.id.im_completed_task)
            taskNameTextView = view.findViewById(R.id.tv_task_name)
            completedSubtasksTextView = view.findViewById(R.id.completed_subtasks)
            categoryTextView = view.findViewById(R.id.tv_category)
            taskLinearLayout = view.findViewById(R.id.ll_task)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskRecyclerView.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.task_layout,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskRecyclerView.MyViewHolder, position: Int) {
        holder.taskNameTextView.text = list[position].name
        holder.categoryTextView.text = list[position].category
        holder.completedSubtasksTextView.text = "0/" + this.list[position].subtasks.size
        holder.completedImageView.setOnClickListener {
            Database.deleteTask(list[position])
            this.context.updateRecyclerView()
        }
        holder.taskLinearLayout.setOnClickListener {
            val valuesMap = mapOf<String, Int>(
                "index" to position,
            )
            IntentRouter.goToActivity(context, TaskActivity::class.java, valuesMap)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size;
    }
}