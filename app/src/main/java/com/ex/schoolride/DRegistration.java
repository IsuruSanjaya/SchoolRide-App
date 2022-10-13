package com.ex.schoolride;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DRegistration extends AppCompatActivity {

    private EditText edDname, edNic, etDvehicle, etDContactNo, etUsername, etPassword;

    private Button submitDriverBtn;

    private  String Dname, DNIC,DVehicle, DContactNo, DUsername, Dpassword ;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dregistration);

        //getting instance from firestore

        db = FirebaseFirestore.getInstance();

        edDname = findViewById(R.id.idDName);
        edNic = findViewById(R.id.idNic);
        etDvehicle = findViewById(R.id.idDvehicle);
        etDContactNo = findViewById(R.id.idDContactNo);
        etUsername = findViewById(R.id.idDUsername);
        etPassword = findViewById(R.id.idDpassword);
        submitDriverBtn = findViewById(R.id.idDbutton);


        submitDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting data from edit text fields
                Dname = edDname.getText().toString();
                DNIC = edNic.getText().toString();
                DVehicle = etDvehicle.getText().toString();
                DContactNo = etDContactNo.getText().toString();
                DUsername = etUsername.getText().toString();
                Dpassword = etPassword.getText().toString();

                //validation

                if (TextUtils.isEmpty(Dname)) {
                    edDname.setError("Enter a name ");
                } else if (TextUtils.isEmpty(DNIC)) {
                    edNic.setError("Username is required");
                } else if (TextUtils.isEmpty(DVehicle)) {
                    etDvehicle.setError("Username is required");
                } else if (TextUtils.isEmpty(DContactNo)) {
                    etDContactNo.setError("Username is required");
                } else if (TextUtils.isEmpty(DUsername)) {
                    etUsername.setError("Username is required");
                } else if (TextUtils.isEmpty(Dpassword)) {
                    etPassword.setError("Username is required");
                } else {
                    //calling mehod to add data to fireabse
                    addDataToFirestore(Dname, DNIC, DVehicle, DContactNo, DUsername, Dpassword);
                }

            }
        });
    }
        private void addDataToFirestore(String Dname, String DNIC,String DVehicle,String DContactNo,String DUsername,String Dpassword)
        {
    // creating a collection reference
    // for our Firebase Firetore database.
    CollectionReference dbDrivers = db.collection("Drivers");

    // adding our data to our courses object class.
    Driver driver = new Driver(Dname, DNIC, DVehicle, DContactNo, DUsername, Dpassword);

    // below method is use to add data to Firebase Firestore.
        dbDrivers.add(driver).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            // after the data addition is successful
            // we are displaying a success toast message.
            Toast.makeText(DRegistration.this, "Your details has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            // this method is called when the data addition process is failed.
            // displaying a toast message when data addition is failed.
            Toast.makeText(DRegistration.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
        }
    });



    }
}