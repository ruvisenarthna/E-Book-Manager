package com.mad.e_librarymanager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cardDetails extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

    }
    public void backBtn1(View view) {
        Intent intent = new Intent(this,customerDetails.class);
        startActivity(intent);
    }
}