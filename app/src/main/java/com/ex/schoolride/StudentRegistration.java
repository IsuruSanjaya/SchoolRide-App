package com.ex.schoolride;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentRegistration extends AppCompatActivity {

    private EditText edSname, edsSchool, edsAddress, edsContactNo, edsAge, edsEmail,edsPassword;

    private Button submitStdBtn;
    TextView StdLog;


    private String sName, sSchool, sAddress, sContactNo, sAge, sEmail, sPassword;

    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    String sid = mAuth.getCurrentUser().getUid();


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);



        //getting instance from firestore

        db = FirebaseFirestore.getInstance();

        edSname = findViewById(R.id.idsName);
        edsSchool = findViewById(R.id.idsSchool);
        edsAddress = findViewById(R.id.idsAddress);
        edsContactNo = findViewById(R.id.idsContactNo);
        edsAge = findViewById(R.id.idSage);
        edsEmail = findViewById(R.id.idsEmail);
        edsPassword = findViewById(R.id.idsPassword);
        StdLog = findViewById(R.id.idStdLog);
        submitStdBtn = findViewById(R.id.idSbutton);

        StdLog.setOnClickListener(view ->{
            startActivity(new Intent(StudentRegistration.this, StudentLogin.class));
        });

        submitStdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting data from edit text fields
                sName = edSname.getText().toString();
                sSchool = edsSchool.getText().toString();
                sAddress = edsAddress.getText().toString();
                sContactNo = edsContactNo.getText().toString();
                sAge = edsAge.getText().toString();
                sEmail = edsEmail.getText().toString();
                sPassword = edsPassword.getText().toString();


                //validation

                if (TextUtils.isEmpty(sName)) {
                    edSname.setError("Enter a name ");
                } else if (TextUtils.isEmpty(sSchool)) {
                    edsSchool.setError("school name is required");
                } else if (TextUtils.isEmpty(sContactNo)) {
                    edsContactNo.setError("contact no is required");
                } else if (TextUtils.isEmpty(sAge)) {
                    edsAge.setError("Username is required");
                } else if (TextUtils.isEmpty(sEmail)) {
                    edsEmail.setError("Username is required");
                } else if (TextUtils.isEmpty(sPassword)) {
                    edsPassword.setError("Username is required");
                } else {
                    //calling mehod to add data to fireabse
                    addDataToFirestore(sName, sSchool,sAddress  ,sContactNo, sAge, sEmail, sPassword);
                }

            }
        });
    }
    private void addDataToFirestore(String sName, String sSchool,  String sAddress, String sContactNo , String  sAge ,String  sEmail,  String sPassword)
    {
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbStudent = db.collection("Students");

        // adding our data to our courses object class.
        Student student = new Student(sName, sSchool, sAddress, sContactNo, sAge, sEmail, sPassword);

        // below method is use to add data to Firebase Firestore.
        dbStudent.document(sid).set(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(StudentRegistration.this, "Your details has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentRegistration.this, StudentLogin.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(StudentRegistration.this, "Fail to add \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }
}