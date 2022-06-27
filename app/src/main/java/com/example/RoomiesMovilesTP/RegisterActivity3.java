package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

public class RegisterActivity3 extends AppCompatActivity {

    Button btnPlan1Leaseholder, btnPlan2Leaseholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        btnPlan1Leaseholder=findViewById(R.id.btnPlan1Leaseholder);
        btnPlan2Leaseholder=findViewById(R.id.btnPlan2Leaseholder);

        //Arrendatario
        int userType=1;

        btnPlan1Leaseholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int planId= 1;
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                i.putExtra("userType", userType);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });

        btnPlan2Leaseholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int planId= 2;
                Intent i = new Intent(getApplicationContext(), RegisterActivity4.class);
                i.putExtra("userType", userType);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });
    }
}