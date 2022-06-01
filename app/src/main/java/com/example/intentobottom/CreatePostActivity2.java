package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.intentobottom.R;
import com.huynn109.IncreaseDecreaseButton;

import Beans.Post2;

public class CreatePostActivity2 extends AppCompatActivity {

    private Button btnContinuar2;
    private EditText edtArea, edtPrice, edtTitle, edtDescription;
    private RadioButton radioBtn;
    private IncreaseDecreaseButton btnRooms, btnBath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post2);

        // To retrieve object in second Activity
        Post2 post= (Post2) getIntent().getSerializableExtra("post");

        btnContinuar2=findViewById(R.id.btnContinuar2);
        edtArea=findViewById(R.id.edtArea);
        edtPrice=findViewById(R.id.edtPrice);
        edtTitle=findViewById(R.id.edtTitle);
        edtDescription=findViewById(R.id.edtDescription);
        radioBtn=findViewById(R.id.radio_one);
        btnRooms=findViewById(R.id.cantHab);
        btnBath=findViewById(R.id.cantBanos);

        btnContinuar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post.setRoomQuantity(btnRooms.getCurrentNumber());
                post.setBathroomQuantity(btnBath.getCurrentNumber());
                float price=Float.parseFloat(edtPrice.getText().toString());
                post.setPrice(price);
                post.setTitle(edtTitle.getText().toString());
                post.setDescription(edtDescription.getText().toString());

                Intent i=new Intent(getApplicationContext(),CreatePostActivity3.class);

                i.putExtra("post", post);

                startActivity(i);
            }
        });

    }
}