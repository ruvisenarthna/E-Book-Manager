package com.mad.e_librarymanager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class report_details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
    }
    public void greportBtn(View view) {
        Intent intent = new Intent(this,report.class);
        startActivity(intent);
    }
}