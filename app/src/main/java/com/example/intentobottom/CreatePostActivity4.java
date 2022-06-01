package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

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

                // calling a method to post the data and passing our name and job.
                // postData(nameEdt.getText().toString(), jobEdt.getText().toString());

                int value=1;
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("id", value);
                startActivity(i);
            }
        });
    }
}