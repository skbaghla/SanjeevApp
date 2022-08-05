package com.sanjeev.sanjeevapp.module_9;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.sanjeevapp.R;

import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ContactModel> contactModalArrayList;
    private AllContactsActivity context;

    // constructor
    public ContactRVAdapter(ArrayList<ContactModel> contactModalArrayList, AllContactsActivity context) {
        this.contactModalArrayList = contactModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ContactModel modal = contactModalArrayList.get(position);
        holder.contactNameTV.setText(modal.getName());
        holder.contactTel.setText(modal.getTelphone());
        holder.contactEmail.setText(modal.getEmail());
        holder.contactAddress.setText(modal.getAddress());
        //for Image

        Bitmap myBitmap = BitmapFactory.decodeFile(modal.getBlob());

        holder.imgUserImage.setImageBitmap(myBitmap);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.updateContact(modal);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Do you really want to Delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                context.deleteContact(modal);
                                context.refreshList();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return contactModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView contactNameTV, contactTel, contactEmail, contactAddress;

        Button btnUpdate,btnDelete;
        ImageView imgUserImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            contactNameTV = itemView.findViewById(R.id.idTVContactName);
            imgUserImage = itemView.findViewById(R.id.imgUserImage);
            contactEmail = itemView.findViewById(R.id.idTVEmail);
            contactTel = itemView.findViewById(R.id.idTVTelPhone);
            contactAddress = itemView.findViewById(R.id.idTVAddress);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
