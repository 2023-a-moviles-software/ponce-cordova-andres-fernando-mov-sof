package com.andresponce.examen.dao.memoria

class IdGenerador {
    companion object {
        private var id: Int = 0

        fun generarId(): Int {
            return ++id
        }
    }
}