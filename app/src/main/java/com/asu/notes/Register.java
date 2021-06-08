package com.asu.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username;
    EditText password;
    Button registerbtn;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        registerbtn = findViewById(R.id.RegisterBtn);
        db = openOrCreateDatabase("users",MODE_PRIVATE,null);
    }

    public void registerUser(View view) {
        String usrStr,passStr;
        usrStr = username.getText().toString();
        passStr = password.getText().toString();
        if(!usrStr.equals("")||!passStr.equals("")){
            Cursor resultSet = db.rawQuery("SELECT * FROM users WHERE Username='"+usrStr+"';",null);
            if(resultSet.moveToFirst()) {
                Toast.makeText(this, "User Already registered", Toast.LENGTH_LONG).show();
            }else{
                db.execSQL("INSERT INTO users VALUES('"+usrStr+"','"+passStr+"');");
                Toast.makeText(this,"account registered please login",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        }else{
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show();
        }

    }
}