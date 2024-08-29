package com.example.demoofforegroundservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        val btnStopService = findViewById<Button>(R.id.btn_stop_service)

        btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also { intent ->
                intent.action = "START"
                startService(intent)
            }
        }

        btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also { intent ->
                intent.action = "STOP"
                startService(intent)
            }
        }
    }
}
