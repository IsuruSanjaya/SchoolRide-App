package com.ex.schoolride;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class StudentList extends AppCompatActivity {
     TextView edSname, edsSchool, edsAddress, edsContactNo, edsAge, edsEmail,edsPassword;
     FirebaseAuth fAuth;
     FirebaseFirestore fStore;
     String sid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        edSname = findViewById(R.id.listsName);
        edsSchool = findViewById(R.id.listsSchool);
//        edsAddress = findViewById(R.id.listsAddress);
//        edsContactNo = findViewById(R.id.listsContactNo);
//        edsAge = findViewById(R.id.listSage);
//        edsEmail = findViewById(R.id.listsEmail);

        fAuth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();
         sid=fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("Students").document(sid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                edSname.setText(documentSnapshot.getString("sName"));
                edsSchool.setText(documentSnapshot.getString("sSchool"));
//                edsAddress.setText(documentSnapshot.getString("sAddress"));
//                edsContactNo.setText(documentSnapshot.getString("sContactNo"));
//                edsAge.setText(documentSnapshot.getString("sAge"));
//                edsEmail.setText(documentSnapshot.getString("sEmail"));


            }
        });

    }
}