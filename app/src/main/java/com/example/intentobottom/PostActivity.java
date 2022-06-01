package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intentobottom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Beans.Review;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    TextView txtDistrito, txtPrecio, txtDormitorios, txtBaños, txtDireccion, txtDescripcion, txtLandlordName, txtLandlordLastName, txtReviewAuthor, txtReviewContent;;
    ImageView imgPost;
    Button btnContactar;
    Dialog mDialog;
    int id;
    ArrayList<Review> review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Post post = (Post) getIntent().getSerializableExtra("Post");

        txtDireccion = findViewById(R.id.txtDireccion);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDistrito = findViewById(R.id.txtDistrito);
        txtBaños = findViewById(R.id.txtBaños);
        txtDormitorios = findViewById(R.id.txtDormitorios);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtLandlordName = findViewById(R.id.txtLandlordName);
        txtLandlordLastName = findViewById(R.id.txtLandlordLastName);
        txtReviewAuthor = findViewById(R.id.txtReviewAuthor);
        txtReviewContent = findViewById(R.id.txtReviewContent);
        imgPost = findViewById(R.id.imgPost);
        btnContactar = findViewById(R.id.btnContactar);
        mDialog = new Dialog(this);

        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.contact_popup);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                mDialog.show();
            }
        });

        txtDireccion.setText(post.getAddress()+"");
        txtDormitorios.setText(post.getRoomQuantity()+"");
        txtBaños.setText(post.getBathroomQuantity()+"");
        txtDistrito.setText(post.getDistrict()+"");
        txtPrecio.setText(post.getPrice()+"");
        txtDescripcion.setText(post.getDescription()+"");
        txtLandlordName.setText(post.getLandlord().getName()+"");
        txtLandlordLastName.setText(post.getLandlord().getLastName()+"");
        id = post.getId();
        getReviews();
        Picasso.get().load(post.getPhoto()).into(imgPost);
    }

    private void getReviews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolder = retrofit.create(PlaceHolderApi.class);

        Call<List<Review>> inter = placeHolder.getReviews(id);

        inter.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                List<Review> data = response.body();
                review = new ArrayList<Review>();

                for (Review re : data) {
                    review.add(new Review(
                            re.getId(),
                            re.getContent(),
                            re.getLeaseholder(),
                            re.getPost()));
                }
                System.out.println(review.get(0).getLeaseholder() + "aaa");
                txtReviewAuthor.setText(review.get(0).getLeaseholder().getName()+" "+ review.get(0).getLeaseholder().getLastName()+" comentó:");
                txtReviewContent.setText(review.get(0).getContent()+"");
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}