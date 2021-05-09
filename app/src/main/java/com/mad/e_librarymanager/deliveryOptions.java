package com.mad.e_librarymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class deliveryOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_options);
    }

    public void conBtn(View view) {
        Intent intent = new Intent(this,report_details.class);
        startActivity(intent);
    }
}