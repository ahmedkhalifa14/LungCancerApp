package com.graduationproject.lungcancerapp.common.helpers

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkHelper @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnected == true
    }
}