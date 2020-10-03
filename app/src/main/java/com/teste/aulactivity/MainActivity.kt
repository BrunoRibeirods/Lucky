package com.teste.aulactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    private var aleatorio: Int = 0
    private var contador: Int = 2

    private val respostas = mutableListOf<String>(
        "Nossa você tem muita sorte",
        "Você tem sorte :)",
        "essa foi por pouco!",
        "Não foi dessa vez, mas não desista"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        painel.text = "Adivinhe um numero de 1 a 10"
        btn_palpite.setOnClickListener{
            if(et_palpite.text.isEmpty()){
                et_palpite.setText("0")
            }
            verificar(et_palpite.text.toString().toInt())
        }

        btn_restart.setOnClickListener{restart()}

    }

    override fun onStart() {
        super.onStart()
        aleatorio = (1..10).random()
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Numero: $aleatorio")
        toaste("O jogo irá começar!")

    }
    private fun restart(){
        onStart()
        onResume()
        btn_palpite.visibility = View.VISIBLE
        et_palpite.visibility = View.VISIBLE
        painel.text = "Adivinhe um numero de 1 a 10"
        btn_restart.visibility = View.INVISIBLE
        contador = 2
    }

    private fun toaste(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

     fun verificar(valortext: Int){
        if(valortext == aleatorio){
            when(contador) {
                2 -> {
                    painel.text = "Parabéns!!"
                    toaste(respostas[0])
                    fimDeJogo()
                }
                1 -> {
                    painel.text = "Parabéns!!"
                    toaste(respostas[1])
                    fimDeJogo()
                }
                0 -> {
                    painel.text = "Parabéns!!"
                    toaste(respostas[2])
                    fimDeJogo()
                }
            }
        }else if(contador == 0){
            painel.text = "Fim de jogo!"
            toaste(respostas[3])
            fimDeJogo()

        }else{
            contador--
            painel.text = "Tentativas restantes: ${contador + 1}"
            toaste("Errou!")
        }
    }

    fun fimDeJogo(){
        et_palpite.visibility = View.INVISIBLE
        btn_palpite.visibility = View.INVISIBLE
        btn_restart.visibility = View.VISIBLE
    }
}