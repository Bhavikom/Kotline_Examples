package com.learnkotline.model

data class Person(val name: String = "default name", val age: Int = 30,
                  val email: String = "dummy email", val phone: Long = 1234567890){
    fun returnME() : String{
        return "Aryan is great"

    }

}
