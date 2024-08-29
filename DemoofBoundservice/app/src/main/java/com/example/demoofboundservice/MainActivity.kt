package com.example.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.demoofboundservice.MyService
import com.example.demoofboundservice.R

class MainActivity : ComponentActivity() {
    private var bound = false
    private var myService: MyService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.LocalBinder
            myService = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBindService = findViewById<Button>(R.id.btn_bind_service)
        val btnGetTime = findViewById<Button>(R.id.btn_get_time)
        val btnUnbindService = findViewById<Button>(R.id.btn_unbind_service)
        val tvTime = findViewById<TextView>(R.id.tv_time)

        btnBindService.setOnClickListener {
            Intent(this, MyService::class.java).also { intent ->
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
        }

        btnGetTime.setOnClickListener {
            if (bound) {
                tvTime.text = myService?.getCurrentTime() ?: "Service not bound"
            }
        }

        btnUnbindService.setOnClickListener {
            if (bound) {
                unbindService(connection)
                bound = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }
}
