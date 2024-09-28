package com.example.practica04

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConsultaActivity : AppCompatActivity() {

    private lateinit var info: TextView
    private var beca: Beca = Beca()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consulta_activity)

        info = findViewById(R.id.txtInfo)

        val infoRecibida = intent.extras
        beca.folio = infoRecibida?.getInt("folio")!!
        beca.institucion = infoRecibida?.getString("institucion")!!
        beca.nombre = infoRecibida?.getString("nombre")!!
        beca.apellido = infoRecibida?.getString("apellido")!!
        beca.nivel = infoRecibida?.getString("nivel")!!

        info.text = "Información registrada:\n" +
                "Folio: ${beca.folio}\n" +
                "Institución: ${beca.institucion}\n" +
                "Nombre: ${beca.nombre}\n" +
                "Apellidos: ${beca.apellido}\n" +
                "${beca.nivel}"
    }

}