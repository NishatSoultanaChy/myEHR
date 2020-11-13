package com.nishatsultana.myehr.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Model.Progress;
import com.nishatsultana.myehr.Prevalent.CurrentUser;
import com.nishatsultana.myehr.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.nishatsultana.myehr.Prevalent.Prevalent.CurrentOnlineUser;

public class AddProgressActivity extends AppCompatActivity {

    TextView patientName_p, patientBirthdate_P;
    EditText progressDate, progressText;

    Button saveProgress;
    final Calendar myCalendar = Calendar.getInstance();

    DatabaseReference databaseReference_progress;

    String phone_P, PatientName_P, PatientBirthdate_P;

    String doctorPhone_P;
    String doctorName_P;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_progress);
        phone_P  = getIntent().getStringExtra("keyPatientPhone");
        PatientName_P  = getIntent().getStringExtra("keyPatientName");
        PatientBirthdate_P = getIntent().getStringExtra("keyPatientBirth");



        patientBirthdate_P = findViewById(R.id.progress_ptnt_birthDate);
        patientName_p= findViewById(R.id.progress_ptnt_name);

        progressDate= findViewById(R.id.edit_progress_date);
        progressText= findViewById(R.id.write_progress_here);

        saveProgress = findViewById(R.id.btn_save_progress);


        patientName_p.setText("Patient's Name: "+PatientName_P);
        patientBirthdate_P.setText("Patient's Birth Date" +PatientBirthdate_P);

        databaseReference_progress = FirebaseDatabase.getInstance().getReference().child("ProgressReport");




        /// date picker
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
        progressDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddProgressActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /// saving prescription to database
        saveProgress.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveProgress_now();
                    }
                });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        progressDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void saveProgress_now() {

        String progDate= progressDate.getText().toString();
        String progText = progressText.getText().toString();


        if (TextUtils.isEmpty(progDate)){

            Toast.makeText(this, "Please enter the date...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(progText)){

            Toast.makeText(this, "Please write progress...", Toast.LENGTH_SHORT).show();
        }
        else
        {

            // LoadingBar.setTitle("Saving Chamber");
            // LoadingBar.setMessage("Please wait...");
            //LoadingBar.setCanceledOnTouchOutside(false);
            // LoadingBar.show();

            save(progDate,progText);
        }
    }

    private void save(String progDate, String progText) {

        String id = databaseReference_progress.push().getKey();

        doctorPhone_P = CurrentUser.phoneUser;
        doctorName_P = CurrentUser.fullNameUser;


        Progress obj = new Progress(doctorName_P,doctorPhone_P,progDate, progText);

        databaseReference_progress.child(""+phone_P).child(""+id).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Progress Report Saved",Toast.LENGTH_LONG).show();
            }
        });

    }

}