package com.ex.schoolride;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    boolean valid = true;


    FirebaseAuth mAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


//        btnLogin.setOnClickListener(view -> {
//            loginUser();
//        });
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, Register.class));
        });

    btnLogin.setOnClickListener((new View.OnClickListener() {
        @Override
        public void onClick (View view){
        checkField(etLoginEmail);
        checkField(etLoginPassword);
        if (valid) {
            mAuth.signInWithEmailAndPassword(etLoginEmail.getText().toString().trim(), etLoginPassword.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(Login.this, "Logged success", Toast.LENGTH_SHORT).show();
                    checkUserAccessLevel(authResult.getUser().getUid());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
    }));

    }


    public boolean checkField(EditText textField){

        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }




    //    private void loginUser(){
//        String email = etLoginEmail.getText().toString();
//        String password = etLoginPassword.getText().toString();
//
//        if (TextUtils.isEmpty(email)){
//            etLoginEmail.setError("Email cannot be empty");
//            etLoginEmail.requestFocus();
//        }else if (TextUtils.isEmpty(password)){
//            etLoginPassword.setError("Password cannot be empty");
//            etLoginPassword.requestFocus();
//        }else{
//            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()){
//                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Login.this, MainActivity.class));
//                    }else{
//                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
    public void checkUserAccessLevel(String uid){
        DocumentReference df =fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG, "Onsuccess"+ documentSnapshot.getData());

                //IDENTIFY THE USER ACCESS

                if(documentSnapshot.getString("isTeacher") !=null){
                    //user admin

                    startActivity(new Intent(getApplicationContext(),DriverHome.class));
                    finish();

                }

                if(documentSnapshot.getString("isStudent") !=null){
                    startActivity(new Intent (getApplicationContext(),StudentHome.class));
                    finish();

                }


            }
        });
    }
    protected   void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() !=   null){
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("isTeacher") !=null){
                        startActivity(new Intent(getApplicationContext(),DriverHome.class));
                        finish();

                    }
                    if(documentSnapshot.getString("isStudent") !=null){
                        startActivity(new Intent(getApplicationContext(),StudentHome.class));
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),Login.class));

                }
            });



        }
    }
}