package com.example.intentobottom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostActivity extends AppCompatActivity {

    Button btnContactar;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btnContactar = findViewById(R.id.btnContactar);
        mDialog = new Dialog(this);

        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.contact_popup);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                mDialog.show();
            }
        });
    }
}