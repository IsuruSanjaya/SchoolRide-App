package com.ex.schoolride;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {


    EditText etemail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent data = getIntent();
        String Email=data.getStringExtra("Email");
        String Password = data.getStringExtra("Password");

        etemail=findViewById(R.id.UEmail);
        etPassword=findViewById(R.id.uPass);
        etemail.setText(Email);
        etPassword.setText(Password);


        Log.d(TAG, "onCreate : " +Email+ "" +Password);





    }
}