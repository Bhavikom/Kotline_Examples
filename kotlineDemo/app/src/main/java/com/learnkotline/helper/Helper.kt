package com.learnkotline.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService

class Helper {
    companion object {
        val strUrl = "www.google.com"
        fun isNetworkAvailable(activity: Context): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

    }
}