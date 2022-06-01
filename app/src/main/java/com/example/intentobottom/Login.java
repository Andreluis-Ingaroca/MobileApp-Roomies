package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentobottom.R;

import Beans.AuthenticationRequest;
import Beans.User;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Button btnIngresar, btnRegistrar;
    EditText login_username, login_password;
    PlaceHolderApi placeHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar=findViewById(R.id.btnIngresar);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        login_username=findViewById(R.id.login_username);
        login_password=findViewById(R.id.login_password);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = login_username.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                placeHolderApi = retrofit.create(PlaceHolderApi.class);

                AuthenticationRequest authentication = new AuthenticationRequest(username, password);

                Call<User> call = placeHolderApi.login(authentication);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()){
                            int userId = response.body().getResult().getId();
                            System.out.println(userId);

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("userId", userId);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña invalido", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error en el request", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity2.class);
                startActivity(i);
            }
        });
    }
}