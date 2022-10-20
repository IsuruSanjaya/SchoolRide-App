package com.ex.schoolride;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLogin extends AppCompatActivity {

    TextView SRegisterHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        SRegisterHere = findViewById(R.id.sBtndriver);

        SRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(StudentLogin.this, StudentRegistration.class));
        });
    }
}