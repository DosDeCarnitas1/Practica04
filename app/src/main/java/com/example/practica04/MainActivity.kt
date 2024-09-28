package com.example.practica04

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var intent: Intent
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beca = Beca()

        //Instancia para generar un hilo para lanzar Activity de Ingreso o Menú
        Timer().schedule(object : TimerTask() {
            override fun run() {
                intent = if (nuevoUsuario()) {
                    // Inicializa el intent para MenuActivity y agrega extras
                    Intent(applicationContext, MenuActivity::class.java).apply {
                        putExtra("folio", beca.folio)
                        putExtra("institucion", beca.institucion)
                        putExtra("nombre", beca.nombre)
                        putExtra("apellido", beca.apellido)
                        putExtra("nivel", beca.nivel)
                    }
                } else {
                    // Inicializa el intent para IngresoActivity
                    Intent(applicationContext, IngresoActivity::class.java)
                }
                startActivity(intent)
            }
        }, 2000)
    }

    fun nuevoUsuario(): Boolean{
        //Acceso a las preferencias
        val preferences: SharedPreferences =
            getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        return preferences.getBoolean("guardado", false)
    }
}