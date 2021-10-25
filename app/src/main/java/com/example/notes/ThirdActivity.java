package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //1. Get EditText view
        EditText contentTextView = (EditText) findViewById(R.id.noteText);

        //2. Get Intent
        Intent intent = getIntent();

        //3. Get the value of integer "noteid" from intent.
        int value = intent.getIntExtra("noteid", -1);

        //4. Initialize class variable "noteid" with the value from intent.
        noteid = value;

        if(noteid != -1){
            //Display content of note by retrieving "notes" ArrayList in SecondActivity.
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            contentTextView.setText(noteContent);
        }

    }

    public void saveMethod(View view){
        //1. Get editText view and the content that user created.
        EditText contentTextView = (EditText) findViewById(R.id.noteText);
        String content = contentTextView.getText().toString();
//        System.out.println("saveMethod");
//        System.out.println(contentTextView.getText().toString());

        //2. Initialize SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //3. Initialize DBHelper
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        //4. Set username in the following variable by fetching it from SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        //5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){ //Add Note
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }else{ //Update note.
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNotes(title, date, content, username);
        }

        //6. Go to second activity using intents.
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}