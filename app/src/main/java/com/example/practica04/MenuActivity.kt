package com.example.practica04

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)

        beca = Beca()

        var infoRecibida = intent.extras
        if (infoRecibida != null) {
            beca.folio = infoRecibida?.getInt("folio")!!
            beca.institucion = infoRecibida?.getString("institucion")!!
            beca.nombre = infoRecibida?.getString("nombre")!!
            beca.apellido = infoRecibida?.getString("apellido")!!
            beca.nivel = infoRecibida?.getString("nivel")!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        //return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when (item.itemId){
            R.id.itmFormulario -> {
                intent = Intent(applicationContext, RegistroActivity::class.java)
                startActivity(intent)
            }
            R.id.itmConsultar -> {
                intent = Intent(applicationContext, ConsultaActivity::class.java)
                intent.putExtra("folio", beca.folio)
                intent.putExtra("institucion", beca.institucion)
                intent.putExtra("nombre", beca.nombre)
                intent.putExtra("apellido", beca.apellido)
                intent.putExtra("nivel", beca.nivel)
                startActivity(intent)
            }
            R.id.itmCerrar -> { cerrarSesion() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.clear()
        editor.apply()

        val intent = Intent (applicationContext, IngresoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP; Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}