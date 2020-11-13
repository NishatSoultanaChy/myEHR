package com.nishatsultana.myehr.Doctor;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.LoginActivity;
import com.nishatsultana.myehr.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class RegisterAsDoctorActivity extends AppCompatActivity {

    private EditText InputFullname,InputLicenceNo, InputEmail, InputPhone, InputDateofBirth, InputPassword,  InputConfirmpassword , InputSpeciality;

    private Spinner InputBloodgroup;
    private Spinner InputGender;

    private Button NextSubmitBtn;
    private ProgressDialog LoadingBar;

    String userRole;

    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as_doctor);
        //Intent intent = getIntent();
        //userRole = intent.getStringExtra("userRole");


        InputFullname = findViewById(R.id.fullNameDoc);
        InputLicenceNo = findViewById(R.id.LicenceNo_Doc);

        InputEmail = findViewById(R.id.emailDoc);
        InputPhone = findViewById(R.id.phoneNoDoc);
        InputDateofBirth = findViewById(R.id.dateofBirthDoc);
        InputSpeciality= findViewById(R.id.SpecialityDoc);

        InputGender = findViewById(R.id.GenderSpinnerDoc);

        InputBloodgroup = findViewById(R.id.bloodGroupPSpinnerDoc);

        //password
        InputPassword = findViewById(R.id.passwordDoc);
        InputConfirmpassword = findViewById(R.id.confirmPasswordDoc);

        NextSubmitBtn = findViewById(R.id.registerDocNext);

        LoadingBar = new ProgressDialog(this);







        //Gender
        List<String>gender = new ArrayList<>();
        gender.add(0,"Gender");
        gender.add(1,"Male");
        gender.add(2,"Female");
        gender.add(3,"Others");
// Style and populate the spinner
        ArrayAdapter<String>dataAdapter1;
        dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,gender);
//Dropdown layout style
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        InputGender.setAdapter(dataAdapter1);

        List<String>bloodgroups = new ArrayList<>();
        bloodgroups.add(0,"Blood Group");
        bloodgroups.add(1,"A+");
        bloodgroups.add(2,"A-");
        bloodgroups.add(3,"B+");
        bloodgroups.add(4,"B-");
        bloodgroups.add(5,"O+");
        bloodgroups.add(6,"O-");
        bloodgroups.add(7,"AB+");
        bloodgroups.add(8,"AB-");
        bloodgroups.add(9,"N/A");
// Style and populate the spinner
        ArrayAdapter<String>dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,bloodgroups);
//Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        InputBloodgroup.setAdapter(dataAdapter);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        InputDateofBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterAsDoctorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        NextSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        InputDateofBirth.setText(sdf.format(myCalendar.getTime()));
    }

    private void createAccount(){

        String fullname = InputFullname.getText().toString();
        String licenceno = InputLicenceNo.getText().toString();
        String speciality = InputSpeciality.getText().toString();
        String email = InputEmail.getText().toString().trim();
        String phone = InputPhone.getText().toString().trim();
        String dateofBirth= InputDateofBirth.getText().toString();
        String gender = InputGender.getSelectedItem().toString();
        String bloodgroup = InputBloodgroup.getSelectedItem().toString();
        String password= InputPassword.getText().toString();
        String confirmpassword = InputConfirmpassword.getText().toString();
        String role = "doctor";


        if (TextUtils.isEmpty(fullname)){

            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(licenceno)){

            Toast.makeText(this, "Please write your licence number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(speciality)){

            Toast.makeText(this, "Please write your speciality...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(phone)){

            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dateofBirth)){

            Toast.makeText(this, "Please write your date of birth...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(gender)){

            Toast.makeText(this, "Please enter your blood group...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(bloodgroup)){

            Toast.makeText(this, "Please enter your blood group...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(confirmpassword)){

            Toast.makeText(this, "Please confirm password...", Toast.LENGTH_SHORT).show();
        }

        else
        {

            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("Please wait, while we are checking the credentials...");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            ValidatePhoneNumber(fullname, licenceno, speciality, phone, email, dateofBirth, gender, bloodgroup, password, role);
        }

    }






    private void ValidatePhoneNumber(final String fullname, final String licenceno, final String speciality, final String phone, final String email,
                                     final String dateofBirth, final String gender, final String bloodgroup, final String password,
                                     final String role)
    {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String , Object> userDataMap = new HashMap<>();
                    final HashMap<String , Object> userDataMap2 = new HashMap<>();

                    userDataMap.put("phone", phone);
                    userDataMap.put("password", password);
                    userDataMap.put("fullname", fullname);
                    userDataMap.put("email", email);
                    userDataMap.put("dateofBirth", dateofBirth);
                    userDataMap.put("gender", gender);
                    userDataMap.put("bloodgroup", bloodgroup);
                    userDataMap.put("role", role);

                    userDataMap2.put("phone", phone);
                    userDataMap2.put("fullname", fullname);
                    userDataMap2.put("licenceno", licenceno);
                    userDataMap2.put("speciality", speciality);
                    userDataMap2.put("email", email);
                    userDataMap2.put("dateofBirth", dateofBirth);
                    userDataMap2.put("gender", gender);
                    userDataMap2.put("bloodgroup", bloodgroup);

                    Rootref.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {

                                        Rootref.child("Doctors").child(phone).updateChildren(userDataMap2)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText(RegisterAsDoctorActivity.this, "Congratulatins! Your Account has been created!", Toast.LENGTH_SHORT).show();
                                                            LoadingBar.dismiss();

                                                            Intent i = new Intent(RegisterAsDoctorActivity.this, LoginActivity.class);
                                                            startActivity(i);
                                                        }

                                                        else
                                                        {
                                                            LoadingBar.dismiss();
                                                            Toast.makeText(RegisterAsDoctorActivity.this, "Network Error:Please try again...", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }
                                                });
                                    }

                                    else
                                    {
                                        LoadingBar.dismiss();
                                        Toast.makeText(RegisterAsDoctorActivity.this, "Network Error:Please try again...", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });




                }

                else
                {
                    Toast.makeText(RegisterAsDoctorActivity.this, "This "+ phone + "already exist", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterAsDoctorActivity.this, "Please try to register using another phone number", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(RegisterAsDoctorActivity.this, RegisterAsDoctorActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RegisterAsDoctorActivity.this, "Network Error:Please try again...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

