package br.vitor.calculatorudemy

import android.app.DatePickerDialog
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import java.util.EventListener
import java.util.Properties
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var tvInput: TextView
    private lateinit var tvResult: TextView
    private var lastNumeric :Boolean = false
    private var lastDot :Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tv_input)
        tvResult = findViewById(R.id.tv_result)

    }

    fun onDigit(view: View){
        if (tvInput.text.contains("Hello World!!")){
            tvInput.text=""
        }
        tvResult.append((view as Button).text)
        lastNumeric=true
        lastDot=false
//            val buttonId = view.id
//        when(buttonId){
//            R.id.btnClear-> tvInput.text = ""
//            R.id.btnOne-> tvInput.append("1")
//            R.id.btnTwo-> tvInput.append("2")
//            R.id.btnThree-> tvInput.append("3")
//            R.id.btnFour-> tvInput.append("4")
//            R.id.btnFive-> tvInput.append("5")
//            R.id.btnSix-> tvInput.append("6")
//            R.id.btnSeven-> tvInput.append("7")
//            R.id.btnEight-> tvInput.append("8")
//            R.id.btnNine-> tvInput.append("9")
//            R.id.btnZero-> tvInput.append("0")
//            else -> {
//                tvInput.text = getString(R.string._error)
//            }
//        }
    }

    fun onClear(view: View){
        tvInput.text = ""
        tvResult.text =""
    }
    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvResult.append(".")
            lastNumeric =false
            lastDot=true
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            val tvValue = tvResult.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith(prefix ="-")){
                    prefix="-"
                    tvValue.substring(startIndex = 1)
                }
                calculation(tvValue, prefix)
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun calculation(
        tvValue: String,
        prefix: String
    ) {
        val resultCalcule: Double
        lateinit var splitValue: List<String>
        var oneNumber:String
        var secondNumber:String
        if (tvValue.contains("-")) {
            splitValue = tvValue.split("-")
            oneNumber = splitValue[0]
            secondNumber = splitValue[1]
            resultCalcule = oneNumber.toDouble() - secondNumber.toDouble()
            tvInput.text = removeZeroAfterDot(resultCalcule.toString())

            if (prefix.isNotEmpty()) {
                oneNumber += prefix
            }
        }else if (tvValue.contains("*")) {
            splitValue = tvValue.split("*")
            oneNumber = splitValue[0]
            secondNumber = splitValue[1]
            resultCalcule = oneNumber.toDouble() * secondNumber.toDouble()
            tvInput.text = removeZeroAfterDot(resultCalcule.toString())

            if (prefix.isNotEmpty()) {
                oneNumber += prefix
            }
        }
        else if (tvValue.contains("+")) {
            splitValue = tvValue.split("+")
            oneNumber = splitValue[0]
            secondNumber = splitValue[1]
            resultCalcule = oneNumber.toDouble() + secondNumber.toDouble()
            tvInput.text = removeZeroAfterDot(resultCalcule.toString())

            if (prefix.isNotEmpty()) {
                oneNumber += prefix
            }
        }
        else if (tvValue.contains("/")) {
            splitValue = tvValue.split("/")
            oneNumber = splitValue[0]
            secondNumber = splitValue[1]
            resultCalcule = oneNumber.toDouble() / secondNumber.toDouble()
            tvInput.text = removeZeroAfterDot(resultCalcule.toString())

            if (prefix.isNotEmpty()) {
                oneNumber += prefix
            }
        }
        else{
            tvInput.text = "Err"
        }
        tvResult.text=""
    }

    private fun removeZeroAfterDot(result: String) :String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.indexOf(".0"))
        }
        return value
    }

    fun onOperator(view: View){
        val operatorAdded = isOperatorAdded((tvResult.text.toString()))
        if (lastNumeric && !operatorAdded){
            tvResult.append((view as Button).text)
            lastNumeric =false
            lastDot =false
        }
    }

    private fun isOperatorAdded(value:String) : Boolean{
        return if(value.startsWith(prefix = "-")){
             false
        }else{
             value.contains("/")
                     || value.contains("*")
                     || value.contains("+")
                     || value.contains("-")

        }
    }


}