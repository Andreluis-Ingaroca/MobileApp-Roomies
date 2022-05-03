package com.example.intentobottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity2 extends AppCompatActivity {

    Button btnSeleccionArrendador, btnSeleccionArrendatario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        btnSeleccionArrendador=findViewById(R.id.btnSeleccionArrendador);
        btnSeleccionArrendatario=findViewById(R.id.btnSeleccionArrendatario);

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
                Intent i = new Intent(getApplicationContext(), RegisterActivity3.class);
                startActivity(i);
            }
        });
    }
}