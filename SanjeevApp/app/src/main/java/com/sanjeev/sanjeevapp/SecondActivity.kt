package com.sanjeev.sanjeevapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    var TAG = "SecondActivityLifeCycle"

    lateinit var btnBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart invoked")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume invoked")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause invoked")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop invoked")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy invoked")
    }

}