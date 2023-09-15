package com.andresponce.exameniib.dao.firebase

import androidx.appcompat.app.AppCompatActivity
import com.andresponce.exameniib.activity.RecyclerViewCallback
import com.andresponce.exameniib.dao.DaoFactory
import com.andresponce.exameniib.dao.EventoDao
import com.andresponce.exameniib.dao.LugarDao

class FirebaseDaoFactory(private val context: RecyclerViewCallback): DaoFactory() {
    override fun getEventoDao(): EventoDao {
        return FirebaseEventoDao(context)
    }

    override fun getLugarDao(): LugarDao {
        return FirebaseLugarDao(context)
    }
}