package com.exampledemo.parsaniahardik.basicskotlin

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.system.Os.bind
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // replace findViewById() by following
    private val button: Button by bind(R.id.btn)
    private val buttonFunction: Button by bind(R.id.btnfunction)
    private val textview: TextView by bind(R.id.text)
    private val imageView: ImageView by bind(R.id.img)
    val x: Int? = null
    //declare variables like below
    private var stringVariable : String? = null //declaring string variable
    private var integerVariable : Int? = null //value of this variable can be changed
    private val readOnlyVariable: Int = 1 //value of this variable can not be changed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val y = x!!.toDouble()
        Log.e(" zero "," check null value : "+x)
        button.text = "give string to button by this way!"

        Toast.makeText(this," done "+button.text,Toast.LENGTH_SHORT).show();

        stringVariable = button.text as String //giving button's string to stringVariable

        textview.text = stringVariable //setting textview's text same as button's text

        integerVariable = 1  //giving any integer value to integer variable

        buttonFunction.setOnClickListener(){
            giveAddition(2,3)
           Toast.makeText(this, "This answer is from Kotlin function -->"+giveAddition(2,3), Toast.LENGTH_LONG).show()
        }

        imageView.setImageResource(R.mipmap.ic_launcher)

    }

    fun <T : View> Activity.bind(@IdRes res : Int) : Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return lazy(LazyThreadSafetyMode.NONE){ findViewById(res) as T }
    }

    fun giveAddition(a: Int, b: Int): Int {
        return a + b
    }

}
