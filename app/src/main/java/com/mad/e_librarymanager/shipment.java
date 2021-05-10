package com.mad.e_librarymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class shipment extends AppCompatActivity {

    private EditText fname,lname,address,city,pcode,email,phno;
    private Button add,delete,edit;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        pcode=findViewById(R.id.pCode);
        email=findViewById(R.id.email);
        phno=findViewById(R.id.phNo);

        add=findViewById(R.id.saveBtn);
        delete=findViewById(R.id.delBtn);
        edit=findViewById(R.id.editBtn);
        db=FirebaseFirestore.getInstance();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAll()){
                    addData();
                }
            }
        });
    }

    private void addData(){
        Map<String, Object> ship = new HashMap<>();
        String id = UUID.randomUUID().toString();
        ship.put("id",id);
        ship.put("fname",fname.getText().toString());
        ship.put("lname",lname.getText().toString());
        ship.put("address",address.getText().toString());
        ship.put("city",city.getText().toString());
        ship.put("email",email.getText().toString());
        ship.put("phno",phno.getText().toString());
        ship.put("pcode",pcode.getText().toString());
        db.collection("shipments")
                .add(ship)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(shipment.this,"Successfully added", Toast.LENGTH_SHORT).show();
                        fname.setText("");
                        lname.setText("");
                        address.setText("");
                        city.setText("");
                        pcode.setText("");
                        email.setText("");
                        phno.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(shipment.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean checkAll(){
        if(fname.length()==0){
            fname.setError("First Name is required");
            return false;
        }else{

        }
        if(lname.length()==0){
            lname.setError("Last Name is required");
            return false;
        }
        if(address.length()==0){
            address.setError("Address is required");
            return false;
        }
        if(city.length()==0){
            city.setError("city is required");
            return false;
        }
        if(pcode.length()==0){
            pcode.setError("postal code is required");
            return false;
        }
        if(email.length()==0){
            email.setError("email  is required");
            return false;
        }
        if(phno.length()==0){
            phno.setError("phone number  is required");
            return false;
        }

        return true;
    }
    public void nxtBtn(View view) {
        Intent intent = new Intent(this,deliveryOptions.class);
        startActivity(intent);
    }


    public void imgBtn(View view) {
        Intent intent = new Intent(this,customerDetails.class);
        startActivity(intent);
    }
}