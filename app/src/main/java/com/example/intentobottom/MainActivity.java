package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.intentobottom.R;
import com.example.intentobottom.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import Beans.Profile;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    PlaceHolderApi placeHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            int value=extras.getInt("id");
            if(value==1){
                Intent i = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(i);
            }
        }

        //replaceFragment(new HomeFragment());

        //Obtener planId através del placeholder

        int userId = extras.getInt("userId");
        System.out.println("User Id: "+ userId);


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        placeHolderApi = retrofit.create(PlaceHolderApi.class);

        Call<Profile> call = placeHolderApi.getProfile(userId);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()){
                    System.out.println("Entro al on response");
                    int planId = response.body().getPlan().getId();
                    int profileId = response.body().getId();
                    System.out.println("Plan ID: "+planId);
                }
                replaceFragment(new HomeFragment());
            }
            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.create_post:
                    replaceFragment(new CreatePostFragment());
                    break;
                case R.id.roomie:
                    replaceFragment(new RoomieFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        System.out.println("llegó al replace fragment");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}