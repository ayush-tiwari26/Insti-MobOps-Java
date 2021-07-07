package com.example.instimobopsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

//Ayush Tiwari CE20B022 - Application for MobOps IITM

public class AllNames extends AppCompatActivity {
    ListView mListView;
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mNamesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names);
        Context context=this;
        Intent intent=getIntent();
        //initializing
        mNamesList=intent.getStringArrayListExtra("nameList");
        mArrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mNamesList);
        mListView=(ListView)findViewById(R.id.namesListView);
        //setting array adapter
        mListView.setAdapter(mArrayAdapter);


        //adding delete functionality
        Toast.makeText(getApplicationContext(),"Long Click To Delete",Toast.LENGTH_SHORT).show();

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new MaterialAlertDialogBuilder(context)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete Item ?")
                        .setMessage("Item will be deleted")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mNamesList.remove(position);
                                //channging local dataset
                                MainActivity.mNamesList=mNamesList;
                                MainActivity.tinydb.putListString("names",mNamesList);
                                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
                                mArrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
                return false;
            }
        });
    }

    public void addnew(View view){
        // as the previous activity is runnung, we wont need an intent to switch there
        finish();
    }
}