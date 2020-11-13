package com.nishatsultana.myehr.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.R;

public class MyHealthDocActivity extends AppCompatActivity {

    private Button btn_prescriptions, btn_reports, btn_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health_doc);

        btn_prescriptions = findViewById(R.id.btn_nav_prescriptions);
        btn_reports = findViewById(R.id.btn_nav_my_reports);
        btn_history = findViewById(R.id.btn_nav_History);


        btn_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyHealthDocActivity.this, "Btn is clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyHealthDocActivity.this, PatientReportList.class));
            }
        });
    }
}