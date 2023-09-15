package com.andresponce.exameniib.dao

import com.andresponce.exameniib.activity.RecyclerViewCallback
import com.andresponce.exameniib.dao.firebase.FirebaseDaoFactory

abstract class DaoFactory {
    abstract fun getEventoDao(): EventoDao
    abstract fun getLugarDao(): LugarDao

    companion object {
        fun daoFactory(context: RecyclerViewCallback): DaoFactory = FirebaseDaoFactory(context)
    }
}