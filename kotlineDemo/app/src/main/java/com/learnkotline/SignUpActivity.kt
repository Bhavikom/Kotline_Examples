package com.learnkotline

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.learnkotline.database.DatabaseHandler
import com.learnkotline.helper.Helper
import com.learnkotline.model.Users
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    var databaseHandler: DatabaseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        databaseHandler = DatabaseHandler(this)
        btnSignUp.setOnClickListener {
            if (checkValidation()) {
                if (databaseHandler!!.checkUserAlreadyRegisterd(edittextUsername.text.toString())) {
                    showToast(resources.getString(R.string.username_exist))
                } else {
                    val user: Users = Users()
                    var success: Boolean = false
                    user.userName = edittextUsername.text.toString()
                    user.passWord = edittextPassword.text.toString()
                    success = databaseHandler!!.addUser(user)
                    if (success) {
                        showToast(resources.getString(R.string.you_are_registered_successfully))
                        clearValues()
                    } else {
                        showToast(resources.getString(R.string.something_went_wroing))
                    }
                }
            }
        }
        textviewLogin.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun checkValidation(): Boolean {
        var validate = false

        if (TextUtils.isEmpty(edittextUsername.text.toString()) || TextUtils.isEmpty(edittextUsername.text.toString())
                || TextUtils.isEmpty(edittextUsername.text.toString()) || TextUtils.isEmpty(edittextUsername.text.toString())) {
            showSnackBar(resources.getString(R.string.please_complete_all_field))
            validate = false
        } else {
            if (Helper.isEmailValid(edittextEmail.text.toString())) {
                if (edittextPassword.text.toString().equals(edittextPassword2.text.toString())) {
                    if (Helper.isNetworkAvailable(this)) {
                        validate = true
                    } else {
                        showSnackBar(resources.getString(R.string.please_check_network_connection))
                        validate = false
                    }
                } else {
                    showSnackBar(resources.getString(R.string.password_should_be_same))
                    validate = false
                }

            } else {
                showSnackBar(resources.getString(R.string.enter_valid_email_address))
                validate = false
            }

        }

        return validate
    }
    fun clearValues(){
        edittextEmail.text.clear()
        edittextPassword.text.clear()
        edittextPassword2.text.clear()
        edittextUsername.text.clear()

    }

}
