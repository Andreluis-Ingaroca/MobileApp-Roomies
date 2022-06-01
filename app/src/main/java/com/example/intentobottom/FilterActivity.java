package com.example.RoomiesMovilesTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.intentobottom.R;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Console;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import Beans.Post;
import Interface.PlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterActivity extends AppCompatActivity {

    Button btnApply;
    Spinner spnDepartament, spnProvince, spnDistrict;
    RangeSlider sldPrice;
    RadioButton rPrimero, rSegundo, rTercero;
    RadioButton rFirst, rSecond, rThird;

    String department, province, district;
    Boolean validDepartment = false, validProvince = false, validDistrict = false;

    ArrayList<Post> dataFiltrada = new ArrayList<>();
    ArrayList<Post> listPosts;

    Boolean confirmActivity, answerActivity;

    List<Float> rangePrice;
    Integer num, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        spnDepartament = findViewById(R.id.spnDepartment);
        spnProvince = findViewById(R.id.spnProvince);
        spnDistrict = findViewById(R.id.spnDistrict);
        sldPrice = findViewById(R.id.sldPrice);
        rPrimero = findViewById(R.id.rPrimero);
        rSegundo = findViewById(R.id.rSegundo);
        rTercero = findViewById(R.id.rTercero);
        rFirst = findViewById(R.id.rFirst);
        rSecond = findViewById(R.id.rSecond);
        rThird = findViewById(R.id.rThird);

        btnApply = findViewById(R.id.btnApply);

        listPosts = (ArrayList<Post>) getIntent().getSerializableExtra("datos");
        confirmActivity = getIntent().getExtras().getBoolean("valid");

        System.out.println("CONFIRMM" + listPosts);
//        System.out.println(confirmActivity.toString());

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(district + "count" + count + "num" + num);
                if (rangePrice != null) {
                    filter(department, province, district, rangePrice, num, count);
                } else {
                    System.out.println("MORICION + " + sldPrice.getValues().get(0) + "   " + sldPrice.getValues().get(1));
                    filter(department, province, district, sldPrice.getValues(), num, count);
                }
            }
        });

        spnDepartament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                department = spnDepartament.getSelectedItem().toString();
                System.out.println(department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                province = spnProvince.getSelectedItem().toString();
                System.out.println(province);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                district = spnDistrict.getSelectedItem().toString();
                System.out.println(district);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sldPrice.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                currencyFormat.setCurrency(Currency.getInstance("PEN"));
                return currencyFormat.format(value);
            }
        });

        sldPrice.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                rangePrice = slider.getValues();

                System.out.println("CHAN"+rangePrice.get(0));
                System.out.println(rangePrice.get(1));
                System.out.println(slider.getValues().getClass().getSimpleName());
            }
        });

        rPrimero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 1;
                System.out.println("RADIO  " + num);
            }
        });

        rSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 2;
                System.out.println("RADIO  " + num);
            }
        });

        rTercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 3;
                System.out.println("RADIO  " + num);
            }
        });

        rFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 1;
                System.out.println("RADIO  " + count);
            }
        });

        rSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 2;
                System.out.println("RADIO  " + count);
            }
        });

        rThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 3;
                System.out.println("RADIO  " + count);
            }
        });

        if (rPrimero.isChecked()) {
            num = 1;
        }

        if (rFirst.isChecked()){
            count = 1;
        }

    }

    private void filter(String department, String province, String district, List<Float> rangePrice, Integer num, Integer count) {

        dataFiltrada.clear();

        if (department.equals("Todos")) {
            validDepartment = true;
        } else {
            validDepartment = false;
        }

        if (province.equals("Todos")) {
            validProvince = true;
        } else {
            validProvince = false;
        }

        if (district.equals("Todos")) {
            validDistrict = true;
        } else {
            validDistrict = false;
        }

        System.out.println("validdd" + validDepartment + "   " + validProvince + "  " + validDistrict);

        for(int i = 0 ; i < listPosts.size() ; i++) {

            if (validDepartment != true) {
                if (validProvince != true) {
                    if (validDistrict != true) {
                        if(listPosts.get(i).getDepartment().toLowerCase().equals(department.toLowerCase()) &&
                                listPosts.get(i).getProvince().toLowerCase().equals(province.toLowerCase()) &&
                                listPosts.get(i).getDistrict().toLowerCase().equals(district.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    } else {
                        if(listPosts.get(i).getDepartment().toLowerCase().equals(department.toLowerCase()) &&
                                listPosts.get(i).getProvince().toLowerCase().equals(province.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    }
                } else {
                    if (validDistrict != true) {
                        if(listPosts.get(i).getDepartment().toLowerCase().equals(department.toLowerCase()) &&
                                listPosts.get(i).getDistrict().toLowerCase().equals(district.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    } else {
                        if(listPosts.get(i).getDepartment().toLowerCase().equals(department.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    }

                }
            } else {
                if (validProvince != true) {
                    if (validDistrict != true) {
                        if(listPosts.get(i).getProvince().toLowerCase().equals(province.toLowerCase()) &&
                                listPosts.get(i).getDistrict().toLowerCase().equals(district.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    } else {
                        if(listPosts.get(i).getProvince().toLowerCase().equals(province.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    }
                } else {
                    if (validDistrict != true) {
                        if(listPosts.get(i).getDistrict().toLowerCase().equals(district.toLowerCase()) &&
                                listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    } else {
                        if(listPosts.get(i).getPrice() > rangePrice.get(0) &&
                                listPosts.get(i).getPrice() < rangePrice.get(1) )  {
                            if (num <= 2 && listPosts.get(i).getBathroomQuantity() == num) {
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            } else if (num > 2 && listPosts.get(i).getBathroomQuantity() >= num){
                                if (count <= 2 && listPosts.get(i).getRoomQuantity() == count) {
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                } else if (count > 2 && listPosts.get(i).getRoomQuantity() >= count){
                                    dataFiltrada.add(listPosts.get(i));
                                    continue;
                                }
                            }
                        }
                    }
                }
            }

        }

        System.out.println("FILTERRR");
        System.out.println(dataFiltrada.size());

        if (confirmActivity == false) {
            answerActivity = true;
        }

        System.out.println("FINN" + answerActivity.getClass().getSimpleName());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("lista", dataFiltrada);
        intent.putExtra("changeBoolean", answerActivity);
        startActivity(intent);
    }

}