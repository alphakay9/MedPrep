package com.example.medprep.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.medprep.model.IssueInfo;
import com.example.medprep.R;

import java.util.Objects;

public class DetailedDiagnosisActivity extends AppCompatActivity {
    IssueInfo issueInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed_diagnosis);

        issueInfo = getIntent().getBundleExtra("des").getParcelable("Info");

        TextView name = findViewById(R.id.dName);
        TextView profname = findViewById(R.id.profName);
        TextView short_des = findViewById(R.id.short_des);
        TextView descr = findViewById(R.id.description);
        TextView our_sym = findViewById(R.id.oucr_sym);
        TextView treatment =findViewById(R.id.treatment);
        TextView possible_syms = findViewById(R.id.possible_syms);

        name.setText(issueInfo.getName());
        profname.setText("Professional Name ("+issueInfo.getProfName()+")");
        short_des.setText(issueInfo.getDescriptionShort());
        descr.setText(issueInfo.getDescription());
        our_sym.setText(issueInfo.getMedicalCondition());
        treatment.setText(issueInfo.getTreatmentDescription());
        possible_syms.setText(issueInfo.getPossibleSymptoms());
    }
}
