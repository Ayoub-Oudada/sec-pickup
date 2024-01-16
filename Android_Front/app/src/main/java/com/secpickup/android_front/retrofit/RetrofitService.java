package com.secpickup.android_front.retrofit;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
<<<<<<< HEAD
                .baseUrl("http://100.89.17.45:8080/")
=======
<<<<<<< HEAD
                .baseUrl("http://100.89.17.45:8080/")
=======
                .baseUrl("http://100.89.17.17:8080/")
>>>>>>> dev
>>>>>>> 7650df02f568a954f51abff8bcab7178e258404f
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit(){
       return retrofit;
    }
}
