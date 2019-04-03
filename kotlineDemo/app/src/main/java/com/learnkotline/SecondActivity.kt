package com.learnkotline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle: Bundle=intent.extras
        val samplename:String
        //if(bundle!=null)
        //{
            samplename = bundle.getString("message")
            Log.e(" get intent "," second activity : $samplename ")
        //}
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent2 = Intent(this,MainActivity::class.java)
        startActivity(intent2)
        finish()
    }
}
