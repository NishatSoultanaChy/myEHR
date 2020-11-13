package com.nishatsultana.myehr.Doctor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Model.Chamber;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import java.util.Calendar;

public class AddchambersActivity extends AppCompatActivity {

    EditText chamberName, chamberLocation, chamberHour;
    Button saveChamber;

    final Calendar myCalendar = Calendar.getInstance();
    private ProgressDialog LoadingBar;

    DatabaseReference databaseReference_Chamber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchambers);

        chamberName= findViewById(R.id.chamber_name);
        chamberLocation= findViewById(R.id.chamber_location);
        chamberHour= findViewById(R.id.visiting_hour);

        saveChamber = findViewById(R.id.btn_save_chamber);
        databaseReference_Chamber = FirebaseDatabase.getInstance().getReference().child("Chambers");





        /// saving symptomps to database
        saveChamber.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChamber_now();
            }
        });



    }

    private void saveChamber_now() {

        String chamber= chamberName.getText().toString();
        String location = chamberLocation.getText().toString();
        String visitingHour = chamberHour.getText().toString();


        if (TextUtils.isEmpty(chamber)){

            Toast.makeText(this, "Please enter the clinic/hospital name...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(location)){

            Toast.makeText(this, "Please write your chamber location...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(visitingHour)){

            Toast.makeText(this, "Please write your visiting hour...", Toast.LENGTH_SHORT).show();
        }

        else
        {

           // LoadingBar.setTitle("Saving Chamber");
           // LoadingBar.setMessage("Please wait...");
            //LoadingBar.setCanceledOnTouchOutside(false);
           // LoadingBar.show();

            save(chamber,location,visitingHour);
        }
    }

    private void save(String chamber, String location, String visitingHour) {

        String id = databaseReference_Chamber.push().getKey();

        final String phone = Prevalent.CurrentOnlineUser.getPhone();
        Chamber obj = new Chamber(chamber,location,visitingHour);

        databaseReference_Chamber.child(phone).child(id).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Chamber Saved",Toast.LENGTH_LONG).show();
            }
        });

        //LoadingBar.dismiss();
    }
}