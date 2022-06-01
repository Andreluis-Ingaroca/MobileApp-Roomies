package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class FavoritePostActivity extends AppCompatActivity {

    private List<Post> data;
    RecyclerView recycler;
    ListAdapter adapter;


    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_post);

        context=this;

        recycler = findViewById(R.id.recyclerDataFavoritePosts);
        recycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

       getFavoritePosts();

    }

    private void getFavoritePosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*Bundle extras= getIntent().getExtras();
        int userId = extras.getInt("userId");*/

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> inter = placeholder.getPostsByLeaseholderId(21);
        System.out.println("hola");

        inter.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> lista = response.body();
                data = new ArrayList<Post>();

                System.out.println(lista.size());

                if(lista.size()>0){
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
                                true));

                        System.out.println("land"+po.getLandlord());
                    }



                    adapter = new ListAdapter(data, context, false, new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Post post) {
                            moveToDetailedPost(post);
                        }
                    });
                    recycler.setAdapter(adapter);



                    /*adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent i= new Intent(context,PostActivity.class);
                            startActivity(i);
                        }
                    });*/
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    void moveToDetailedPost(Post post) {
        Intent i= new Intent(context, PostActivity.class);
        i.putExtra("Post", post);
        startActivity(i);
    }

}