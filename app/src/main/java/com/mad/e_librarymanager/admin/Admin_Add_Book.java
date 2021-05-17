package com.mad.e_librarymanager.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Admin_Add_Book extends AppCompatActivity {

    EditText mTextTitle, mTextAuthor, mTextPrice;
    Button btnSubmit, btnReset;
    ProgressDialog pd;

    FirebaseFirestore db;

   static int isSuccess = 0;

    //veriable for validation
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__book);

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

                String title = mTextTitle.getText().toString().trim();
                String author = mTextAuthor.getText().toString().trim();
                String price = "Rs " + mTextPrice.getText().toString().trim();


                uploadData(title, author, price);
            }else{
                    Toast.makeText(Admin_Add_Book.this,"Validation Failed",Toast.LENGTH_LONG).show();
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

    public int uploadData(String title, String author, String price) {



            //set title to progress bar
            pd.setTitle("Adding data to FireBase");

            pd.show();

            String id = UUID.randomUUID().toString();

            Map<String, Object> doc = new HashMap<>();
            doc.put("id",id);
            doc.put("title",title);
            doc.put("search", title.toLowerCase());
            doc.put("author",author);
            doc.put("price",price);
            isSuccess = 1;
            //add data to firebase
            db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    pd.dismiss();
                    Toast.makeText(Admin_Add_Book.this,"Book Details added Successfully",Toast.LENGTH_LONG).show();
                    isSuccess = 1;
                    startActivity(new Intent(Admin_Add_Book.this,Admin_View_Books.class));
                    finish();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(Admin_Add_Book.this, e.getMessage(),Toast.LENGTH_LONG).show();
                            isSuccess = 0;
                        }
                    });


    return isSuccess;
    }

}