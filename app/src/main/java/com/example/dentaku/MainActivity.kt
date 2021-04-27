package com.example.dentaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    private var prevOperation: String = ""
    private var prevNum: String = ""
    private var calcResult: Int = 0
    private var isNan: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun resetPrevParameter(){
        prevNum = ""
        prevOperation = ""
    }

    private fun calcPrevValue(){
        if (prevNum == ""){
            return
        }

        //最初の数字の場合
        if (prevOperation == ""){
            calcResult = prevNum.toInt()
        }else if (prevOperation == "+"){
            calcResult += prevNum.toInt()
        }else if (prevOperation == "-"){
            calcResult -= prevNum.toInt()
        }else if (prevOperation == "×"){
            calcResult *= prevNum.toInt()
        }else if (prevOperation == "÷"){
            if (prevNum.toInt() == 0){
                //表記をnullにする
                val mainText: TextView = findViewById(R.id.textView)
                mainText.text = "NaN"
                isNan = true
            }else {
                calcResult /= prevNum.toInt()
            }
        }
    }

    fun numberButtonClick(view: View){
        if (isNan) return

        val numButton: String = view.tag.toString()

        prevNum += numButton

        val mainText: TextView = findViewById(R.id.textView)
        mainText.text = mainText.text.toString() + numButton
    }

    fun clearButtonClick(view: View){
        resetPrevParameter()
        calcResult = 0
        isNan = false

        val mainText: TextView = findViewById(R.id.textView)
        mainText.text = ""
    }

    fun resultButtonClick(view: View){

        if (isNan) return

        calcPrevValue()

        //結果表示
        if (!isNan) {
            val mainText: TextView = findViewById(R.id.textView)
            mainText.text = calcResult.toString()
        }
        resetPrevParameter()
    }

    fun operationButtonClick(view: View){

        if (isNan) return

        val operation: String = view.tag.toString()

        val mainText: TextView = findViewById(R.id.textView)
        mainText.text = mainText.text.toString() + operation

        calcPrevValue()

        prevOperation = operation
        prevNum = ""


    }
}