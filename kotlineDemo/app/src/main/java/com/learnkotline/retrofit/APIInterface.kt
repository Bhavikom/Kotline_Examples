package com.learnkotline.retrofit

import `in`.eyehunt.retrofitandroidexamplekotlin.UsersList
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("users?q=rokano")
    fun getUser(): Call<UsersList>
}