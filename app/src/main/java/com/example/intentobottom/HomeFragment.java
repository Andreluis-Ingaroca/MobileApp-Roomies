package com.example.intentobottom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private List<Post> data;
    RecyclerView recycler;
    Button btnFilter, btnRelevant;
    ListAdapter adapter;

    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onStart(){
        super.onStart();

        btnFilter=(Button) context.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,FilterActivity.class);
                startActivity(i);
            }
        });

        /*public void changeActivity()
        {
            Intent intent = new Intent(context, PostActivity.class);
            startActivity(intent);
        }*/

/*        btnContactar=(Button) context.findViewById(R.id.btnContactar);
        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent (context,PostActivity.class);
                startActivity(i);
            }
        });*/

        recycler = (RecyclerView) context.findViewById(R.id.recyclerData);
        recycler.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false));

        getPosts();
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418172319.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> inter = placeholder.getPosts();
        System.out.println("hola");
        inter.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> lista = response.body();
                data = new ArrayList<Post>();

                System.out.println(lista.size());

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
                            po.getLandlord()));
                }

                adapter = new ListAdapter(data);
                recycler.setAdapter(adapter);

                adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i= new Intent(context,PostActivity.class);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}