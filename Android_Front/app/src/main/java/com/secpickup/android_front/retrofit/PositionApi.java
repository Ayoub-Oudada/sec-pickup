package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.Positions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PositionApi {

    @POST("/api/position")
    Call<Void> registerPosition(@Body Positions positions);

     @GET("/api/position/trajet")
    Call<List<Positions>> GetTrajet(@Query("username") String username);

    @GET("/api/position/last")
    Call<Positions> GetPosition(@Query("username") String username);

<<<<<<< HEAD
    @GET("/api/position/distance")
    Call<Double> GetDistance(@Query("username1") String username1, @Query("username2") String username2);

=======
>>>>>>> dev


}
