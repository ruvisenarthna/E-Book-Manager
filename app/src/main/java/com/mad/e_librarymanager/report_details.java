package com.mad.e_librarymanager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class report_details extends AppCompatActivity {
    private EditText name,address,pno,exp_del,exp_arr;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        db=FirebaseFirestore.getInstance();
        exp_arr=findViewById(R.id.editTextDate3);
        name=findViewById(R.id.editTextTextPersonName);
        address=findViewById(R.id.editTextTextPersonName2);
        pno=findViewById(R.id.editTextPhone);
        exp_del=findViewById(R.id.editTextDate2);
        getAllData();
    }
    public void greportBtn(View view) {
        Intent intent = new Intent(this,report.class);
        startActivity(intent);
    }

    private void getAllData(){
        db.collection("shipments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                name.setText(document.get("fname").toString());
                                address.setText(document.get("address").toString());
                              pno.setText(document.get("phno").toString());
                                exp_del.setText("12-07-2021");
                                exp_arr.setText("24-07-2021");
                                break;

                            }

                        } else {
                            Toast.makeText(report_details.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}