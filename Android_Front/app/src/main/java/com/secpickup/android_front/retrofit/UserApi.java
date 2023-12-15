package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/Auth")
    Call<User> findUser(@Body User user);

    @POST("/api")
    Call<User> registerNewUser(@Body User user);


}
