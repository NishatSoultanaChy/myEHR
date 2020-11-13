package com.nishatsultana.myehr.Patient;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSymptomsActivity extends AppCompatActivity {

    EditText symptomDate, symptom_desc;
    Button saveSymptom;

    final Calendar myCalendar = Calendar.getInstance();
    private ProgressDialog LoadingBar;

    DatabaseReference databaseReference_symp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

        symptomDate = findViewById(R.id.edit_symptom_date);
        symptom_desc= findViewById(R.id.edit_symptom_description);

        saveSymptom = findViewById(R.id.btn_save_symptom);
        databaseReference_symp = FirebaseDatabase.getInstance().getReference().child("Symptoms");


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
        symptomDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSymptomsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /// saving symptomps to database
        saveSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSymptom_now();
            }
        });

    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        symptomDate.setText(sdf.format(myCalendar.getTime()));
    }


// updating symptom database
    private void saveSymptom_now() {

        String dateofSymptom= symptomDate.getText().toString();
        String symptomDescription = symptom_desc.getText().toString();

        if (TextUtils.isEmpty(dateofSymptom)){

            Toast.makeText(this, "Please enter the date...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(symptomDescription)){

            Toast.makeText(this, "Please write your about your stmptoms...", Toast.LENGTH_SHORT).show();
        }

        else
        {

            //LoadingBar.setTitle("Save Symptoms");
            //LoadingBar.setMessage("Please wait...");
           // LoadingBar.setCanceledOnTouchOutside(false);
           //LoadingBar.show();

            save(dateofSymptom,symptomDescription);
        }
    }

    private void save(String dateofSymptom, String symptomDescription) {

        String id = databaseReference_symp.push().getKey();

        final String phone = Prevalent.CurrentOnlineUser.getPhone();
        Symptoms obj = new Symptoms(dateofSymptom, symptomDescription);

        databaseReference_symp.child(phone).child(id).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Symptom Saved",Toast.LENGTH_LONG).show();
            }
        });

       // LoadingBar.dismiss();



    }

}
