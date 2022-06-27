package com.example.RoomiesMovilesTP;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.intentobottom.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.AuthenticationRequest;
import Beans.Profile;
import Beans.Slider;
import Beans.User;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomieFragment extends Fragment {

    PlaceHolderApi placeHolderApi;
    Activity context;

    Dialog mDialog;
    Button contactar;

    ViewPager viewPager;
    ListAdapterSlider listAdapterSlider;
    List<Slider> sliders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context=getActivity();

        return inflater.inflate(R.layout.fragment_roomie, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        sliders = new ArrayList<>();

        mDialog = new Dialog(context);
        contactar = (Button) context.findViewById(R.id.btnSearchRoomiesContact);

        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setContentView(R.layout.contact_popup);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                mDialog.show();
            }
        });


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        placeHolderApi = retrofit.create(PlaceHolderApi.class);

        Call<List<Profile>> call = placeHolderApi.getProfiles();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                System.out.println("No fue fail dx");
                List<Profile> lista = response.body();
                System.out.println("Largo de la vista traida de profiles: "+lista.size());
                if (response.isSuccessful()){
                    for (Profile p: lista){
                        if (p.getPlan().getId() == 1 || p.getPlan().getId() == 2){
                            sliders.add(new Slider(p.getProfilePicture(), p.getName() + " " + p.getLastName(), p.getDescription()));
                        }
                    }
                    listAdapterSlider = new ListAdapterSlider(sliders, context);
                    viewPager = (ViewPager) context.findViewById(R.id.viewPager);
                    System.out.println("listadapterslider");
                    System.out.println(listAdapterSlider);
                    System.out.println(viewPager);
                    viewPager.setAdapter(listAdapterSlider);
                    viewPager.setPadding(130,0,130,0);
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                System.out.println("Fue fail dx");
                System.out.println(t.getCause());
            }
        });
    }
}