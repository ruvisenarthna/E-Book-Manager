package com.mad.e_librarymanager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class cardDetails extends AppCompatActivity {


    private EditText cno,cvv,exp;
    private Button add,edit;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        db=FirebaseFirestore.getInstance();
        cno=findViewById(R.id.editTextNumber2);
        cvv=findViewById(R.id.editTextNumber3);
        exp=findViewById(R.id.editTextDate);

        add=findViewById(R.id.button3);
        edit=findViewById(R.id.button2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cardDetails.this,"check",Toast.LENGTH_SHORT).show();
                if(checkAll()){
                    addData();
                }
            }
        });
    }

    private void addData(){
        Map<String, Object> card = new HashMap<>();
        String id = UUID.randomUUID().toString();
        card.put("id",id);
        card.put("cno",cno.getText().toString());
        card.put("cvv",cvv.getText().toString());
        card.put("exp",exp.getText().toString());

        db.collection("cardDetails")
                .add(card)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(cardDetails.this,"Successfully added", Toast.LENGTH_SHORT).show();
                        cno.setText("");
                        cvv.setText("");
                        exp.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(cardDetails.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private boolean checkAll(){
        if(cno.length()==0){
            cno.setError("card number Name is required");
            return false;
        }
        if(cvv.length()==0){
            cvv.setError("cvv is required");
            return false;
        }
        if(exp.length()==0){
            exp.setError("expirary date  is required");
            return false;
        }


        return true;
    }
    public void backBtn1(View view) {
        Intent intent = new Intent(this,customerDetails.class);
        startActivity(intent);
    }
}