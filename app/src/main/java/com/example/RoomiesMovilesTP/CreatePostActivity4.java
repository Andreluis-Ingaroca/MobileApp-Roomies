package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Beans.Post2;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePostActivity4 extends AppCompatActivity {

    Button btnPublicacion;

    //Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post4);

        btnPublicacion=findViewById(R.id.btnPublicacion);

        int postId= (int) getIntent().getSerializableExtra("postId");
        int userId= (int) getIntent().getSerializableExtra("userId");
        System.out.println("Vista 4, PostId:");
        System.out.println(postId);

        btnPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("value",1);
                i.putExtra("postId",postId);
                i.putExtra("userId",userId);
                startActivity(i);
            }
        });

    }


}