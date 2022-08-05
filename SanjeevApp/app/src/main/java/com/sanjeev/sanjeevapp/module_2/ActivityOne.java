package com.sanjeev.sanjeevapp.module_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sanjeev.sanjeevapp.R;
import com.sanjeev.sanjeevapp.SecondActivity;

public class ActivityOne extends AppCompatActivity {

    Button btnLogin;

    String TAG = "ActivityOneLifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();
    }

    private void initVariables() {
        btnLogin = findViewById(R.id.idBtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOne.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop invoked");

        //comment below code to proceed without onStop->onCreate
        //If below code is commented it will go to second activity
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }

}