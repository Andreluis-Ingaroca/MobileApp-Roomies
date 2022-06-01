package com.example.RoomiesMovilesTP;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentobottom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Post;
import Beans.Post2;
import Interface.PlaceHolderApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    private List<Post> data;

    private Context context;

    private boolean isHome;

    final ListAdapter.OnItemClickListener listener;

    public ListAdapter(List<Post> data, Context context, boolean isHome,ListAdapter.OnItemClickListener listener) {

        this.data = data;
        this.context=context;
        this.isHome=isHome;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.asignarData(data.get(position));
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    //private OnItemClickListener mListener;

    public interface OnItemClickListener{
        //void onItemClick(int position);
        void onItemClick(Post posts);
    }

   /* public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }*/


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView price, address, district, department, area, roomQuantity,
                bathroomQuantity, rating, heart;
        Button contactar;
        ImageView img;

        boolean flag;

        FloatingActionButton btnFav;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.txtPrice);
            address = itemView.findViewById(R.id.txtAddress);
            district = itemView.findViewById(R.id.txtDistrict);
           // department = itemView.findViewById(R.id.txtDepartment);
            /*area = itemView.findViewById(R.id.txtArea);*/
            roomQuantity = itemView.findViewById(R.id.txtRoomQuantity);
            bathroomQuantity = itemView.findViewById(R.id.txtBathroomQuantity);
            //rating = itemView.findViewById(R.id.txtRating);
            //heart = itemView.findViewById(R.id.txtHeart);
            contactar = itemView.findViewById(R.id.btnContactar);
            img = itemView.findViewById(R.id.imagePost);

            btnFav=itemView.findViewById(R.id.fltBtnFavorite);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });*/

        }

        public void asignarData(Post posts) {
            price.setText("S/"+posts.getPrice()+"");
            address.setText(posts.getAddress()+"");
            district.setText(posts.getDistrict()+", "+ posts.getDepartment());
            //department.setText(posts.getDepartment()+"");
            roomQuantity.setText(posts.getRoomQuantity()+"");
            bathroomQuantity.setText(posts.getBathroomQuantity()+"");
            flag=posts.isFlag();

            if(flag){
                btnFav.setImageResource(R.drawable.ic_baseline_favorite_24);
                ImageViewCompat.setImageTintList(btnFav, ColorStateList.valueOf(Color.rgb(255,182,0)));
            }
            else{
                btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24); }

//            area.setText();
//            rating.setText();
//            heart.setText();
//            contactar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(posts);
                }
            });
            Picasso.get().load(posts.getPhoto()).into(img);

            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(flag){
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        flag=false;

                        deleteFavoritePost(posts.getId());
                    }
                    else{

                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24);
                        ImageViewCompat.setImageTintList(btnFav, ColorStateList.valueOf(Color.rgb(255,182,0)));
                        flag=true;

                        postFavoritePost(posts.getId());
                    }

                    //btnFav.setVisibility(view.INVISIBLE);
                    //btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);


                }
            });
        }

        private void postFavoritePost(int postId) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);


            // calling a method to create a post and passing our modal class.
            Call<Post2> call = placeholder.createFavoritePost(postId);

            System.out.println(postId);

            // on below line we are executing our method.
            call.enqueue(new Callback<Post2>() {
                @Override
                public void onResponse(Call<Post2> call, Response<Post2> response) {
                    // this method is called when we get response from our api.
                    //Toast.makeText(context, postId, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(CreatePostActivity3.this, responseFromAPI.getAddress(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Post2> call, Throwable t) {

                }
            });
        }

        private void deleteFavoritePost(int postId) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);


            // calling a method to create a post and passing our modal class.
            Call<ResponseBody> call = placeholder.deleteFavoritePost(postId);


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    // use response.code, response.headers, etc.
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // handle failure
                }
            });


            if(isHome==false){
                data.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), data.size());
            }

        }


    }
}
