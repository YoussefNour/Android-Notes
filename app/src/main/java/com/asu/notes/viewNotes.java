package com.asu.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewNotes extends AppCompatActivity {
    TextView tv;
    Button createbtn;
    SQLiteDatabase db;
    ListView s;
    ArrayList<String> notes;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotes);
        createbtn = findViewById(R.id.createBtn);
        s = findViewById(R.id.ListViewNotes);
        tv = findViewById(R.id.textViewName);
        SharedPreferences loginState = getSharedPreferences(MainActivity.Prefs,0);
        db = openOrCreateDatabase("users",MODE_PRIVATE,null);
        notes = new ArrayList<String>();
        Intent i = getIntent();
        username = i.getStringExtra(login.EXTRA_MESSAGE);
        if(username == null){
            username = loginState.getString("username","");
            Toast.makeText(this,"Welcome back",Toast.LENGTH_LONG).show();
        }
        tv.setText("Hello "+username);
        Cursor resultSet = db.rawQuery("SELECT * FROM notes WHERE Username='"+username+"';",null);
        int x = 0;
        String note;
        ArrayAdapter<String > adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,notes);
        s.setAdapter(adapter);
        while (resultSet.moveToPosition(x)){
            note = resultSet.getString(1);
            notes.add(note);
            x++;
            // next thing you have to do is check if your adapter has changed
            adapter.notifyDataSetChanged();
        }
    }

    public void writeNote(View view) {
        Intent i = new Intent(getApplicationContext(), writeNote.class);
        i.putExtra(login.EXTRA_MESSAGE,username);
        startActivity(i);
        finish();
    }

    public void logOut(View view) {
        SharedPreferences loginState = getSharedPreferences(MainActivity.Prefs,0);
        SharedPreferences.Editor editor = loginState.edit();
        editor.putString("rememberMe","no");
        editor.putString("username",null);
        editor.commit();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}