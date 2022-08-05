package com.sanjeev.sanjeevapp.module_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sanjeev.sanjeevapp.R;

public class ActivityTwo extends AppCompatActivity {

    String TAG = "SecondLifeCycle";

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initVar();
    }

    private void initVar() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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