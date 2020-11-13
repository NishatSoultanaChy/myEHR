package com.nishatsultana.myehr.Patient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.R;

public class SupportActivity extends AppCompatActivity {

    Button bkash_paymnt, nogod_paymnt, rocket_paymnt;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        bkash_paymnt = findViewById(R.id.btn_bkash_paymnt);
        nogod_paymnt = findViewById(R.id.btn_nogod_paymnt);
        rocket_paymnt = findViewById(R.id.btn_rocket_paymnt);

        bkash_paymnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupportActivity.this, "bkash payment is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        nogod_paymnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupportActivity.this, "Nogod payment is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        rocket_paymnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SupportActivity.this, "Rocket payment is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}