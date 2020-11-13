package com.nishatsultana.myehr.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPatientProfileActivity extends AppCompatActivity {

    TextView txt_patient_name, txt_patient_phone, txt_patient_gender, txt_patient_birthDate;
    Button btn_syp_doc, btn_HT_doc, btn_rep_doc, btn_prev_presc_doc, btn_progress_report_doc, btn_new_presc_doc;
    private DatabaseReference mDatabase;
   // Intent intent = getIntent();
    //private String phone =intent.getExtras().getString("key");// getString("key");
    //Bundle bundle=getIntent().getExtras();
    String phone_P , fullname, dateofBirth, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient_profile);
        mDatabase = FirebaseDatabase.getInstance().getReference("Patients");

        phone_P  = getIntent().getStringExtra("key");
        txt_patient_name = findViewById(R.id.tv_name_patnt);
        txt_patient_phone = findViewById(R.id.tv_phone_patnt);
        txt_patient_gender= findViewById(R.id.tv_gender_patnt);
        txt_patient_birthDate = findViewById(R.id.tv_bDate_patnt);

        btn_syp_doc= findViewById(R.id.btn_symptoms_doc);
        btn_HT_doc = findViewById(R.id.btn_health_track_doc);
        btn_rep_doc = findViewById(R.id.btn_reports_doc);
        btn_prev_presc_doc  = findViewById(R.id.btn_prv_prescrptns_doc);
        btn_progress_report_doc = findViewById(R.id.btn_progress_report_doc);
        //btn_new_presc_doc = findViewById(R.id.btn_new_pres_doc);*/

        getPatientInfo();// method for retrieving patient data from database

        //butoon see symptoms
        btn_syp_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPatientProfileActivity.this,MyPatientSymptomActivity.class);
                i.putExtra("keyPhone",phone_P);
                startActivity(i);
            }
        });

        btn_rep_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MyPatientProfileActivity.this,MyPatientReportsActivity.class);
                j.putExtra("keyPhone",phone_P);
                startActivity(j);
            }
        });
        btn_progress_report_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MyPatientProfileActivity.this,ProgresReportActivity.class);
                k.putExtra("keyPatientPhone",phone_P);
                k.putExtra("keyPatientBirth",dateofBirth);
                k.putExtra("keyPatientName",fullname);

                startActivity(k);
            }
        });

        btn_prev_presc_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MyPatientProfileActivity.this, PreviousPrescriptionsActivity.class);
                k.putExtra("keyPatientPhone",phone_P);
                k.putExtra("keyPatientBirth",dateofBirth);
                k.putExtra("keyPatientName",fullname);

                startActivity(k);
            }
        });
    }
    // method for retrieving patient data from database
    private void getPatientInfo() {
        //mDatabase = FirebaseDatabase.getInstance().getReference("Patients").child(phone);
        mDatabase.child(phone_P).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fullname =  snapshot.child("fullname").getValue().toString();
                gender =  snapshot.child("gender").getValue().toString();
                dateofBirth =  snapshot.child("dateofBirth").getValue().toString();

                txt_patient_name.setText(fullname);
                txt_patient_phone.setText(phone_P);
                txt_patient_gender.setText(gender);
                txt_patient_birthDate.setText(dateofBirth);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}