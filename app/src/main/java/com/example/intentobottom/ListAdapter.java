package com.example.intentobottom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Post;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    private List<Post> data;

    public ListAdapter(List<Post> data) {
        this.data = data;
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

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView price, address, district, department, area, roomQuantity,
                bathroomQuantity, rating, heart;
        Button contactar;
        ImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.txtPrice);
            address = itemView.findViewById(R.id.txtAddress);
            district = itemView.findViewById(R.id.txtDistrict);
            department = itemView.findViewById(R.id.txtDepartment);
            area = itemView.findViewById(R.id.txtArea);
            roomQuantity = itemView.findViewById(R.id.txtRoomQuantity);
            bathroomQuantity = itemView.findViewById(R.id.txtBathroomQuantity);
            //rating = itemView.findViewById(R.id.txtRating);
            //heart = itemView.findViewById(R.id.txtHeart);
            contactar = itemView.findViewById(R.id.btnContactar);
            img = itemView.findViewById(R.id.imagePost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void asignarData(Post posts) {
            price.setText(posts.getPrice()+"");
            address.setText(posts.getAddress()+"");
            district.setText(posts.getDistrict()+"");
            department.setText(posts.getDepartment()+"");
            roomQuantity.setText(posts.getRoomQuantity()+"");
            bathroomQuantity.setText(posts.getBathroomQuantity()+"");
//            area.setText();
//            rating.setText();
//            heart.setText();
//            contactar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
            Picasso.get().load(posts.getPhoto()).into(img);
        }
    }
}
