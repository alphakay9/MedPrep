package com.example.medprep.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medprep.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final int RC_PHOTO_PICKER=1;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.main_progressbaR);


        /*Button button = findViewById(R.id.symptomasd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SymptomCheckingActivity.class));
            }
        });*/

        Button patient = findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent studentevent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(studentevent);

                }
                else{
                    Intent studentevent = new Intent(MainActivity.this, PatientSignInActivity.class);
                    startActivity(studentevent);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onatrsumr","ya");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}