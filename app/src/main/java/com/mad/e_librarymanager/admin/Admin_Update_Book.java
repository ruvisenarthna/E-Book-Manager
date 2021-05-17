package com.mad.e_librarymanager.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mad.e_librarymanager.R;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Admin_Update_Book extends AppCompatActivity {

    EditText mTextTitle, mTextAuthor, mTextPrice;
    Button btnSubmit, btnReset;
    ProgressDialog pd;

    FirebaseFirestore db;

    String pId, pTitle, pAuthor, pPrice;

    //veriable for validation
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__update__book);

        //initialized the view items variables
        mTextTitle = findViewById(R.id.book_add_title);
        mTextAuthor = findViewById(R.id.book_add_author);
        mTextPrice = findViewById(R.id.book_add_price);
        btnSubmit = findViewById(R.id.btn_add_submit);
        btnReset = findViewById(R.id.btn_add_reset);

        //initialized variable for validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //add validation for input feild
        awesomeValidation.addValidation(this,R.id.book_add_title, RegexTemplate.NOT_EMPTY,R.string.invalid_title);
        awesomeValidation.addValidation(this,R.id.book_add_author, RegexTemplate.NOT_EMPTY,R.string.invalid_author);
        awesomeValidation.addValidation(this,R.id.book_add_price, RegexTemplate.NOT_EMPTY,R.string.invalid_price);
        awesomeValidation.addValidation(this,R.id.book_add_price,RegexTemplate.TELEPHONE,R.string.invalid_number);


        // get data from intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            //get the data
            pId = bundle.getString("pId");
            pTitle = bundle.getString("pTitle");
            pAuthor = bundle.getString("pAuthor");
            pPrice = bundle.getString("pPrice");

            //set the data
            mTextTitle.setText(pTitle);
            mTextAuthor.setText(pAuthor);
            mTextPrice.setText(pPrice);

        }

        //progress dialog
        pd = new ProgressDialog(this);

        //Firestore
        db = FirebaseFirestore.getInstance();

        //click button to upload data
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation before submit
                if (awesomeValidation.validate()) {

                    //get the update values from user
                    String id = pId;
                    String title = mTextTitle.getText().toString().trim();
                    String author = mTextAuthor.getText().toString().trim();
                    String price = mTextPrice.getText().toString().trim();
                    // function to update the data
                    updateData(id, title, author, price);
                }else{
                    Toast.makeText(Admin_Update_Book.this,"Validation Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextTitle.setText("");
                mTextAuthor.setText("");
                mTextPrice.setText("");
            }
        });
    }

    private void updateData(String id,String title, String author, String price) {
        //set title to progress bar
        pd.setTitle("Updating data");

        pd.show();

        db.collection("Documents").document(id)
                .update("title",title,"search",title.toLowerCase(),"author",author,"price",price)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        pd.dismiss();

                        Toast.makeText(Admin_Update_Book.this,"Book Details Updated Successfully",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(Admin_Update_Book.this,Admin_View_Books.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(Admin_Update_Book.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void uploadData(String title, String author, String price) {


        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("title",title);
        doc.put("author",author);
        doc.put("price",price);

        //add data to firebase
        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(Admin_Update_Book.this,"Book Details added Successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(Admin_Update_Book.this,Admin_View_Books.class));
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(Admin_Update_Book.this, e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}