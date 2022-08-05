package com.sanjeev.sanjeevapp.module_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanjeev.sanjeevapp.R;
import com.sanjeev.sanjeevapp.module_8.ViewCourses;

import java.util.ArrayList;

public class AllContactsActivity extends AppCompatActivity {

    RecyclerView rvContact;
    ContactRVAdapter mAdapter;
    ArrayList<ContactModel> mList = new ArrayList<>();

    DBHandlerContacts dbHandlerContacts;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Clear the Activity's bundle of the subsidiary fragments' bundles.
        outState.clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        dbHandlerContacts = new DBHandlerContacts(this);

        try {

            mList = (ArrayList<ContactModel>) getIntent().getSerializableExtra("data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView imgBack = findViewById(R.id.imgBack);
        ImageView imgRefresh = findViewById(R.id.imgRefresh);
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshList();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (mList.size() > 0) {
            mAdapter = new ContactRVAdapter(mList, AllContactsActivity.this);
            rvContact = findViewById(R.id.rvContacts);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllContactsActivity.this, RecyclerView.VERTICAL, false);
            rvContact.setLayoutManager(linearLayoutManager);

            // setting our adapter to recycler view.
            rvContact.setAdapter(mAdapter);
        } else {
            Toast.makeText(this, "No contact found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void updateContact(ContactModel modal) {
        Intent intent = new Intent(AllContactsActivity.this, AddContactActivity.class);
        intent.putExtra("data", modal);
        intent.putExtra("from", "update");
        startActivity(intent);
    }

    public void deleteContact(ContactModel modal) {
        dbHandlerContacts.deleteContact(modal);
    }

    public void refreshList() {
        ArrayList<ContactModel> contactModels = dbHandlerContacts.readAllContacts();
        mList.clear();
        mList = contactModels;
        if (mList.size() > 0) {
            mAdapter = new ContactRVAdapter(mList, AllContactsActivity.this);
            rvContact = findViewById(R.id.rvContacts);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllContactsActivity.this, RecyclerView.VERTICAL, false);
            rvContact.setLayoutManager(linearLayoutManager);

            // setting our adapter to recycler view.
            rvContact.setAdapter(mAdapter);
            Toast.makeText(this, "Contact List Updated.", Toast.LENGTH_SHORT).show();
        }
    }
}