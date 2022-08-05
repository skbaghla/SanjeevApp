package com.sanjeev.sanjeevapp.module_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanjeev.sanjeevapp.R;

import java.util.ArrayList;

public class SearchContact extends AppCompatActivity {

    Button btnSearch;
    EditText edtName, edtEmail;
    private DBHandlerContacts dbHandler;

    ArrayList<ContactModel> contactModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        initVariables();
    }

    private void initVariables() {
        edtEmail = findViewById(R.id.edtSearchEmail);
        edtName = findViewById(R.id.edtSearchName);
        btnSearch = findViewById(R.id.btnSearch);
        dbHandler = new DBHandlerContacts(this);
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    if (edtName.getText().toString().isEmpty())
                    {
                        contactModels = dbHandler.searchContact("", edtEmail.getText().toString());
                    }
                    else
                    {
                        contactModels = dbHandler.searchContact("name", edtName.getText().toString());
                    }
                    Intent intent = new Intent(SearchContact.this, AllContactsActivity.class);
                    intent.putExtra("data",contactModels);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean validations() {
        if (edtEmail.getText().toString().isEmpty() && edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter email or name to search", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}