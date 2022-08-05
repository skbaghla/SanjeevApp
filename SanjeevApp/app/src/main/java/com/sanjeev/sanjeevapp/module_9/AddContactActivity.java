package com.sanjeev.sanjeevapp.module_9;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanjeev.sanjeevapp.R;

import java.io.IOException;
import java.io.Serializable;

public class AddContactActivity extends AppCompatActivity {


    EditText edtName, edtTel, edtEmail, edtAddress;

    Button btnAdd;
    private DBHandlerContacts dbHandler;

    String from = "add";

    String currentID = "";
    ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initVars();

        try {
            if (getIntent().hasExtra("from")) {
                from = getIntent().getStringExtra("from");
                if (from.equals("update")) {
                    ContactModel data = (ContactModel) getIntent().getSerializableExtra("data");
                    currentID = String.valueOf(data.getId());
                    edtName.setText(data.getName());
                    edtAddress.setText(data.getAddress());
                    edtTel.setText(data.getTelphone());
                    edtEmail.setText(data.getEmail());

                    btnAdd.setText("Update");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String mImageBitmap;
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {

                    try {
                        mImageBitmap = ImageFilePath.getPath(AddContactActivity.this, uri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    imgUser.setImageURI(uri);
                }
            });
    private void initVars() {
        dbHandler = new DBHandlerContacts(this);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgUser = findViewById(R.id.imgUser);

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        edtName = findViewById(R.id.idEdtcontactName);
        edtTel = findViewById(R.id.idEdtcontactTel);
        edtEmail = findViewById(R.id.idEdtcontactEmail);
        edtAddress = findViewById(R.id.idEdtcontactAddress);
        btnAdd = findViewById(R.id.idBtnAddcontact);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validations()) {
                    if (from.equals("add")) {
                        dbHandler.addNewContact(mImageBitmap,edtName.getText().toString(), edtTel.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString());
                        Toast.makeText(AddContactActivity.this, "Contact Added Successfully.", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtTel.setText("");
                        edtEmail.setText("");
                        edtAddress.setText("");
                        imgUser.setImageResource(R.drawable.user_image);
                    }
                    else{

                        dbHandler.updateContact(edtName.getText().toString(), edtTel.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString(),currentID);
                        Toast.makeText(AddContactActivity.this, "Contact Updated Successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean validations() {
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
            return false;
        } else if (edtTel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Telephone", Toast.LENGTH_SHORT).show();
            edtTel.requestFocus();
            return false;
        } else if (edtEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            edtEmail.requestFocus();
            return false;
        } else if (edtAddress.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Address", Toast.LENGTH_SHORT).show();
            edtAddress.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}