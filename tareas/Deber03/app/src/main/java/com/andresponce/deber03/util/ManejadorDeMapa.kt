package com.andresponce.deber03.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.AnnotationConfig
import com.mapbox.maps.plugin.annotation.AnnotationPlugin
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.andresponce.deber03.R
import com.andresponce.deber03.configuracion.ConstantesPrecision
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import java.math.BigDecimal
import java.math.RoundingMode

class ManejadorDeMapa (
    private val context: AppCompatActivity,
    private val mapView: MapView,
    private val textView: TextView
) {

    private var pluginAnotaciones: AnnotationPlugin? = null
    private lateinit var configuracionAnotaciones: AnnotationConfig
    private val etiquetaAnotaciones = "map_annotation"
    private var manejadorAnotacionesPuntos: PointAnnotationManager? = null

    fun mostrarUbicacionInicial(latitud: BigDecimal, longitud: BigDecimal) {
        mapView.getMapboxMap().loadStyleUri(
            Style.MAPBOX_STREETS,
            object : Style.OnStyleLoaded {
                val point = Point.fromLngLat(
                    longitud.toDouble(),
                    latitud.toDouble(),
                )

                override fun onStyleLoaded(style: Style) {
                    mapView.getMapboxMap().setCamera(
                        CameraOptions.Builder().center(
                            point
                        ).zoom(12.0).build()
                    )

                    //Marca
                    pluginAnotaciones = mapView.annotations
                    configuracionAnotaciones = AnnotationConfig(
                        etiquetaAnotaciones
                    )
                    manejadorAnotacionesPuntos =
                        pluginAnotaciones?.createPointAnnotationManager(configuracionAnotaciones)
                    crearMarcador(point)
                    textView.text = latLogATexto(latitud, longitud)
                }
            }
        )

        mapView.getMapboxMap().addOnMapClickListener { point ->
            val latitudNueva = point.latitude()
            val longitudNueva = point.longitude()

            crearMarcador(Point.fromLngLat(longitudNueva, latitudNueva))
            textView.text = latLogATexto(
                BigDecimal(point.latitude()).setScale(
                    ConstantesPrecision.PRECISION_COORDENADAS,
                    RoundingMode.CEILING
                ),
                BigDecimal(point.longitude()).setScale(
                    ConstantesPrecision.PRECISION_COORDENADAS,
                    RoundingMode.CEILING
                )
            )
            true
        }
    }

    fun crearMarcador(point: Point) {
        manejadorAnotacionesPuntos?.deleteAll()

        val marca = convertirDrawableABitmap(
            context.getDrawable(R.drawable.red_marker)
        )?.let {
            PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(it)
        }
        manejadorAnotacionesPuntos?.create(marca!!)
    }

    private fun convertirDrawableABitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    private fun latLogATexto(latitud: BigDecimal, longitud: BigDecimal): String {
        return "${latitud.toPlainString()}, ${longitud.toPlainString()}"
    }
}