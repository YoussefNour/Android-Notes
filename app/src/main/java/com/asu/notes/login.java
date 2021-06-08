package com.asu.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText uname;
    EditText pname;
    Button loginBtn;
    SQLiteDatabase db;
    CheckBox checkBox;
    public static final String EXTRA_MESSAGE = "com.example.notes.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = findViewById(R.id.editTextTextPersonName2);
        pname = findViewById(R.id.editTextTextPassword2);
        loginBtn = findViewById(R.id.LoginButton2);
        checkBox = findViewById(R.id.checkBox);
        db = openOrCreateDatabase("users",MODE_PRIVATE,null);
    }

    public void Login(View view) {
        String username = uname.getText().toString();
        String password = pname.getText().toString();
        Cursor resultSet = db.rawQuery("SELECT * FROM users WHERE Username='"+username+"' AND Password='"+password+"';",null);
        if(!username.equals("")||!password.equals("")){
            if(resultSet.moveToFirst()){
                Toast.makeText(this,"Hello there!",Toast.LENGTH_LONG).show();
                if(checkBox.isChecked()){
                    SharedPreferences loginState = getSharedPreferences(MainActivity.Prefs,0);
                    SharedPreferences.Editor editor = loginState.edit();
                    editor.putString("rememberMe","yes");
                    editor.putString("username",username);
                    editor.commit();
                }
                Intent i = new Intent(this, viewNotes.class);
                i.putExtra(EXTRA_MESSAGE,username);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this,"Invalid Username or Password",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"please enter some credentials",Toast.LENGTH_LONG).show();
        }
    }
}