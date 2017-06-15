package com.example.android.sfwhf1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity implements View.OnClickListener{
    private Button buttonDailyVital;
    private Button buttonIntakes;
    private Button buttonWorkout;
    private Button buttonLocation;
    private Button buttonGame;
    private Button buttonUserProfile;
    private TextView textViewSignout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDailyVital = (Button) findViewById(R.id.buttonDailyVital);
        buttonIntakes = (Button) findViewById(R.id.buttonIntakes);
        buttonWorkout = (Button) findViewById(R.id.buttonWorkout);
        buttonLocation = (Button) findViewById(R.id.buttonLocation);
        buttonGame = (Button) findViewById(R.id.buttonGame);
        buttonUserProfile = (Button) findViewById(R.id.buttonUserProfile);
        textViewSignout = (TextView) findViewById(R.id.textViewSignout);
        firebaseAuth = FirebaseAuth.getInstance();
        buttonDailyVital.setOnClickListener(this);
        buttonIntakes.setOnClickListener(this);
        buttonWorkout.setOnClickListener(this);
        buttonLocation.setOnClickListener(this);
        buttonGame.setOnClickListener(this);
        buttonUserProfile.setOnClickListener(this);
        textViewSignout.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if(v == buttonDailyVital){
            //go to dailyvital activity
        }
        if(v == buttonIntakes){
            //go to intakes activity
        }
        if(v == buttonWorkout){
            //go to workout activity
        }
        if(v == buttonLocation){
            //go to location activity
        }
        if(v == buttonGame){
            //go to game activity
        }
        if(v == buttonUserProfile){
            finish();
            Intent goToUserProfile = new Intent(Main.this, UserProfile.class);
            //goToUserProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goToUserProfile);
        }
        if(v == textViewSignout){
            firebaseAuth.signOut();
            Toast.makeText(this, "You successfully logout", Toast.LENGTH_SHORT).show();
            finish();
            Intent goToWelcom = new Intent(Main.this, Welcom.class);
            goToWelcom.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goToWelcom);
        }
    }
}
