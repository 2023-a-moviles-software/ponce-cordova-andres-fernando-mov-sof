package com.andresponce.deber02.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andresponce.deber02.R
import com.andresponce.deber02.TaskActivity
import com.andresponce.deber02.data.Database
import com.andresponce.deber02.models.Subtask

class SubtaskRecyclerView(
    private val context: TaskActivity,
    var list: ArrayList<Subtask>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<SubtaskRecyclerView.MyViewHolder>() {
    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val subtaskImageView: ImageView
        val subtaskNameTextView: TextView

        init {
            subtaskImageView = view.findViewById(R.id.im_completed_subtask)
            subtaskNameTextView = view.findViewById(R.id.tv_subtask_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.subtask_layout,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.subtaskNameTextView.text = list[position].name
        holder.subtaskImageView.setOnClickListener {
            Database.deleteSubtaskFromTaskAtIndex(context.taskIndex, list[position])
            this.context.updateRecyclerView()
        }
    }
}