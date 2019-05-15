package com.learnkotline.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import android.provider.Telephony.Carriers.PASSWORD
import android.util.Log
import com.learnkotline.model.Users

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null , DB_VERSION){
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $USERNAME TEXT, $PASSWORD TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    //Inserting (Creating) data
    fun addUser(user: Users): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USERNAME, user.userName)
        values.put(PASSWORD, user.passWord)
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.e("InsertedID", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    //get all users
    fun getAllUsers(): MutableList<Users> {
        var allUser : MutableList<Users> = ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var user = Users()
                    user.id = cursor.getInt(cursor.getColumnIndex(ID))
                    user.userName = cursor.getString(cursor.getColumnIndex(USERNAME))
                    user.passWord = cursor.getString(cursor.getColumnIndex(PASSWORD))
                    allUser.add(user)

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allUser
    }
    fun checkUserExist(username: String,password: String): Boolean{
        var flag = false
        val db = readableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $USERNAME = '$username' AND $PASSWORD = '$password'"
        val cursor = db.rawQuery(selectQuery, null)
        flag = cursor.count > 0
        return flag
    }
    fun checkUserAlreadyRegisterd(username: String): Boolean{
        var flag = false
        val db = readableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $USERNAME = '$username'"
        val cursor = db.rawQuery(selectQuery, null)
        flag = cursor.count > 0
        return flag
    }

    companion object {
        private val DB_NAME = "UsersDB"
        private val DB_VERSION = 1;
        private val TABLE_NAME = "users"
        private val ID = "id"
        private val USERNAME = "username"
        private val PASSWORD = "password"
    }
}