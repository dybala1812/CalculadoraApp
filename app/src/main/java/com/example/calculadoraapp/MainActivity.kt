package com.example.calculadoraapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var verResultado: TextView
    private var numeroActual = ""
    private var operador = ""
    private var primerNumero: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verResultado = findViewById(R.id.verResultado)
    }

    fun onNumeroClick(view: android.view.View) {
        val boton = view as Button
        numeroActual += boton.text
        verResultado.text = numeroActual
    }

    fun onOperadorClick(view: android.view.View) {
        val boton = view as Button
        if (numeroActual.isNotEmpty()) {
            primerNumero = numeroActual.toInt()
            operador = boton.text.toString()
            numeroActual = ""
        } else {
            verResultado.text = "Error"
        }
    }

    fun onIgualClick(view: android.view.View) {
        if (numeroActual.isNotEmpty() && primerNumero != null) {
            val segundoNumero = numeroActual.toInt()
            val resultado: Double = when (operador) {
                "+" -> primerNumero!! + segundoNumero.toDouble()
                "-" -> primerNumero!! - segundoNumero.toDouble()
                "*" -> primerNumero!! * segundoNumero.toDouble()
                "/" -> {
                    if (segundoNumero == 0) {
                        verResultado.text = "Error: ÷0"
                        return
                    } else {
                        primerNumero!!.toDouble() / segundoNumero.toDouble()
                    }
                }
                else -> 0.0
            }

            verResultado.text = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            numeroActual = verResultado.text.toString()
            primerNumero = null
            operador = ""
        } else {
            verResultado.text = "Error"
        }
    }

    fun onBorrarTodoClick(view: android.view.View) {
        numeroActual = ""
        operador = ""
        primerNumero = null
        verResultado.text = "0"
    }

    fun onBorrarDigitoClick(view: android.view.View) {
        if (numeroActual.isNotEmpty()) {
            numeroActual = numeroActual.dropLast(1)
            verResultado.text = if (numeroActual.isEmpty()) "0" else numeroActual
        }
    }
}



