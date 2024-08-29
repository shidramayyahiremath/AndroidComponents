package com.example.demoofbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the received broadcast
        Toast.makeText(context, "Custom Broadcast Received!", Toast.LENGTH_SHORT).show()
        Log.d("DemoofBroadcast", "Custom Broadcast Received!")
    }
}
