package com.example.wayfindr.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/** Opens HTTP over a validated internet network, never over the dash's internet-less Wi-Fi. */
object InternetNetwork {
    fun validated(context: Context): Network? {
        val cm = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.allNetworks.firstOrNull { n ->
            cm.getNetworkCapabilities(n)?.let {
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            } == true
        }
    }

    fun openConnection(context: Context, url: String): HttpURLConnection {
        val network = validated(context) ?: throw IOException("No validated internet network is available")
        return network.openConnection(URL(url)) as HttpURLConnection
    }
}
