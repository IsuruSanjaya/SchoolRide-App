package com.ex.schoolride;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class StudentProfile extends AppCompatActivity {
    TextView edSname, edsSchool, edsAddress, edsContactNo, edsAge,pDate, pfees;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String sid;
    private FirebaseFirestore db;
    Button SUpdateBtn;
    Button SProfileBack;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        getSupportActionBar().setTitle("School Ride");


        edSname = findViewById(R.id.idvname);
        edsSchool = findViewById(R.id.idvinsurance);
        edsAddress = findViewById(R.id.idvlicense);
        edsContactNo = findViewById(R.id.idvtype);
        edsAge = findViewById(R.id.idvno);

        pDate=findViewById(R.id.idmonthsF);
        pfees=findViewById(R.id.idpFeess);
//        Button delete=findViewById(R.id.DeleteBtn);


        SUpdateBtn = findViewById(R.id.idSUpdateBtn);
        SProfileBack = findViewById(R.id.idSProfilebackBtn);

        SProfileBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), StudentHome.class));
        });


        SUpdateBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), UpdateStudent.class));
        });

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        sid = mAuth.getCurrentUser().getUid();
        uid = mAuth.getCurrentUser().getUid();



        DocumentReference documentReference = fStore.collection("Students").document(sid);
        DocumentReference documentReference1=fStore.collection("Payment").document(uid);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                edSname.setText(documentSnapshot.getString("sName"));
                edsSchool.setText(documentSnapshot.getString("sSchool"));
                edsAddress.setText(documentSnapshot.getString("sAddress"));
                edsContactNo.setText(documentSnapshot.getString("sContactNo"));
                edsAge.setText(documentSnapshot.getString("sAge"));


            }

        });

        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                pDate.setText(documentSnapshot.getString("date"));
                pfees.setText(documentSnapshot.getString( "fee"));
            }
        });

//       delete.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               fStore.collection("Students").document("uid")
//                       .delete()
//                       .addOnSuccessListener(new OnSuccessListener<Void>() {
//                           @Override
//                           public void onSuccess(Void aVoid) {
////                               Log.d(TAG, "DocumentSnapshot successfully deleted!");
//                               startActivity(new Intent(getApplicationContext(),StudentHome.class));
//                               finish();
//                           }
//                       })
//                       .addOnFailureListener(new OnFailureListener() {
//                           @Override
//                           public void onFailure(@NonNull Exception e) {
////                               Log.w(TAG, "Error deleting document", e);
//                           }
//                       });
//           }
//       });



    }
}