package com.example.intentobottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreatePostActivity4 extends AppCompatActivity {

    Button btnPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post4);

        btnPublicacion=findViewById(R.id.btnPublicacion);

        btnPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value=1;
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("id", value);
                startActivity(i);
            }
        });
    }
}