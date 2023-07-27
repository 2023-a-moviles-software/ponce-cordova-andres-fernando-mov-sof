package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class HFirebaseUIAuth : AppCompatActivity() {
    //Callback del intent del Login
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){res: FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode == RESULT_OK){
            if (res.idpResponse != null){
                //Lógica de negocio
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(res: IdpResponse){
        val btnLogin: Button = findViewById(R.id.btn_login_firebase)
        val btnLogout: Button = findViewById(R.id.btn_logout_firebase)
        btnLogin.visibility = View.GONE
        btnLogout.visibility = View.VISIBLE
        if (res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res);
        }
    }

    private fun registrarUsuarioPorPrimeraVez(res: IdpResponse) {
        //Lógica de negocio
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
    }
}