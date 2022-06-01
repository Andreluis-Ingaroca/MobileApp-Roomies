package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RegisterActivity2 extends AppCompatActivity {

    Button btnSeleccionArrendador, btnSeleccionArrendatario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        btnSeleccionArrendador=findViewById(R.id.btnSeleccionArrendador);
        btnSeleccionArrendatario=findViewById(R.id.btnSeleccionArrendatario);

/*        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.of(2016, 8, 19);
        System.out.println("fecha1"+localDate);
        Date date = Date
                .from(localDate.atStartOfDay(defaultZoneId).toInstant());

        System.out.println("fecha"+date);*/

        btnSeleccionArrendador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity3.class);
                startActivity(i);
            }
        });

        btnSeleccionArrendatario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}