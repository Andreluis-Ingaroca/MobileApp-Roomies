package com.example.RoomiesMovilesTP;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intentobottom.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import Beans.Post;
import Beans.Profile;
import Beans.Review;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    TextView txtDashboardPostQnt, txtDashboardPromedio, txtUserDashboard;
    float denominadorPosts = 1;
    float reviewQnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtDashboardPostQnt = findViewById(R.id.txtUserDashboardPostQnt);
        txtDashboardPromedio = findViewById(R.id.txtDashboardPromedio);
        txtUserDashboard = findViewById(R.id.txtUserDashboard);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<Profile> inter =placeholder.getProfile(VariablesGlobales.userId);

        inter.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                txtUserDashboard.setText("Dashboard de " + response.body().getName() + " " + response.body().getLastName());
                int profileId = response.body().getId();
                getPostQuantity(profileId);
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println("Error: "+t.getCause());
            }
        });
    }

    private void getPostQuantity(int profileId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> inter = placeholder.getPostByLandlordId(profileId);

        inter.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.body().size() == 0) {
                    txtDashboardPostQnt.setText("0");
                    txtDashboardPromedio.setText("0");
                }
                else {
                    txtDashboardPostQnt.setText(response.body().size()+"");
                    denominadorPosts = response.body().size();
                    for(int i = 0; i < response.body().size(); i++) {
                        getPostReviews(response.body().get(i).getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void getPostReviews(int postId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Review>> inter = placeholder.getReviews(postId);

        inter.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                reviewQnt += response.body().size();
                txtDashboardPromedio.setText((float)reviewQnt/denominadorPosts + "");
                System.out.println("Numerador: "+reviewQnt);
                System.out.println("Denominador: " + denominadorPosts);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}