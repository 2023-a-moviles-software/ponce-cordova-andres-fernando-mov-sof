package com.andresponce.deber02.util

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class IntentRouter {
    companion object {
        fun goToActivity(
            context: AppCompatActivity,
            targetClass: Class<*>,
            attributes: Map<String, Serializable>? = null
        ) {
            val intent = Intent(context, targetClass)
            if (attributes != null) {
                for ((key, value) in attributes) {
                    intent.putExtra(key, value)
                }
            }
            context.startActivity(intent)
        }
    }
}