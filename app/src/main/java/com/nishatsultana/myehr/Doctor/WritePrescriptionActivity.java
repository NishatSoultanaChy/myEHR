package com.nishatsultana.myehr.Doctor;

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

import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Patient.AddSymptomsActivity;
import com.nishatsultana.myehr.Prevalent.CurrentUser;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.nishatsultana.myehr.Prevalent.Prevalent.*;

public class WritePrescriptionActivity extends AppCompatActivity {

    TextView patientName, patientBirthdate;
    EditText prescriptionDate, prescriptionText;

    Button savePrescription;
    final Calendar myCalendar = Calendar.getInstance();
    private ProgressDialog LoadingBar;

    DatabaseReference databaseReference_presc;

    String phone, PatientName, PatientBirthdate;

    String doctorPhone;
    String doctorName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_prescription);

        phone  = getIntent().getStringExtra("keyPatientPhone");
        PatientName  = getIntent().getStringExtra("keyPatientName");
        PatientBirthdate = getIntent().getStringExtra("keyPatientBirth");



        patientBirthdate = findViewById(R.id.pres_ptnt_birthDate);
        patientName = findViewById(R.id.pres_ptnt_name);

        prescriptionDate= findViewById(R.id.edit_presc_date);
        prescriptionText= findViewById(R.id.write_presc_here);

        savePrescription = findViewById(R.id.btn_save_presc);


        patientName.setText("Patient's Name: "+PatientName);
        patientBirthdate.setText("Patient's Birth Date" +PatientBirthdate);

        databaseReference_presc = FirebaseDatabase.getInstance().getReference().child("Prescriptions");




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
        prescriptionDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(WritePrescriptionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /// saving prescription to database
        savePrescription.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        savePrescription_now();
                    }
                });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        prescriptionDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void savePrescription_now() {

        String prescDate= prescriptionDate.getText().toString();
        String prescText = prescriptionText.getText().toString();


        if (TextUtils.isEmpty(prescDate)){

            Toast.makeText(this, "Please enter the date...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(prescText)){

            Toast.makeText(this, "Please prescribe...", Toast.LENGTH_SHORT).show();
        }
        else
        {

            // LoadingBar.setTitle("Saving Chamber");
            // LoadingBar.setMessage("Please wait...");
            //LoadingBar.setCanceledOnTouchOutside(false);
            // LoadingBar.show();

            save(prescDate,prescText);
        }
    }

    private void save(String prescDate, String prescText) {

        String id = databaseReference_presc.push().getKey();

        doctorPhone = CurrentUser.phoneUser;
        doctorName = CurrentUser.fullNameUser;


        Prescription obj = new Prescription(doctorName,doctorPhone,prescDate,prescText);

        databaseReference_presc.child(""+phone).child(""+id).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Prescription Saved",Toast.LENGTH_LONG).show();
            }
        });

    }
}