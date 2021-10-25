package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String usernameKey = "username";
    //String usernamekey = "username";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernamekey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes",Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);

        if(!sharedPreferences.getString(usernamekey,"").equals("")){
            String user = sharedPreferences.getString("username","");
            goToActivity2();
        }else{
            setContentView(R.layout.activity_main);
        }
    }

    public void goToActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void onButtonClick(View view){
        //1. Get username and password via EditText view
        EditText username = (EditText) findViewById(R.id.userNameTextView);
        EditText password = (EditText) findViewById(R.id.passwordTextView);

        //2. Add username to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username.getText().toString()).apply();
        // Trying to print in console
        System.out.println(username.getText().toString());
        System.out.println("YOOOO");




        //3. Start Second Activity
        goToActivity2();
    }
}