package com.mad.e_librarymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class shipment extends AppCompatActivity {

    private EditText fname,lname,address,city,pcode,email,phno;
    private Button add,delete,edit;

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