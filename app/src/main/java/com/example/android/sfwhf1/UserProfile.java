package com.example.android.sfwhf1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserProfile extends AppCompatActivity {
    private EditText username;
    private EditText weight;
    private EditText birthdate;
    private FirebaseAuth firebaseAuth;
    private LinearLayout submitButton;
    private ProgressDialog progressDialog;




    //FIREBASE DATABASE FIELDS
    DatabaseReference homeDatabase;
    FirebaseAuth.AuthStateListener homeAuthListener;
    StorageReference homeStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        username = (EditText) findViewById(R.id.username);
        weight = (EditText) findViewById(R.id.weight);
        birthdate = (EditText) findViewById(R.id.birthdate);
        firebaseAuth = FirebaseAuth.getInstance();
        submitButton = (LinearLayout) findViewById(R.id.submit);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        homeAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    finish();
                    Intent backToHome = new Intent(UserProfile.this, Main.class);
                    backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(backToHome);
                }
            }
        };

        homeDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        homeStorage = FirebaseStorage.getInstance().getReference();

        submitButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Saving profile");
                progressDialog.setMessage("Progressing...");
                progressDialog.show();
                saveUserProfile();
                

            }
        });




    }

    private void saveUserProfile() {
        String userName = username.getText().toString().trim();
        //int userWeight = Integer.parseInt(weight.getText().toString());
        String uw = weight.getText().toString().trim();
        String birthDate = birthdate.getText().toString().trim();
        int userWeight = 0;

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(uw) && !TextUtils.isEmpty(birthDate)) {
            userWeight = Integer.parseInt(uw);
            homeDatabase.child("username").setValue(userName);
            homeDatabase.child("weight").setValue(userWeight);
            homeDatabase.child("birthdate").setValue(birthDate);
            progressDialog.dismiss();

        }
        if( TextUtils.isEmpty(userName)) {
            Toast.makeText(UserProfile.this, "Please enter username", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(UserProfile.this, "Please enter correctly", Toast.LENGTH_SHORT).show();
        }

    }
}
