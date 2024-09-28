package com.example.practica04

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoActivity : AppCompatActivity() {

    //Instancias
    private lateinit var correo: EditText
    private lateinit var contraseña: EditText
    private lateinit var guardar: Switch
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso_activity)

        correo = findViewById(R.id.edtCorreo)
        contraseña = findViewById(R.id.edtContraseña)
        guardar = findViewById(R.id.swtGuardar)

        beca = Beca()
    }

    fun onClick(view: View?){
        when(view?.id){
            R.id.btnIngresar -> ingresar()
            R.id.btnBorrar -> limpiar()
        }
    }

    private fun ingresar(){
        if (correo.text.isNotBlank() && correo.text.isNotEmpty() &&
            contraseña.text.isNotBlank() && contraseña.text.isNotEmpty()){
            val usr =
                Usuario(correo.text.toString(), contraseña.text.toString(), true)
            if (guardar.isChecked){
                guardarPreferencias(usr)
            }
            val intent = Intent(applicationContext, MenuActivity::class.java)
            intent.putExtra("folio", beca.folio)
            intent.putExtra("institucion", beca.institucion)
            intent.putExtra("nombre", beca.nombre)
            intent.putExtra("apellido", beca.apellido)
            intent.putExtra("nivel", beca.nivel)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Capturar información", Toast.LENGTH_LONG).show()
        }
    }

    private fun limpiar() {
        correo.text = null
        contraseña.text = null
        correo.requestFocus()
    }

    private fun guardarPreferencias(user: Usuario) {
        val preferences: SharedPreferences =
            getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("email", user.correo)
        editor.putString("password", user.contraseña)
        editor.putBoolean("guardado", user.guardado)
        editor.apply()
    }
}