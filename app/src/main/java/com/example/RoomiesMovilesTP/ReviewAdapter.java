package com.example.RoomiesMovilesTP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentobottom.R;

import java.util.List;

import Beans.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.viewHolder> {

    private List<Review> data;

    private Context context;

    public ReviewAdapter(List<Review> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, null, false);
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

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtLeaseholder, txtReview;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtLeaseholder = itemView.findViewById(R.id.txtLeaseholder);
            txtReview = itemView.findViewById(R.id.txtReview);
        }

        public void asignarData(Review review) {
            txtLeaseholder.setText(review.getLeaseholder().getName() + " " + review.getLeaseholder().getLastName() + " comentó:");
            txtReview.setText(review.getContent()+"");
        }
    }
}
