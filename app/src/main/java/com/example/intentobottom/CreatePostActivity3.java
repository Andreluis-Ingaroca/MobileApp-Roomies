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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Beans.Post;
import Beans.Post2;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePostActivity3 extends AppCompatActivity {

    private Button galleryBtn;
    private Button galleryBtn2;
    private Button galleryBtn3;

    private Button btnContinuar3;

    private LinearLayout linearPhotos;
    private VideoView videoview;
    MediaController mc;

    private ImageView imagePhoto;

    Button select, previous, next;
    ImageSwitcher imageView;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    TextView total;
    ArrayList<Uri> mArrayUri;
    int position = 0;
    List<String> imagesEncodedList;

    Post2 post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post3);

        // To retrieve object in second Activity
        post=(Post2) getIntent().getSerializableExtra("post");

        select = findViewById(R.id.select);
        imageView = findViewById(R.id.image);
        previous = findViewById(R.id.previous);
        mArrayUri = new ArrayList<Uri>();

        linearPhotos=findViewById(R.id.linearPhotos);

        btnContinuar3=findViewById(R.id.btnContinuar3);

        galleryBtn2=findViewById(R.id.galleryBtn2);
        videoview=findViewById(R.id.video);
        mc=new MediaController(CreatePostActivity3.this);
        videoview.setMediaController(mc);

        galleryBtn3=findViewById(R.id.galleryBtn3);
        imagePhoto=findViewById(R.id.imageView3);

        btnContinuar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),CreatePostActivity4.class);

                //post.setPhoto("https://www.inmoblog.com/wp-content/uploads/2014/06/imagen-vivienda-optima.jpg");



                postData(post);

                startActivity(i);
            }
        });


        galleryBtn2=findViewById(R.id.galleryBtn2);

        galleryBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent pickVideo = new Intent(Intent.ACTION_PICK);
                pickVideo.setType("video/*");
                startActivityForResult(pickVideo , 2);

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
        next = findViewById(R.id.next);

        // click here to select next image
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < mArrayUri.size() - 1) {
                    // increase the position by 1
                    position++;
                    imageView.setImageURI(mArrayUri.get(position));
                } else {
                    Toast.makeText(CreatePostActivity3.this, "La última imagen ya fue mostrada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // click here to view previous image
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    // decrease the position by 1
                    position--;
                    imageView.setImageURI(mArrayUri.get(position));
                }
            }
        });

        imageView = findViewById(R.id.image);

        // click here to select image
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // initialising intent
                Intent intent = new Intent();

                // setting type to select to be image
                intent.setType("image/*");

                // allowing multiple image to be selected
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

            }
        });


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
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {

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
        }
        else if (requestCode==2){
            Uri videoUri=data.getData();
            videoview.setVisibility(View.VISIBLE);
            videoview.setVideoURI(videoUri);
        }
        else if (requestCode==3 && resultCode == RESULT_OK && null != data){
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

           /* final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imgUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                String encodedImage = encodeImage(selectedImage);

                //System.out.println("pruebafoto");
                //System.out.println(encodedImage);
                //post.setPhoto(encodedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/



            imagePhoto.setImageURI(imgUri);
        }
        else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't selected the item", Toast.LENGTH_LONG).show();
        }

    }

    private void postData(Post2 post) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418172319.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeholder = retrofit.create(PlaceHolderApi.class);


        System.out.println("pepesi");
        System.out.println(post);
        System.out.println(post.getAddress());
        System.out.println(post.getTitle());
        System.out.println(post.getPhoto());
        System.out.println(post.getPrice());
        System.out.println(post.getRoomQuantity());

        // calling a method to create a post and passing our modal class.
        Call<Post2> call = placeholder.createPost(post);

        // on below line we are executing our method.
        call.enqueue(new Callback<Post2>() {
            @Override
            public void onResponse(Call<Post2> call, Response<Post2> response) {
                // this method is called when we get response from our api.
                //Toast.makeText(CreatePostActivity3.this, "Data added to API", Toast.LENGTH_SHORT).show();
                //Toast.makeText(CreatePostActivity3.this, responseFromAPI.getAddress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post2> call, Throwable t) {

            }
        });
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }


}