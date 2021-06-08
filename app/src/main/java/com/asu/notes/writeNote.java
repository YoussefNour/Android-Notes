package com.asu.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class writeNote extends AppCompatActivity {
    EditText text;
    SQLiteDatabase db;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        text = findViewById(R.id.editTextNote);
        db = openOrCreateDatabase("users",MODE_PRIVATE,null);
        Intent i = getIntent();
        username = i.getStringExtra(login.EXTRA_MESSAGE);
    }

    public void saveNote(View view) {
        String note = text.getText().toString();
        if(!note.equals("")) {
            db.execSQL("INSERT INTO notes VALUES('" + username + "','" + note + "');");
            Intent i = new Intent(getApplicationContext(), viewNotes.class);
            i.putExtra(login.EXTRA_MESSAGE,username);
            Toast.makeText(getApplicationContext(),"Added the note",Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"enter a note",Toast.LENGTH_LONG).show();
        }
    }
}