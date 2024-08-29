package com.example.content_provider_in_android

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovidersinandroid.MyContentProvider

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var resultView: TextView
    private lateinit var addButton: Button
    private lateinit var showButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        nameEditText = findViewById(R.id.textName)
        resultView = findViewById(R.id.res)
        addButton = findViewById(R.id.insertButton)
        showButton = findViewById(R.id.loadButton)

        // Set onClick listeners
        addButton.setOnClickListener { addDetails() }
        showButton.setOnClickListener { showDetails() }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.onTouchEvent(event)
    }

    private fun addDetails() {
        // Class to add values in the database
        val values = ContentValues().apply {
            put(MyContentProvider.NAME, nameEditText.text.toString())
        }

        // Inserting into the database through content URI
        contentResolver.insert(MyContentProvider.CONTENT_URI, values)

        // Displaying a toast message
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
    }

    private fun showDetails() {
        // Creating a cursor object for the content URI
        val cursor = contentResolver.query(
            Uri.parse("content://com.demo.user.provider/users"),
            null,
            null,
            null,
            null
        )

        // Iteration of the cursor to print the whole table
        cursor?.let {
            if (it.moveToFirst()) {
                val strBuild = StringBuilder()
                do {
                    strBuild.append("${it.getString(it.getColumnIndexOrThrow("id"))} - ${it.getString(it.getColumnIndexOrThrow("name"))}\n")
                } while (it.moveToNext())
                resultView.text = strBuild.toString()
            } else {
                resultView.text = "No Records Found"
            }
            it.close()
        }
    }
}
