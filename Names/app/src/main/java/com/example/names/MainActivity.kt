package com.example.names

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val CONTENT_URI = Uri.parse("content://com.demo.user.provider/users")
    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        resultView = findViewById(R.id.res)
        val showDetailsButton = findViewById<Button>(R.id.loadButton)

        // Set click listener for the "Show Details" button
        showDetailsButton.setOnClickListener {
            showDetails()
        }
    }

    private fun showDetails() {
        // Creating a cursor object of the content URI
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)

        // Iteration of the cursor to print the whole table
        if (cursor != null && cursor.moveToFirst()) {
            val strBuild = StringBuilder()
            do {
                val idIndex = cursor.getColumnIndex("id")
                val nameIndex = cursor.getColumnIndex("name")

                // Check column indices are valid before accessing them
                if (idIndex != -1 && nameIndex != -1) {
                    strBuild.append("${cursor.getString(idIndex)} - ${cursor.getString(nameIndex)}\n")
                }
            } while (cursor.moveToNext())
            resultView.text = strBuild.toString()
        } else {
            resultView.text = "No Records Found"
        }

        // Close the cursor to prevent memory leaks
        cursor?.close()
    }
}
