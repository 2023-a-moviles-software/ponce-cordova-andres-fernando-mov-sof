package com.example.movilessoftware2023a

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GGoogleMaps : AppCompatActivity() {
    var permisos = false;
    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        this.solicitarPermisos()
        this.iniciarLogicaMapa()
        val boton = findViewById<Button>(R.id.btn_ir_carolina).setOnClickListener {
            this.irCarolina()
        }
    }

    private fun irCarolina() {
        val carolina = LatLng(
            -0.1825684318486696,
            -78.48447277600916)
        val zoom = 17f
        this.moverCamaraConZoom(carolina, zoom)
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

    fun agregarPoliLinea() {
        with(this.mapa) {
            val poliLineaUno = mapa.addPolyline(
                PolylineOptions().clickable(true).add(
                    LatLng(-0.17591, -78.4850647242),
                    LatLng(-0.17632, -78.482655),
                    LatLng(-0.17591, -78.4850647242)
                )
            )
            poliLineaUno.tag = "Polilinea 1" // Para identificar
        }
    }

    fun agregarPoligono() {
        with(this.mapa) {
            val poligonoUno = mapa.addPolygon(
                PolygonOptions().clickable(true).add(
                    LatLng(-0.1771546902239471, -78.48344981495214),
                    LatLng(-0.17968981486125768, -78.48269198043828),
                    LatLng(-0.17710958124147777, -78.48142892291516)
                )
            )
            poligonoUno.fillColor = -0xc771c4;
            poligonoUno.tag = "Poligono 1" // Para identificar
        }
    }

    fun escucharListeners() {
        this.mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener$it");
            it.tag // ID
        }

        this.mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener$it");
            it.tag // ID
        }

        this.mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener$it");
            it.tag // ID
            return@setOnMarkerClickListener true
        }

        this.mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener");
        }

        this.mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener $it");
        }

        this.mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener");
        }
    }
}