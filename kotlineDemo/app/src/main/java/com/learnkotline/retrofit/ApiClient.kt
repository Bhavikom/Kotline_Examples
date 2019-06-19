package com.learnkotline.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{

    companion object Factory {
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {

            var intercepter: HttpLoggingInterceptor = HttpLoggingInterceptor()
            intercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
            var okHttp: OkHttpClient = OkHttpClient.Builder().addInterceptor(intercepter).build()

            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp)
                    .build()
            return retrofit;
        }
    }
}