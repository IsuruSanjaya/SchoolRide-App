package com.ex.schoolride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class UpdateDriver extends AppCompatActivity {
    private EditText edDname, edNic, etDvehicle, etDContactNo, etAge;

    private Button dSaveBtn, dShowBtn;
    TextView DriverBack;


    private  String DName, DNIC,DVehicle, DContactNo, DAge ;

    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    String uid = mAuth.getCurrentUser().getUid();


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver);

        edDname = findViewById(R.id.iduvName);
        edNic = findViewById(R.id.iduvNic);
        etDvehicle = findViewById(R.id.iduvvehicle);
        etDContactNo = findViewById(R.id.iduvLicenseNo);
        etAge = findViewById(R.id.iduvVNo);
        dSaveBtn = findViewById(R.id.iduvbutton);
        dShowBtn = findViewById(R.id.iduvShow);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            dSaveBtn.setText("Update");
            DName = bundle.getString("DName");
            DNIC = bundle.getString("DNIC");
            DVehicle = bundle.getString("DVehicle");
            DContactNo = bundle.getString("DContactNo");
            DAge = bundle.getString("DAge");

            edDname.setText(DName);
            edNic.setText(DNIC);
            etDvehicle.setText(DVehicle);
            etDContactNo.setText(DContactNo);
            etAge.setText(DAge);

        }else{
            dSaveBtn.setText("Save");
        }

        dShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateDriver.this , DriverProfile.class));
            }
        });


        dSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DName = edDname .getText().toString();
                String DNIC = edNic .getText().toString();
                String DVehicle = etDvehicle .getText().toString();
                String DContactNo = etDContactNo .getText().toString();
                String DAge = etAge .getText().toString();


                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 !=null){
                    String id = uid;
                    updateToFireStore(uid ,DName, DNIC,DVehicle, DContactNo, DAge);
                }else{
                    String id = uid;
                    saveToFireStore(uid ,DName, DNIC,DVehicle, DContactNo, DAge);
                }

            }
        });
    }

    private void updateToFireStore(String uid,String DName,String DNIC,String DVehicle,String DContactNo,String DAge){

        db.collection("Drivers").document(uid).update("DName" , DName , "DNIC" , DNIC,"DVehicle",DVehicle,"DContactNo",DContactNo,"DAge",DAge)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdateDriver.this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UpdateDriver.this, "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateDriver.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void saveToFireStore(String uid, String DName,String DNIC,String DVehicle,String DContactNo,String DAge){

        if (!DName.isEmpty() && !DNIC.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("DName" , DName);
            map.put("DNIC" , DNIC);
            map.put("DVehicle" , DVehicle);
            map.put("DContactNo" , DContactNo);
            map.put("DAge" , DAge);


            db.collection("Drivers").document(uid).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UpdateDriver.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateDriver.this, "Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }
}

