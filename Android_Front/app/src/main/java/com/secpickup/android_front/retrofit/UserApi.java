package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @POST("/api/Auth")
    Call<User> findUser(@Body User user);

    @POST("/api")
    Call<User> registerNewUser(@Body User user);
    @PUT("/api/{userId}/updatePassword")
    Call<Void> updateUserPassword(
            @Path("userId") Long userId,
            @Query("oldPassword") String oldPassword,
            @Query("newPassword") String newPassword
    );

    @POST("/api/forgot-password")
    Call<Void> forgotPassword(@Query("username") String username);



}
