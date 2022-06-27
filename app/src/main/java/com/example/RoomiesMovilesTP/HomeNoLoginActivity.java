package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intentobottom.R;

import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeNoLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGoToLogin;
    RecyclerView recycler;
    ListAdapterNoLogin adapter;

    private List<Post> dataFavoritePost;
    private ArrayList<Post> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_no_login);

        btnGoToLogin = findViewById(R.id.btnHomeNoLogin);

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        recycler = findViewById(R.id.recyclerDataNoLogin);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

        getPosts();
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> inter = placeholder.getPosts();

        inter.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> lista = response.body();
                data = new ArrayList<Post>();

                    for (Post po : lista) {
                        data.add(new Post(
                                po.getId(),
                                po.getTitle(),
                                po.getDescription(),
                                po.getAddress(),
                                po.getProvince(),
                                po.getDistrict(),
                                po.getPhoto(),
                                po.getDepartment(),
                                po.getPrice(),
                                po.getRoomQuantity(),
                                po.getBathroomQuantity(),
                                po.getPostDate(),
                                po.getLandlord(),
                                false));
                    }


                adapter = new ListAdapterNoLogin(data, getApplicationContext());

                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                });

                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}