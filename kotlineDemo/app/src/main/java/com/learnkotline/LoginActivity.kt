package com.learnkotline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.constraintlayout.solver.widgets.Helper
import com.learnkotline.database.DatabaseHandler
import com.learnkotline.helper.MyPreferences
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity
import org.w3c.dom.Text

class LoginActivity : BaseActivity() {

    var databaseHandler: DatabaseHandler? = null
    var sharedPreference: MyPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseHandler = DatabaseHandler(this)
        sharedPreference = MyPreferences(this)
        btnLogIn.setOnClickListener {
            if(checkValidation()){
                if(databaseHandler!!.checkUserExist(edittextUserName.text.toString(),edittextPassword.text.toString())){
                    sharedPreference!!.saveString(MyPreferences.KEY_LOGIN,"yes")
                    sharedPreference!!.saveString(MyPreferences.KEY_USERNAME,edittextUserName.text.toString())
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    showToast(resources.getString(R.string.username_or_password_is_wrong))
                }
            }
        }
        textviewSignUp.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }
    fun checkValidation(): Boolean{
        var validate = false

        if (!TextUtils.isEmpty(edittextUserName.text) && !TextUtils.isEmpty(edittextPassword.text)) {
            if (com.learnkotline.helper.Helper.isNetworkAvailable(this)) {
                validate = true
            }
            else{
                showSnackBar(resources.getString(R.string.please_check_network_connection))
                validate = false
            }
        } else {
            showSnackBar(resources.getString(R.string.please_complete_all_field))
            validate = false
        }
        return validate
    }
}
