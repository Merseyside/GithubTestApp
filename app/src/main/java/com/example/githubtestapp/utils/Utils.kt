package com.example.githubtestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.githubtestapp.GithubApplication

class Utils {

    companion object {


        fun isOnline(context: Context = GithubApplication.getInstance()): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }
    }
}