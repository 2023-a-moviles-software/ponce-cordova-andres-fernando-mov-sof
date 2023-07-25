package com.example.movilessoftware2023a

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class GGoogleMaps : AppCompatActivity() {
    var permisos = false;
    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        this.solicitarPermisos()
    }

    private fun solicitarPermisos() {
        val contexto = this.applicationContext;
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION;
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION;
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            nombrePermisoFine
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            this.permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    nombrePermisoFine,
                    nombrePermisoCoarse
                ),
                1
            )
        }
    }

    private fun iniciarLogicaMapa() {
        val fragmentoMapa =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
            }
        }
    }

    private fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext;
        with(this.mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
                mapa.uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun agregarMarcador(latLng: LatLng, title: String): Marker {
        return this.mapa.addMarker(
            MarkerOptions().position(latLng).title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        this.mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }
}