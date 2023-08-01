package com.andresponce.deber02.data

import com.andresponce.deber02.models.Subtask
import com.andresponce.deber02.models.Task
import kotlin.random.Random

class TaskGenerator {
    companion object {
        private fun generateRandomTaskName(): String {
            val prefixes = listOf(
                "Crear",
                "Desarrollar",
                "Investigar",
                "Organizar",
                "Revisar",
                "Escribir",
                "Implementar"
            )
            val suffixes = listOf(
                "informe",
                "aplicación",
                "evento",
                "proyecto",
                "presentación",
                "documento",
                "sistema"
            )
            return "${prefixes.random()} ${suffixes.random()}"
        }

        private fun generateRandomCategory(): String {
            val categories = listOf(
                "Administración", "Desarrollo de software", "Eventos", "Marketing", "Investigación",
                "Recursos Humanos", "Educación", "Ventas", "Finanzas"
            )
            return categories.random()
        }

        fun generateRandomSubtask(): Subtask {
            val subtaskNames = listOf(
                "Diseñar interfaz de usuario",
                "Implementar lógica de negocio",
                "Realizar pruebas de funcionalidad",
                "Optimizar rendimiento",
                "Preparar gráficos y visualizaciones",
                "Redactar conclusiones",
                "Reservar lugar del evento",
                "Coordinar catering y bebidas",
                "Enviar invitaciones a medios y asistentes",
                "Investigar datos relevantes",
                "Analizar tendencias y estadísticas",
                "Preparar material promocional",
                "Definir estrategia de marketing",
                "Analizar la competencia",
                "Gestionar recursos",
                "Entrenar al personal"
            )
            return Subtask(subtaskNames.random())
        }

        fun generateRandomTasks(): Task {
            val name = generateRandomTaskName()
            val category = generateRandomCategory()
            val numSubtasks = Random.nextInt(1, 6)
            val subtasks: ArrayList<Subtask> = arrayListOf()
            for (i in 0 until numSubtasks) {
                subtasks.add(generateRandomSubtask())
            }
            return Task(name, category, subtasks)
        }
    }
}
