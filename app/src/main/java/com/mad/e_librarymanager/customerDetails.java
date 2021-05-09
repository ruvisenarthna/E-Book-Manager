package com.mad.e_librarymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class customerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
    }

    public void nxtBtn1(View view) {
        Intent intent = new Intent(this,cardDetails.class);
        startActivity(intent);
    }
}