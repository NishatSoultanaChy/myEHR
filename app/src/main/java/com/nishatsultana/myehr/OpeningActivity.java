package com.nishatsultana.myehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        Button btn_reg = findViewById(R.id.join_us);
        Button btn_login = findViewById(R.id.login1);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OpeningActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OpeningActivity.this, ChooseUserTypeActivity.class);
                startActivity(i);
            }
        });
    }
}
