package com.nishatsultana.myehr.Doctor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class update_doctor_info extends AppCompatActivity {

    ImageView imgDoc;
    private EditText InputFullname,InputLicenceNo, InputEmail, InputPhone, InputDateofBirth, InputPassword,  InputConfirmpassword , InputSpeciality;

    private Spinner InputBloodgroup;
    private Spinner InputGender;

    private Button NextSubmitBtn;
    private ProgressDialog LoadingBar;

    final Calendar myCalendar = Calendar.getInstance();

    private DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor_info);


        InputFullname = findViewById(R.id.edit_fullName_doc);
        InputLicenceNo = findViewById(R.id.edit_LicenceNo_Doc);

        InputEmail = findViewById(R.id.edit_emailDoc);
        InputPhone = findViewById(R.id.edit_phoneNoDoc);
        InputDateofBirth = findViewById(R.id.edit_datePicker_doc);
        InputSpeciality= findViewById(R.id.edit_SpecialityDoc);

        InputGender = findViewById(R.id.edit_GenderSpinnerDoc);

        InputBloodgroup = findViewById(R.id.edit_bloodGroupPSpinnerDoc);

        //password
        InputPassword = findViewById(R.id.edit_passwordDoc);
        InputConfirmpassword = findViewById(R.id.edit_confirmPasswordDoc);

        NextSubmitBtn = findViewById(R.id.updateDctrInfo);

        LoadingBar = new ProgressDialog(this);

        mReference = FirebaseDatabase.getInstance().getReference().child("Doctors");




    }
}