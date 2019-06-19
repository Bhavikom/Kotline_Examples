package com.learnkotline

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.annotation.IdRes
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_base2.*
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base2)
        supportActionBar?.hide()
    }
    fun <T : View> Activity.bind(@IdRes res : Int) : Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return lazy(LazyThreadSafetyMode.NONE){ findViewById(res) as T }
    }
    fun showToast(message: String){
        toast(message)
    }
    fun showSnackBar(message: String) {
        //Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG).show();
        val mSnackBar = Snackbar.make(findViewById(android.R.id.content), message, 3000)
        val view = mSnackBar.getView()
        val params = view.getLayoutParams() as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        view.setLayoutParams(params)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setBackgroundColor(applicationContext.getColor(R.color.abc_background_cache_hint_selector_material_dark))
        }else{
            view.setBackgroundColor(resources.getColor(R.color.abc_background_cache_hint_selector_material_dark))
        }
        val mainTextView = view.findViewById(R.id.snackbar_text) as TextView
        mainTextView.setTextColor(Color.WHITE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mainTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        else
            mainTextView.gravity = Gravity.CENTER_HORIZONTAL
        mSnackBar.show()
    }
    fun showAlertDialog(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

}
