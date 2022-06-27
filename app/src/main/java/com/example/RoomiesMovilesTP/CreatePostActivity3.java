package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import com.example.intentobottom.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Beans.Post2;
import Beans.PostReceived;
import Beans.Profile;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePostActivity3 extends AppCompatActivity {

    private Button galleryBtn;

    private Button galleryBtn3;

    private Button btnContinuar3;

    private LinearLayout linearPhotos;

    MediaController mc;

    private ImageView imagePhoto;


    ImageSwitcher imageView;
    int PICK_IMAGE_MULTIPLE = 1;

    ArrayList<Uri> mArrayUri;
    int position = 0;


    Post2 post;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post3);

        // To retrieve object in second Activity
        post=(Post2) getIntent().getSerializableExtra("post");
        userId=(int) getIntent().getSerializableExtra("userId");


        imageView = findViewById(R.id.image);

        mArrayUri = new ArrayList<Uri>();

        linearPhotos=findViewById(R.id.linearPhotos);

        btnContinuar3=findViewById(R.id.btnContinuar3);


        mc=new MediaController(CreatePostActivity3.this);


        galleryBtn3=findViewById(R.id.galleryBtn3);
        imagePhoto=findViewById(R.id.imageView3);

        btnContinuar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //post.setPhoto("");

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

                Call<Profile> i = placeholder.getProfile(VariablesGlobales.userId);

                i.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        int profileId = response.body().getId();
                        postData(post, profileId);
                        System.out.println("Exito al crear Post con el Profile Id");
                        System.out.println(profileId);
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });
                System.out.println("Post de Create 3");
                System.out.println(post);
            }
        });


        // showing all images in imageswitcher
        imageView.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView1 = new ImageView(getApplicationContext());
                return imageView1;
            }
        });


        imageView = findViewById(R.id.image);


        galleryBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 3);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        /*if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {

            linearPhotos.setVisibility(View.VISIBLE);

            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                }
                // setting 1st selected image into image switcher
                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            } else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            }
        }*/

        if (requestCode==3 && resultCode == RESULT_OK && null != data){
            imagePhoto.setVisibility(View.VISIBLE);
            Uri imgUri=data.getData();

            System.out.println("prueba");

            if (imgUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    byte[] byteArray = outputStream.toByteArray();

                    //Use your Base64 String as you wish
                    String encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    post.setPhoto(encodedString);
                    System.out.println("fotooooo");
                    System.out.println(encodedString);
                    System.out.println("fotooooo2");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imgUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                String encodedImage = encodeImage(selectedImage);

                System.out.println("pruebafoto");
                post.setPhoto(encodedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            imagePhoto.setImageURI(imgUri);
        }
        else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't selected the item", Toast.LENGTH_LONG).show();
        }

    }

    private void postData(Post2 post, int profileId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);

        // calling a method to create a post and passing our modal class.
        Call<PostReceived> call = placeholder.createPost(post, profileId);

        // on below line we are executing our method.
        call.enqueue(new Callback<PostReceived>() {
            @Override
            public void onResponse(Call<PostReceived> call, Response<PostReceived> response) {
                // this method is called when we get response from our api.
                // this method is called when we get response from our api.
                PostReceived postReceived = response.body();
                int postId = postReceived.getId();
                System.out.println("Post Creado Id:");
                System.out.println(postId);

                Intent i=new Intent(getApplicationContext(),CreatePostActivity4.class);
                i.putExtra("postId",postId);
                i.putExtra("userId",userId);

                startActivity(i);
                //Toast.makeText(CreatePostActivity3.this, responseFromAPI.getAddress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostReceived> call, Throwable t) {
                System.out.println("failure create 3");
            }
        });
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        System.out.println(encImage);
        System.out.println("mi imagen jiji");
        //logLargeString(encImage);
        return encImage;
    }

    private void logLargeString(String content) {
        if (content.length() > 3000) {
            Log.d("mi", content.substring(0, 3000));
            logLargeString(content.substring(3000));
        } else {
            Log.d("mi", content);
        }
    }


}



