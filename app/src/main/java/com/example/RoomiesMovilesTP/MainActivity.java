package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intentobottom.R;
import com.example.intentobottom.databinding.ActivityMainBinding;
import com.example.intentobottom.databinding.ItemBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import Beans.Post;
import Beans.Post2;
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

    ConstraintLayout constraintLayout;

    int planId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        constraintLayout=findViewById(R.id.constraint_layout);
        ConstraintSet constraintSet=new ConstraintSet();
        constraintSet.clone(constraintLayout);

        planId = (getIntent().getExtras() != null) ? getIntent().getExtras().getInt("planID") : 0;
        System.out.println("PLAN MAIN ACTIVITY " + planId);

        if (planId != 0) {
            if(planId<=2){
//                                item = findViewById(R.id.roomie);
//                                item.setVisibility(View.VISIBLE);
                binding.bottomNavigationViewLeaseholder.setVisibility(View.VISIBLE);
                constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLeaseholder.getId(),ConstraintSet.TOP,0);

            }
            //Landlord 3 4 5
            else{
//                                item = findViewById(R.id.create_post);
//                                item.setVisibility(View.VISIBLE);
                binding.bottomNavigationViewLanlord.setVisibility(View.VISIBLE);
                constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLanlord.getId(),ConstraintSet.TOP,0);
            }
        }

        //binding.bottomNavigationViewLeaseholder.setVisibility(View.VISIBLE);

        System.out.println("entró al mainact");
        Bundle extras= getIntent().getExtras();
       /* Post post= (Post) getIntent().getSerializableExtra("post");
        System.out.println("post de mainact");*/
        if(extras!=null){
            int value=extras.getInt("value");
            int userIdPost=extras.getInt("userId");

            System.out.println("valor mainact");
            System.out.println(value);
            if(value==1){
                int postId= (int) getIntent().getSerializableExtra("postId");
                System.out.println("entró al if value");
                Intent i = new Intent(getApplicationContext(), PostActivity.class);
                //i.putExtra("value", value);
                i.putExtra("postId", postId);
                i.putExtra("value", value);
                i.putExtra("userId",userIdPost);
                System.out.println(postId);
                startActivity(i);
            }
            else{

                System.out.println("value filter");
                System.out.println(value);

                //Obtener planId através del placeholder
                int userId = extras.getInt("userId");
                //System.out.println("User Id: "+ userId);


                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                placeHolderApi = retrofit.create(PlaceHolderApi.class);

                Call<Profile> call = placeHolderApi.getProfile(userId);

                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()){
                            planId = response.body().getPlan().getId();
                            int profileId = response.body().getId();
                            System.out.println("Plan ID Main: "+planId);
                            Bundle bundle = new Bundle();
                            bundle.putInt("planId", planId);
                            View item;

                                if(planId<=2){
//                                item = findViewById(R.id.roomie);
//                                item.setVisibility(View.VISIBLE);
                                    binding.bottomNavigationViewLeaseholder.setVisibility(View.VISIBLE);
                                    constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLeaseholder.getId(),ConstraintSet.TOP,0);

                                }
                                //Landlord 3 4 5
                                else{
//                                item = findViewById(R.id.create_post);
//                                item.setVisibility(View.VISIBLE);
                                    binding.bottomNavigationViewLanlord.setVisibility(View.VISIBLE);
                                    constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLanlord.getId(),ConstraintSet.TOP,0);
                                }


                        }
                        System.out.println("replace fragment");
                        replaceFragment(new HomeFragment(),userId,planId);
                        System.out.println("replace fragment2");
                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        System.out.println(t.getCause());
                    }
                });

                binding.bottomNavigationViewLanlord.setOnItemSelectedListener(item -> {

                    switch(item.getItemId()){
                        case R.id.home:
                            replaceFragment(new HomeFragment(),userId,planId);
                            break;
                        case R.id.profile:
                            replaceFragment(new ProfileFragment(),userId,planId);
                            break;
                        case R.id.create_post:
                            replaceFragment(new CreatePostFragment(),userId,planId);
                            break;
                        case R.id.roomie:
                            replaceFragment(new RoomieFragment(),userId,planId);
                            break;
                    }

                    return true;
                });
                binding.bottomNavigationViewLeaseholder.setOnItemSelectedListener(item -> {

                    switch(item.getItemId()){
                        case R.id.home:
                            replaceFragment(new HomeFragment(),userId,planId);
                            break;
                        case R.id.profile:
                            replaceFragment(new ProfileFragment(),userId,planId);
                            break;
                        case R.id.create_post:
                            replaceFragment(new CreatePostFragment(),userId,planId);
                            break;
                        case R.id.roomie:
                            replaceFragment(new RoomieFragment(),userId,planId);
                            break;
                    }

                    return true;
                });
            }

        }

        else{

            //Obtener planId através del placeholder
            int userId = extras.getInt("userId");
            //System.out.println("User Id: "+ userId);


            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            placeHolderApi = retrofit.create(PlaceHolderApi.class);

            Call<Profile> call = placeHolderApi.getProfile(userId);

            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    if (response.isSuccessful()){
                        planId = response.body().getPlan().getId();
                        int profileId = response.body().getId();
                        System.out.println("Plan ID: "+planId);
                        Bundle bundle = new Bundle();
                        bundle.putInt("planId", planId);
                        View item;
                            if(planId<=2){
//                                item = findViewById(R.id.roomie);
//                                item.setVisibility(View.VISIBLE);
                                binding.bottomNavigationViewLeaseholder.setVisibility(View.VISIBLE);
                                constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLeaseholder.getId(),ConstraintSet.TOP,0);

                            }
                            //Landlord 3 4 5
                            else{
//                                item = findViewById(R.id.create_post);
//                                item.setVisibility(View.VISIBLE);
                                binding.bottomNavigationViewLanlord.setVisibility(View.VISIBLE);
                                constraintSet.connect(binding.frameLayout.getId(),ConstraintSet.TOP,binding.bottomNavigationViewLanlord.getId(),ConstraintSet.TOP,0);
                            }

                    }
                    System.out.println("replace fragment");
                    replaceFragment(new HomeFragment(),userId,planId);
                    System.out.println("replace fragment2");
                }
                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    System.out.println(t.getCause());
                }
            });

            binding.bottomNavigationViewLeaseholder.setOnItemSelectedListener(item -> {

                switch(item.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment(),userId,planId);
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment(),userId,planId);
                        break;
                    case R.id.create_post:
                        replaceFragment(new CreatePostFragment(),userId,planId);
                        break;
                    case R.id.roomie:
                        replaceFragment(new RoomieFragment(),userId,planId);
                        break;
                }

                return true;
            });
            binding.bottomNavigationViewLanlord.setOnItemSelectedListener(item -> {

                switch(item.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment(),userId,planId);
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment(),userId,planId);
                        break;
                    case R.id.create_post:
                        replaceFragment(new CreatePostFragment(),userId,planId);
                        break;
                    case R.id.roomie:
                        replaceFragment(new RoomieFragment(),userId,planId);
                        break;
                }

                return true;
            });
        }

    }


    private void replaceFragment(Fragment fragment,int userId,int planId){
        System.out.println("llegó al replace fragment");
        Bundle bundle = new Bundle();
        bundle.putInt("userId", userId);
        bundle.putInt("planId", planId);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }



}