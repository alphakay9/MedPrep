package com.example.medprep.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.medprep.activity.AddProfileFragment;
import com.example.medprep.R;
import com.example.medprep.helpers.helpers.GlobalVariables;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener,AddProfileFragment.AddProfileFragmentListener {

    private FirebaseAuth mFirebaseAuth;
    private TextView mTvEmail;

    private Button email_a_doc;
    private Button nearby_clinics;
    Button chatbot;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        mTvEmail = findViewById(R.id.tv_email);
        mFirebaseAuth = FirebaseAuth.getInstance();

        email_a_doc = (Button)findViewById(R.id.email_a_doc);

        chatbot = findViewById(R.id.chatbot);
        chatbot.setOnClickListener(this);

        //getSupportFragmentManager().beginTransaction().add(R.id.fg_add_profile, new AddProfileFragment()).commit();


        Button button = findViewById(R.id.symptomasd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, SymptomCheckingActivity.class));
            }
        });

        email_a_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Consult a doctor over email.");
                builder.setMessage("Have Queries that we cannot solve? Ask a doctor.");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL,new String[]{"dr.chiragpatilmedprep@gmail.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT,"Patient Name: ");
                        i.putExtra(Intent.EXTRA_TEXT,"Please provide your details like Name, Number, Symptoms or any other health related problems you are facing and delete this content before sending email. The doctor will contact you soon.");

                        startActivity(Intent.createChooser(i,"Send mail.."));

                        dialog.dismiss();

                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        nearby_clinics = (Button) findViewById(R.id.nearby_clinics);
        nearby_clinics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapsActivity();
            }
        });

        /*chatbot = (Button) findViewById(R.id.chatbot);
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddProfileFragment();
            }
        });*/

    }
    
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            //there is some user logged in
            mTvEmail.setText(mFirebaseUser.getEmail());
        }else{
            startActivity(new Intent(this,PatientSignInActivity.class));
            finish();
        }
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,PatientSignInActivity.class));
        finish();
    }

    public void openMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /*public void openAddProfileFragment(){
        Intent intent = new Intent(this, AddProfileFragment.class);
        startActivity(intent);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.chatbot){
            //getSupportFragmentManager().beginTransaction().replace(R.id.FragmentInMenu, new AddProfileFragment()).commit();
            Intent intent = new Intent(MainActivity2.this, SplashActivity.class);
            startActivity(intent);
            finish();
            chatbot.setVisibility(View.GONE);
        }
    }

    public void callback(String result) {
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);

    }
}