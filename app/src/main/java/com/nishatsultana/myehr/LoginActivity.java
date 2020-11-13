package com.nishatsultana.myehr;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nishatsultana.myehr.Doctor.DoctorHomeActivity;
import com.nishatsultana.myehr.Model.Users;
import com.nishatsultana.myehr.Patient.PatientHomeActivity;
import com.nishatsultana.myehr.Prevalent.CurrentUser;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InputPhoneNumber2, InputPassword2;
    private Button LoginButton3;
    private ProgressDialog loadingBar3;
    private final String parentDbName =  "Users";
    private CheckBox chkBoxRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InputPhoneNumber2 = findViewById(R.id.phoneNoLogin);
        InputPassword2 = findViewById(R.id.passwordLogin);
        LoginButton3 = findViewById(R.id.login3);
        loadingBar3 = new ProgressDialog(this);

        chkBoxRememberMe= findViewById(R.id.remember_me_chkb);
        Paper.init(this);


        LoginButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }


    private void LoginUser(){

        String phoneNo = InputPhoneNumber2.getText().toString();
        String password = InputPassword2.getText().toString();

        if (TextUtils.isEmpty(phoneNo)){

            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingBar3.setTitle("Login Account");
            loadingBar3.setMessage("Please wait, while we are checking the credentials...");
            loadingBar3.setCanceledOnTouchOutside(false);
            loadingBar3.show();

            AllowAccessTOAccount(phoneNo, password);
        }

    }

    private void AllowAccessTOAccount(final String phone, final String password){

        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users userData= dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(userData.getPhone().equals(phone))
                    {
                        if(userData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this,"Logged in Successfully...", Toast.LENGTH_SHORT).show();

                            loadingBar3.dismiss();
                            Prevalent.CurrentOnlineUser = userData;

                            CurrentUser.fullNameUser = userData.getFullname();
                            CurrentUser.blodGroupUser = userData.getBloodgroup();
                            CurrentUser.dateofBirthUser = userData.getDateofBirth();
                            CurrentUser.emailUser = userData.getEmail();
                            CurrentUser.genderUser = userData.getGender();
                            CurrentUser.phoneUser = userData.getPhone();


                            if(userData.getRole().equals("doctor"))
                            {

                                Intent i = new Intent(LoginActivity.this, DoctorHomeActivity.class);
                                startActivity(i);
                            }

                            else if(userData.getRole().equals("patient"))
                            {
                                Intent i = new Intent(LoginActivity.this, PatientHomeActivity.class);
                                startActivity(i);
                            }
                        }

                        else
                        {
                            loadingBar3.dismiss();
                            Toast.makeText(LoginActivity.this,"Password is incorrect...", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                else {
                    Toast.makeText(LoginActivity.this, "Account with this "+ phone+ " number do not exist!", Toast.LENGTH_SHORT).show();
                    loadingBar3.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Network Error:Please try again...", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
