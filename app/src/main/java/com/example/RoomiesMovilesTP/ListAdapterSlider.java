package com.example.RoomiesMovilesTP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.intentobottom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Slider;

public class ListAdapterSlider extends PagerAdapter {

    private List<Slider> sliders;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListAdapterSlider(List<Slider> sliders, Context context) {
        this.sliders = sliders;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView name, desc;

        imageView = view.findViewById(R.id.imageSlider);
        name = view.findViewById(R.id.nameSlider);
        desc = view.findViewById(R.id.descriptionSlider);

        Picasso.get().load(sliders.get(position).getImagrUrl()).into(imageView);
        /*imageView.setImage(sliders.get(position).getImagrUrl());*/
        name.setText(sliders.get(position).getName());
        desc.setText(sliders.get(position).getDescription());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
