package com.sanjeev.sanjeevapp.module_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sanjeev.sanjeevapp.R;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    Button btnAdd,btnSearch,btnAllContact;

    private DBHandlerContacts dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        initVariables();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Clear the Activity's bundle of the subsidiary fragments' bundles.
        outState.clear();
    }

    private void initVariables() {
        dbHandler = new DBHandlerContacts(this);

        btnAdd = findViewById(R.id.btnAddContact);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactListActivity.this,AddContactActivity.class));
            }
        });
        btnSearch = findViewById(R.id.btnSearchContact);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactListActivity.this,SearchContact.class));
            }
        });

        btnAllContact = findViewById(R.id.btnAllContacts);

        btnAllContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<ContactModel> contactModels = dbHandler.readAllContacts();
                Intent intent = new Intent(ContactListActivity.this, AllContactsActivity.class);
                intent.putExtra("data",contactModels);
                startActivity(intent);
            }
        });

    }
}