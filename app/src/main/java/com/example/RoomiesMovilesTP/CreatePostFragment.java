package com.example.RoomiesMovilesTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.intentobottom.R;

import Beans.Post;
import Beans.Post2;


public class CreatePostFragment extends Fragment {

    Button btnContinuar;

    Activity context;

    EditText edtRegion, edtProvince, edtDistrict, edtStreet;

    int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();

        userId = getArguments().getInt("userId");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    public void onStart(){
        super.onStart();

        edtRegion=(EditText) context.findViewById(R.id.edtRegion);
        edtProvince=(EditText) context.findViewById(R.id.edtProvince);
        edtDistrict=(EditText) context.findViewById(R.id.edtDistrict);
        edtStreet=(EditText) context.findViewById(R.id.edtStreet);

        btnContinuar=(Button) context.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(edtDistrict.getText().toString());
                Post2 post = new Post2("titulo","desc",edtStreet.getText().toString(),edtProvince.getText().toString(),edtDistrict.getText().toString(),
                        "url",edtRegion.getText().toString(),0,1,1);

                System.out.println(post.getTitle());

                Intent i= new Intent(context,CreatePostActivity2.class);
                i.putExtra("post", post);
                i.putExtra("userId", userId);
                startActivity(i);
            }
        });
    }

}