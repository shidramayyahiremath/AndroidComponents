package com.example.demoofstartedservice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference buttons from the XML layout
        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        val btnStopService = findViewById<Button>(R.id.btn_stop_service)

        // Set onClickListeners for the buttons
        btnStartService.setOnClickListener {
            startService(Intent(this, MyStartedService::class.java))
        }

        btnStopService.setOnClickListener {
            stopService(Intent(this, MyStartedService::class.java))
        }
    }
}
