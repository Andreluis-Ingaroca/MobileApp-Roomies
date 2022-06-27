package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intentobottom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Beans.Review;
import Beans.Rule;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    TextView txtDistrito, txtPrecio, txtDormitorios, txtBaños, txtDireccion, txtDescripcion, txtLandlordName, txtLandlordLastName, txtReviewAuthor, txtReviewContent, txtRules;
    ImageView imgPost;
    Button btnContactar, btnReviews;
    FloatingActionButton floatingActionButton;
    Dialog mDialog;
    int id;
    ArrayList<Review> review;
    List<Rule> rules;
    int postId,userId,value;
    Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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
        txtRules = findViewById(R.id.txtRules);
        imgPost = findViewById(R.id.imgPost);
        btnContactar = findViewById(R.id.btnContactar);
        btnReviews = findViewById(R.id.btnReviews);
        floatingActionButton = findViewById(R.id.btnHome);
        mDialog = new Dialog(this);

        value = (int) getIntent().getSerializableExtra("value");
        if(value==1){
            postId= (int) getIntent().getSerializableExtra("postId");
            userId = (int) getIntent().getSerializableExtra("userId");

            this.getPostById(postId);
        }
        else
            if(value==2){
            post= (Post) getIntent().getSerializableExtra("Post");

            txtDireccion.setText(post.getAddress()+"");
            txtDormitorios.setText(post.getRoomQuantity()+"");
            txtBaños.setText(post.getBathroomQuantity()+"");
            txtDistrito.setText(post.getDistrict()+"");
            txtPrecio.setText(post.getPrice()+"");
            txtDescripcion.setText(post.getDescription()+"");
            txtLandlordName.setText(post.getLandlord().getName()+"");
            txtLandlordLastName.setText(post.getLandlord().getLastName()+"");
            id = post.getId();
            getRules();
            getLastReview();

            decodeBase64Image(post.getPhoto());
        }




        if(value==1) {
            floatingActionButton.show();
        }
        else {
            floatingActionButton.hide();
        }

        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.contact_popup);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                mDialog.show();
            }
        });

        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToPostReviews();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("value",0);
                i.putExtra("postId",postId);
                i.putExtra("userId",userId);
                startActivity(i);
            }
        });
    }

    private void getLastReview() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolder = retrofit.create(PlaceHolderApi.class);

        Call<List<Review>> inter = placeHolder.getReviews(id);

        inter.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.body().size()!=0) {
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
                    txtReviewAuthor.setText(review.get(review.size() - 1).getLeaseholder().getName() + " " + review.get(review.size() - 1).getLeaseholder().getLastName() + " comentó:");
                    txtReviewContent.setText(review.get(review.size() - 1).getContent() + "");
                } else {
                    txtReviewAuthor.setText("No existen reviews para este Post.");
                    txtReviewContent.setText(" ");
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }

    private void getRules() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolder = retrofit.create(PlaceHolderApi.class);

        Call<List<Rule>> inter = placeHolder.getRules(id);

        inter.enqueue(new Callback<List<Rule>>() {
            @Override
            public void onResponse(Call<List<Rule>> call, Response<List<Rule>> response) {
                String texto = "";
                if(response.body().size()!=0) {
                    rules = response.body();
                    for(int i = 0; i < rules.size(); i++) {
                        texto += "- " + rules.get(i).getDescription() + "\n";
                    }
                    txtRules.setText(texto);
                } else {
                    txtRules.setText("Aún no se han dictado reglas.");
                }
            }

            @Override
            public void onFailure(Call<List<Rule>> call, Throwable t) {

            }
        });
    }

    void moveToPostReviews() {
        Intent i= new Intent(getApplicationContext(), ReviewsActivity.class);
        i.putExtra("Post", post);
        startActivity(i);
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
                Post post;
                post = response.body();

                System.out.println("Post Activity post body");
                System.out.println(response.body());

                txtDireccion.setText(post.getAddress()+"");
                txtDormitorios.setText(post.getRoomQuantity()+"");
                txtBaños.setText(post.getBathroomQuantity()+"");
                txtDistrito.setText(post.getDistrict()+"");
                txtPrecio.setText(post.getPrice()+"");
                txtDescripcion.setText(post.getDescription()+"");
                txtLandlordName.setText(post.getLandlord().getName()+"");
                txtLandlordLastName.setText(post.getLandlord().getLastName()+"");
                id = post.getId();
                getRules();
                getLastReview();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("Failure Post");
            }
        });
    }

    private void decodeBase64Image(String base64Image) {
        byte[] bytes = Base64.decode(base64Image, Base64.NO_WRAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imgPost.setImageBitmap(bitmap);
    }
}