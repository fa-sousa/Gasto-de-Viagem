package com.fatimasousa.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(this)
    }

    //metodo importado da interface (View.OnClickListener
    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (validationOk()) {

            try {
                val distance = editDistance.text.toString().toFloat()
                val price = editPrice.text.toString().toFloat()
                val autonomy = editAutonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy
                textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
            } catch (nfe: NumberFormatException) {
                Toast.makeText(this, getString(R.string.informe_valores_validos), Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            //cria uma notificação para o usuario
            //contexto é a alma da aplicacao android, ou seja, praticamente a aplicacao em forma de variavel
            //this(no caso de activity, this é o proprio contexto) ou aplication context
            Toast.makeText(this, getString(R.string.preencha_todos_os_campos), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun validationOk(): Boolean {
        return (editDistance.text.toString() != ""
                && editAutonomy.text.toString() != ""
                && editPrice.text.toString() != "")
    }

}