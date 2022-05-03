package com.example.intentobottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity3 extends AppCompatActivity {

    Button btnPlanGratuito, btnPlanPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        btnPlanGratuito=findViewById(R.id.btnPlanGratuito);
        btnPlanPremium=findViewById(R.id.btnPlanPremium);

        btnPlanGratuito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                startActivity(i);
            }
        });

        btnPlanPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                startActivity(i);
            }
        });
    }
}