package com.example.demoofbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    // Receiver instance
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    lateinit var txt : TextView
    lateinit var button: Button
    val connected = "Wi-Fi is connected"
    val disconnected = "Wi-Fi is Disconnected"
    class NetworkChangeReceiver(private val activity: MainActivity) : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (isNetworkAvailable(context)) {
                Log.d("DemoofBroadcast", "Wi-Fi is connected")
                activity.runOnUiThread{
                    activity.txt.text = activity.connected
                }
            } else {
                Log.d("DemoofBroadcast", "Wi-Fi is not connected")
                activity.runOnUiThread{
                    activity.txt.text = activity.disconnected
                }
            }
        }

        private fun isNetworkAvailable(context: Context?): Boolean {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.textView)
        button =findViewById(R.id.button)

        // Initialize and register the receiver
        networkChangeReceiver = NetworkChangeReceiver(this)
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)


        val mybroadcastreciever  = MyBroadcastReceiver()
        val intentFilter1 = IntentFilter("com.example.demoofbroadcast.CUSTOM_BROADCAST")
        registerReceiver(mybroadcastreciever,intentFilter1)
        button.setOnClickListener{
            val intent = Intent("com.example.demoofbroadcast.CUSTOM_BROADCAST")
            // Send the custom broadcast
            sendBroadcast(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver to prevent memory leaks
        unregisterReceiver(networkChangeReceiver)
    }
}
