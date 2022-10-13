package com.ex.schoolride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginOption extends AppCompatActivity {

    Button btnDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        btnDriver = findViewById(R.id.DBtndriver);


        btnDriver.setOnClickListener(view -> {
            startActivity(new Intent(LoginOption.this, DRegistration.class));
        });
    }
}