package com.andresponce.deber02.data

import com.andresponce.deber02.models.Subtask
import com.andresponce.deber02.models.Task

class Database {
    companion object {
        val tasks = arrayListOf(
            Task(
                name = "Crear informe de ventas",
                category = "Administración",
                subtasks = arrayListOf<Subtask>(
                    Subtask("Investigar datos de ventas"),
                    Subtask("Analizar tendencias de ventas"),
                    Subtask("Preparar gráficos y visualizaciones"),
                    Subtask("Redactar conclusiones del informe")
                )
            ),
            Task(
                name = "Desarrollar aplicación móvil",
                category = "Desarrollo de software",
                subtasks = arrayListOf<Subtask>(
                    Subtask("Diseñar interfaz de usuario"),
                    Subtask("Implementar lógica de negocio"),
                    Subtask("Realizar pruebas de funcionalidad"),
                    Subtask("Optimizar rendimiento"),
                    Subtask("Preparar lanzamiento")
                )
            ),
            Task(
                name = "Organizar evento de lanzamiento",
                category = "Eventos",
                subtasks = arrayListOf<Subtask>(
                    Subtask("Reservar lugar del evento"),
                    Subtask("Coordinar catering y bebidas"),
                    Subtask("Diseñar materiales promocionales"),
                    Subtask("Enviar invitaciones a medios y asistentes"),
                    Subtask("Preparar programa del evento")
                )
            )
        )

        fun addTask() {
            tasks.add(TaskGenerator.generateRandomTasks())
        }

        fun deleteTask(task: Task) {
            tasks.remove(task)
        }

        fun addSubtaskToTaskAtIndex(index: Int) {
            tasks[index].subtasks.add(TaskGenerator.generateRandomSubtask())
        }

        fun deleteSubtaskFromTaskAtIndex(index: Int, subtask: Subtask) {
            tasks[index].subtasks.remove(subtask)
        }
    }
}