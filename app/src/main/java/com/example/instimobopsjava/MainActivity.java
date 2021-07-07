package com.example.instimobopsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

//Ayush Tiwari CE20B022 - Application for MobOps IITM

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> mNamesList;
    EditText mNameEditText;
    static TinyDB tinydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialising
        tinydb = new TinyDB(this);
        mNameEditText=((TextInputLayout)findViewById(R.id.nameEditTextLayout)).getEditText();
        mNamesList=new ArrayList<String>();
        mNamesList=tinydb.getListString("names");
    }

    public void addname(View view) {
        if(mNameEditText.getText().toString().trim()==null){
            Toast.makeText(getApplicationContext(),"Enter Your Name Please",Toast.LENGTH_SHORT).show();
        }
        else{
            String name=mNameEditText.getText().toString().trim();
            mNamesList.add(name);
            //saving data locally on device
            tinydb.putListString("names",mNamesList);
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        }
    }

    public void showAllNames(View view) {
        Intent intent=new Intent(MainActivity.this,AllNames.class);
        intent.putExtra("nameList",mNamesList);
        startActivity(intent);
        mNameEditText.setText("");
    }
}