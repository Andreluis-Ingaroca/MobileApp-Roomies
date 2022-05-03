package com.example.intentobottom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post3);

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
            imagePhoto.setImageURI(imgUri);
        }
        else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't selected the item", Toast.LENGTH_LONG).show();
        }

    }

}