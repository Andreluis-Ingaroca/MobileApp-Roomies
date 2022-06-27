package com.example.RoomiesMovilesTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.intentobottom.R;


public class ProfileFragment extends Fragment {

    Activity context;
    Button btnFavorite, btnDashboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onStart(){
        super.onStart();
        btnFavorite=(Button) context.findViewById(R.id.btnFavorite);
        btnDashboard=(Button) context.findViewById(R.id.btnDashboard);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,FavoritePostActivity.class);
                startActivity(i);
                /*getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        int result = bundle.getInt("profileId");
                        Intent i= new Intent(context,FavoritePostActivity.class);
                        i.putExtra("profileId", result);
                        startActivity(i);
                    }
                });*/
            }
        });

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DashboardActivity.class);
                startActivity(i);
            }
        });
    }

}