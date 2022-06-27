package com.example.RoomiesMovilesTP;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ListAdapterNoLogin extends RecyclerView.Adapter<ListAdapterNoLogin.viewHolder>
        implements View.OnClickListener{

    private List<Post> data;

    private Context context;

    private  View.OnClickListener listener;


    public ListAdapterNoLogin(List<Post> data, Context context) {

        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementonologin, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));

        view.setOnClickListener(this);
        return new ListAdapterNoLogin.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.asignarData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView price, address, district, roomQuantity,
                bathroomQuantity;
        ImageView img;

        FloatingActionButton btnFav;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.txtPriceNoLogin);
            address = itemView.findViewById(R.id.txtAddressNoLogin);
            district = itemView.findViewById(R.id.txtDistrictNoLogin);
            roomQuantity = itemView.findViewById(R.id.txtRoomQuantityNoLogin);
            bathroomQuantity = itemView.findViewById(R.id.txtBathroomQuantityNoLogin);
            img = itemView.findViewById(R.id.imagePostNoLogin);

        }

        public void asignarData(Post posts) {
            price.setText("S/"+posts.getPrice()+"");
            address.setText(posts.getAddress()+"");
            district.setText(posts.getDistrict()+", "+ posts.getDepartment());
            roomQuantity.setText(posts.getRoomQuantity()+"");
            bathroomQuantity.setText(posts.getBathroomQuantity()+"");

            System.out.println(posts.getDescription());
            System.out.println("foto");
            System.out.println(posts.getPhoto());
            decodeBase64Image(posts.getPhoto());

            //Picasso.get().load(posts.getPhoto()).into(img);
        }

        private void decodeBase64Image(String base64Image) {
            byte[] bytes = Base64.decode(base64Image, Base64.NO_WRAP);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img.setImageBitmap(bitmap);
            System.out.println("bitmap");
            System.out.println(base64Image);
            System.out.println(bitmap);
        }
    }
}