package com.sanjeev.sanjeevapp

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.os.Process.killProcess
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var edtUsername : EditText
    lateinit var edtPwd : EditText
    lateinit var btnLogin : Button
    var TAG = "AndroidLifeCycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG,"OnCreate Called")

        initialize()
        setupMethods()
    }

    private fun initialize() {
        edtUsername = findViewById(R.id.idEdtUserName)
        edtPwd = findViewById(R.id.idEdtPassword)
        btnLogin = findViewById(R.id.idBtnLogin)
    }

    private fun setupMethods() {
        btnLogin.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
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

    /*
    * usually it depends upon Phone's RAM that it will clear the app from background. that create scenario to get onCreate after onStop without onDestroy called.
So, I created the scenario to call onCreate after onStop.
by killing app process manually on "onStop"
So now I am able to produce onPause->onStop->OnCreate
when minimizing and reopening the app
without getting onDestroy called.

used below code to do that

int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
*/
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop invoked")
        val pid = Process.myPid()
        killProcess(pid)
//        System.exit(0)
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