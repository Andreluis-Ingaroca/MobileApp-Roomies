package com.example.RoomiesMovilesTP;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentobottom.R;

import java.util.Locale;

import Beans.AuthenticationResponse;
import Beans.DNIData;
import Beans.RegisterRequest;
import Beans.SaveProfile;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity4 extends AppCompatActivity {

    Button btnRegistrar;
    EditText eTextNombre,eTextApellido,eTextcelular,eTextdni, eTextUsername, eTextPassword,
            eTextDescripcion,eTextFechaDeNacimiento,eTextDepartamento,
            eTextProvincia,eTextDistrito,eTextDireccion,eTextPfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        btnRegistrar=findViewById(R.id.btnRegistrarse);
        eTextNombre=findViewById(R.id.p_nombre);
        eTextApellido=findViewById(R.id.p_apellidos);
        eTextUsername=findViewById(R.id.p_username);
        eTextPassword=findViewById(R.id.p_password);
        eTextcelular=findViewById(R.id.p_celular);
        eTextdni=findViewById(R.id.p_dni);
        eTextDescripcion=findViewById(R.id.p_description);
        eTextFechaDeNacimiento=findViewById(R.id.p_birthdate);
        eTextDepartamento=findViewById(R.id.p_department);
        eTextProvincia=findViewById(R.id.p_province);
        eTextDistrito=findViewById(R.id.p_district);
        eTextDireccion=findViewById(R.id.p_address);
        eTextPfp=findViewById(R.id.p_pfp);

        int planId=0;
        int userType=0;

        Bundle extras= getIntent().getExtras();
        if(extras!=null){
            planId=extras.getInt("planId");
            userType=extras.getInt("userType");
        }

        int finalUserType = userType;
        int finalPlanId = planId;
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(RegisterActivity4.this, "El DNI ingresado si corresponde a los datos ingresados", Toast.LENGTH_SHORT).show();
                System.out.println(finalUserType);
                System.out.println(finalPlanId);
                RegisterRequest userRequest = new RegisterRequest(eTextNombre.getText().toString(),eTextApellido.getText().toString(),eTextUsername.getText().toString(),eTextPassword.getText().toString());
                createUser(finalUserType,finalPlanId,userRequest);

            }
        });
    }

    private void verifyDNIInfo(int userType, int planId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiperu.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);

        Call<DNIData> inter = placeHolderApi.getDNIAuth(eTextdni.getText().toString());

        inter.enqueue(new Callback<DNIData>() {
            @Override
            public void onResponse(Call<DNIData> call, Response<DNIData> response) {
                DNIData dniData = response.body();
                if(dniData!=null){
                    if(dniData.isSuccess()){
                        String nombres=eTextNombre.getText().toString().toLowerCase(Locale.ROOT);
                        String apellidos=eTextApellido.getText().toString().toLowerCase(Locale.ROOT);
                        String nombresGet= dniData.getData().getNombres().toLowerCase(Locale.ROOT);
                        String apellidosGet = dniData.getData().getApellido_paterno().toLowerCase(Locale.ROOT)+" "+dniData.getData().getApellido_materno().toLowerCase(Locale.ROOT);
                        if(nombres.equals(nombresGet) && apellidos.equals(apellidosGet)){
                            Toast.makeText(RegisterActivity4.this, "El DNI ingresado si corresponde a los datos ingresados", Toast.LENGTH_SHORT).show();
                            System.out.println(nombresGet+" "+apellidosGet);
                            RegisterRequest userRequest = new RegisterRequest(eTextNombre.getText().toString(),eTextApellido.getText().toString(),eTextUsername.getText().toString(),eTextPassword.getText().toString());
                            createUser(userType,planId,userRequest);
                        }
                        else {
                            Toast.makeText(RegisterActivity4.this, "El DNI ingresado no corresponde a los datos ingresados", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<DNIData> call, Throwable t) {

            }
        });

    }

    private void createUser(int userType,int planId,RegisterRequest request){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        System.out.println("entró create user");
        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
        Call<AuthenticationResponse> inter = placeHolderApi.createUser(request);

        System.out.println("entró create user2");

        inter.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                AuthenticationResponse authenticationResponse = response.body();
                int userId= authenticationResponse.getId();
                System.out.println("authenticationr response"+response.body());
                String sBirthDate=eTextFechaDeNacimiento.getText().toString();


                SaveProfile saveProfile = new SaveProfile(eTextNombre.getText().toString()
                        ,eTextApellido.getText().toString(),eTextcelular.getText().toString(),
                        eTextdni.getText().toString(),eTextDescripcion.getText().toString(),
                        sBirthDate,eTextDepartamento.getText().toString(),
                        eTextProvincia.getText().toString(),eTextDistrito.getText().toString(),
                        eTextDireccion.getText().toString(),eTextPfp.getText().toString());

                createProfile(userType,saveProfile,userId,planId);


            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                System.out.println("pepito");
            }
        });
    }

    private void createProfile(int userType, SaveProfile saveProfile, int userId, int planId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://roomiesapi20220418164342.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        System.out.println("userId:" +userId);
        System.out.println("planId:"+planId);

        System.out.println("Entro a crear perfil 22222");
        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
        System.out.println(userType);
        if(userType==1){
            Call<SaveProfile> inter = placeHolderApi.createLandlord(saveProfile,userId,planId);
            inter.enqueue(new Callback<SaveProfile>() {
                @Override
                public void onResponse(Call<SaveProfile> call, Response<SaveProfile> response) {
                    System.out.println("Entro a crear perfil landlord 3333");
                    System.out.println(response);
                    System.out.println(response.body());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<SaveProfile> call, Throwable t) {
                    System.out.println("falló landlord");
                }
            });
        }
        else {
            Call<SaveProfile> inter = placeHolderApi.createLeaseholder(saveProfile,userId,planId);
            inter.enqueue(new Callback<SaveProfile>() {
                @Override
                public void onResponse(Call<SaveProfile> call, Response<SaveProfile> response) {
                    System.out.println("Entro a crear perfil leaseholder 3333");
                    System.out.println(response);
                    SaveProfile leaseholder= response.body();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<SaveProfile> call, Throwable t) {
                    System.out.println("falló leaseholder");

                }
            });

        }
    }
}