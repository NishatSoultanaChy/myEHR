package com.nishatsultana.myehr;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nishatsultana.myehr.Doctor.RegisterAsDoctorActivity;
import com.nishatsultana.myehr.Patient.RegisterAsPatientActivity;


public class ChooseUserTypeActivity extends AppCompatActivity {

    Button b_reg_patient,b_reg_doctor,b_reg_nurse;
    Button b_login2;
    String user_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_type);


        b_reg_patient= findViewById(R.id.b_reg_patient);
        b_reg_doctor= findViewById(R.id.b_reg_doctor);
        b_login2= findViewById(R.id.login2);




        //--------------------------register as patient button---
        b_reg_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_role = "Patient";

                Intent intent = new Intent(ChooseUserTypeActivity.this, RegisterAsPatientActivity.class);
                intent.putExtra("userRole", user_role);

                startActivity(intent);

            }
        });


        //--------------------------register as doctor button---


        b_reg_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_role = "Doctor";


                Intent intent = new Intent(ChooseUserTypeActivity.this, RegisterAsDoctorActivity.class);
                intent.putExtra("userRole", user_role);
                startActivity(intent);

            }
        });





        //--------------------------if forgot password button

        b_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent b_post_loging = new Intent(ChooseUserTypeActivity.this,LoginActivity.class);
                startActivity(b_post_loging);

            }
        });

    }
}
