package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.Ecole;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EcoleApi {
    @GET("/api/ecoles/{id}")
    Call<Ecole> getEcoleById(@Path("id") Long id);
}
