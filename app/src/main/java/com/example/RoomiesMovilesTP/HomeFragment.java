package com.example.RoomiesMovilesTP;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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


public class HomeFragment extends Fragment {
    private List<Post> dataFavoritePost;
    //private List<Post> data;
    private ArrayList<Post> data;
    RecyclerView recycler;
    Button btnFilter, btnRelevant;
    ListAdapter adapter;

    Activity context;

    Boolean passActivity = false;
    Boolean activityValue;

    ArrayList<Post> newData, dataForever;

    /*Button btnContactarHome;
    Dialog mDialogHome;*/

    int value;
    int postId;
    Post post;
    int planId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();
        Bundle bundle = getArguments();
        if(bundle!=null){
            value = bundle.getInt("value");
            postId = bundle.getInt("postId");
            planId = bundle.getInt("planId");
        }
        System.out.println("value home");
        System.out.println(value);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onStart(){
        super.onStart();

        System.out.println("value home");
        System.out.println(value);

        System.out.println("value postid");
        System.out.println(postId);

        if(value==1){
            /*Intent i = new Intent(context, PostActivity.class);
            i.putExtra("value", 1);
            i.putExtra("postId", postId);
            startActivity(i);*/
            getPostById(postId);
        }

        btnFilter=(Button) context.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dataForever = data;

                Intent i = new Intent(context, FilterActivity.class);
                System.out.println(dataForever + "comienza");
                i.putExtra("datos", dataForever);
                System.out.println(dataForever + "termina");
                i.putExtra("valid", passActivity);
                i.putExtra("planId", planId);
                startActivity(i);
            }
        });

        activityValue = (this.context.getIntent().getExtras() != null) ? context.getIntent().getExtras().getBoolean("changeBoolean") : false;

        if (activityValue == false) {
            getFavoritePosts();
            //getPosts();
        } else {
            getFavoritePosts();
            //getPosts();
        }

        recycler = (RecyclerView) context.findViewById(R.id.recyclerData);
        recycler.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false));


        //getPosts();
    }

    private void getPosts() {

        //getFavoritePosts();
        System.out.println("entro a getpost");

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

                System.out.println("lista size "+ lista);
               System.out.println("data favorite post size "+ dataFavoritePost);


                if(dataFavoritePost==null){

                    System.out.println("dataFavoritePost es null");

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
                }
                else if(dataFavoritePost.size()==0){
                    System.out.println("dataFavoritePost size es 0");

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
                }
                else{
                    System.out.println("dataFavoritePost check");
                    for (int i = 0; i < dataFavoritePost.size(); i++) {
                        for (int j = 0; j < lista.size(); j++){


                            boolean v=false;

                            if(lista.get(j).getId()==dataFavoritePost.get(i).getId()) {

                                for (int m = 0; m < data.size(); m++) {
                                    if (data.get(m).getId() == lista.get(j).getId()) {
                                        v = true;
                                    }
                                }

                                if(v==false) {
                                    data.add(new Post(
                                            lista.get(j).getId(),
                                            lista.get(j).getTitle(),
                                            lista.get(j).getDescription(),
                                            lista.get(j).getAddress(),
                                            lista.get(j).getProvince(),
                                            lista.get(j).getDistrict(),
                                            lista.get(j).getPhoto(),
                                            lista.get(j).getDepartment(),
                                            lista.get(j).getPrice(),
                                            lista.get(j).getRoomQuantity(),
                                            lista.get(j).getBathroomQuantity(),
                                            lista.get(j).getPostDate(),
                                            lista.get(j).getLandlord(), true));
                                    //System.out.println("land"+lista.get(j).getLandlord());
                                }
                                else {
                                    data.get(j).setFlag(true);
                                }

                            }
                            else {
                                for (int m = 0; m < data.size(); m++) {
                                    if (data.get(m).getId() == lista.get(j).getId()){
                                        v=true;
                                    }
                                }
                                if(v==false) {
                                    data.add(new Post(
                                            lista.get(j).getId(),
                                            lista.get(j).getTitle(),
                                            lista.get(j).getDescription(),
                                            lista.get(j).getAddress(),
                                            lista.get(j).getProvince(),
                                            lista.get(j).getDistrict(),
                                            lista.get(j).getPhoto(),
                                            lista.get(j).getDepartment(),
                                            lista.get(j).getPrice(),
                                            lista.get(j).getRoomQuantity(),
                                            lista.get(j).getBathroomQuantity(),
                                            lista.get(j).getPostDate(),
                                            lista.get(j).getLandlord(), false));
                                    System.out.println("land"+lista.get(j).getLandlord());
                                }

                            }
                        }
                    }
                }


                if (activityValue == false) {
                    adapter = new ListAdapter(data, context, true, new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Post post) {
                            moveToDetailedPost(post);
                        }
                    });

                    recycler.setAdapter(adapter);
                } else {
                    recibir();
                }



            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void getFavoritePosts() {
        System.out.println("entreo a getfavoritepost");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*Bundle extras= getIntent().getExtras();
        int userId = extras.getInt("userId");*/

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> inter = placeholder.getPostsByLeaseholderId(21);

        inter.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> listafav = response.body();
                dataFavoritePost = new ArrayList<Post>();

                System.out.println("entro al callback favoritepost");

                for (Post po : listafav) {
                    dataFavoritePost.add(new Post(
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

                }

                if(dataFavoritePost==null)
                    System.out.println("spi es null");
                else
                    System.out.println("spi NO es null: "+ dataFavoritePost.size());

                if(dataFavoritePost.size()==0)
                    System.out.println("spi ES CERO");

                System.out.println("responseeeee");
                System.out.println(response.body());

                getPosts();

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                System.out.println("ES NULL");
            }
        });
    }

    void moveToDetailedPost(Post post) {
        Intent i= new Intent(context, PostActivity.class);
        i.putExtra("Post", post);
        i.putExtra("value", 2);
        startActivity(i);
    }

    private void recibir() {
        if (activityValue != false) {
            System.out.println("Estoy muertooo");
            newData = (ArrayList<Post>) context.getIntent().getSerializableExtra("lista");
            System.out.println(newData.size()+"aquii");
            ListAdapter adap = new ListAdapter(newData, context, true, new ListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Post post) {
                    moveToDetailedPost(post);
                }
            });
            recycler.setAdapter(adap);
        }
    }

    private void getPostById(int postId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        Call<Post> inter = placeholder.getPostById(postId);
        System.out.println("hola");
        System.out.println("postId");
        System.out.println(postId);

        inter.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                post = response.body();

                System.out.println("response body home");
                System.out.println(response.body());

                moveToDetailedPost(post);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("Failure Post");
            }
        });


    }

}