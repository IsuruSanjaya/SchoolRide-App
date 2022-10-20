package com.ex.schoolride;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnLogOut;
//    FirebaseAuth mAuth;
    Button btnDriver;
    Button btnStudent;
    Button UProfileBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut = findViewById(R.id.btnLogout);

        btnStudent = findViewById(R.id.SstudentBtn);
        btnDriver = findViewById(R.id.DBtndriver);
        UProfileBtn = findViewById(R.id.UbtnUser);



//        btnLogOut.setOnClickListener(view ->{
//            mAuth.signOut();
//            startActivity(new Intent(MainActivity.this, Login.class));
//        });
        UProfileBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, UserProfile.class));
        });
        btnDriver.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DRegistration.class));
        });
        btnStudent.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, StudentLogin.class));
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null){
//            startActivity(new Intent(MainActivity.this, Login.class));
//        }
//    }
}