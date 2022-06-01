package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnPlan3Landlord, btnPlan4Landlord,btnPlan5Landlord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnPlan3Landlord= findViewById(R.id.btnPlan3Landlord);
        btnPlan4Landlord= findViewById(R.id.btnPlan4Landlord);
        btnPlan5Landlord= findViewById(R.id.btnPlan5Landlord);

        //Arrendador
        int userType=2;

        btnPlan3Landlord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int planId= 3;
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                i.putExtra("userType", userType);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });

        btnPlan4Landlord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int planId= 4;
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                i.putExtra("userType", userType);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });
        btnPlan5Landlord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int planId= 5;
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                i.putExtra("userType", userType);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });
        /*
        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity2.class);
                startActivity(i);
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });*/
    }
}