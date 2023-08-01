package com.andresponce.deber02.models

data class Task(
    val name: String,
    val category: String,
    val subtasks: ArrayList<Subtask>
)
