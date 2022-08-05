package com.sanjeev.sanjeevapp.module_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sanjeev.sanjeevapp.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtLogin,txtSignup,txtDisplayMessage;
    EditText edtEmail,edtPwd;
    Button btnLogin,btnClear;
    View lineLogin,lineSignup;
    String defaultUsername = "user1";
    String defaultPwd = "123456";

    Boolean isLogin = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_constraints);

        initVariables();

        setupClickListners();
    }
    private void initVariables() {
        txtLogin = findViewById(R.id.login_text_view);
        txtSignup = findViewById(R.id.txtSignup);
        txtDisplayMessage = findViewById(R.id.welcom_back_log_in_text_view);
        edtEmail = findViewById(R.id.edtEmail);
        edtPwd = findViewById(R.id.edtPwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);
        lineLogin = findViewById(R.id.viewLogin);
        lineSignup = findViewById(R.id.viewSignup);
    }

    private void setupClickListners() {
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin = false;
                lineSignup.setVisibility(View.VISIBLE);
                lineLogin.setVisibility(View.GONE);
                txtDisplayMessage.setText("Create New Account");
                txtSignup.setTextColor(Color.parseColor("#FFFFFF"));
                txtLogin.setTextColor(Color.parseColor("#CACACA"));
                btnLogin.setText("Signup");
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin = true;
                lineLogin.setVisibility(View.VISIBLE);
                lineSignup.setVisibility(View.GONE);
                txtDisplayMessage.setText("Login to your Account");
                txtSignup.setTextColor(Color.parseColor("#CACACA"));
                txtLogin.setTextColor(Color.parseColor("#FFFFFF"));
                btnLogin.setText("Login");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtEmail.setText("");
                edtPwd.setText("");

                if (isLogin)
                    txtDisplayMessage.setText("Login to your Account");
                else
                    txtDisplayMessage.setText("Create New Account");

            }
        }); // point 2.

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                txtDisplayMessage.setText("welcome"); // point 1.
                if (validations())
                {
                    txtDisplayMessage.setText("Success!");
                }
            }
        });

    }

    private boolean validations() {
        if (edtEmail.getText().toString().isEmpty())
        {
            edtEmail.setError("!");
            Toast.makeText(LoginActivity.this, "Please enter Username", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (edtPwd.getText().toString().isEmpty())
        {
            edtPwd.setError("sdfsdf!");
            Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!edtEmail.getText().toString().equals(defaultUsername))
        {
            Toast.makeText(LoginActivity.this, "Wrong Username", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!edtPwd.getText().toString().equals(defaultPwd))
        {
            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

}