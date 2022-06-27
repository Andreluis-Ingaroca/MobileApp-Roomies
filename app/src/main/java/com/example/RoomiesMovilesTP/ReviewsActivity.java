package com.example.RoomiesMovilesTP;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentobottom.R;

import java.util.List;

import Beans.Post;
import Beans.Review;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewsActivity extends AppCompatActivity {
    RecyclerView recyclerReview;
    List<Review> lista;
    ReviewAdapter adapter;
    TextView txtNoReviews;
    Activity context;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        context = this;
        Post post = (Post) getIntent().getSerializableExtra("Post");

        txtNoReviews = findViewById(R.id.txtNoReviews);
        recyclerReview = findViewById(R.id.recyclerReviews);
        recyclerReview.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));
        id = post.getId();
        getReviews();
    }

    private void getReviews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolder = retrofit.create(PlaceHolderApi.class);

        Call<List<Review>> inter = placeHolder.getReviews(id);

        inter.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.body().size() != 0) {
                    lista = response.body();
                    adapter = new ReviewAdapter(lista, context);
                    recyclerReview.setAdapter(adapter);
                    System.out.println(lista.size() + " cantidad de reviews");
                }
                else {
                    txtNoReviews.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}