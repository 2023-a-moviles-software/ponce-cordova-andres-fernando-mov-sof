package com.andresponce.exameniib.activity

interface RecyclerViewCallback {
    fun alRecibirDatos()

    fun setData(data: List<Any>)

    fun setParent(data: Any)
}