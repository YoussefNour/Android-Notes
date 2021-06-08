package com.asu.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    public static final String Prefs = "MyPreferenceFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("users",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(Username VARCHAR,Password VARCHAR);");
        db.execSQL("INSERT INTO users VALUES('admin','test');");
        db.execSQL("CREATE TABLE IF NOT EXISTS notes(Username VARCHAR,Note LONGTEXT);");
        SharedPreferences loginState = getSharedPreferences(Prefs,0);
        String rememberMe = loginState.getString("rememberMe","no");
        String username = loginState.getString("username","");
        if(rememberMe.equals("yes")){
            Intent i = new Intent(this, viewNotes.class);
            i.putExtra(MainActivity.Prefs,username);
            startActivity(i);
            Toast.makeText(this,"Welcome back",Toast.LENGTH_LONG);
            finish();
        }
    }


    public void openRegister(View view) {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }

    public void openLogin(View view) {
        Intent i = new Intent(getApplicationContext(),login.class);
        startActivity(i);
    }
}