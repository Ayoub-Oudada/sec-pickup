package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.EleveDto;
import com.secpickup.android_front.modele.UserAccountType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EleveApi {

    @POST("/api/parent/find")
    Call<List<Eleve>> searchParentByUsernameType(
            @Query("username") String username,
            @Query("type") UserAccountType type
    );

    //@PUT("/api/eleves/update/{eleveId}")
   // public void update(@Path("eleveId") Long eleveId,
        //               @Body String eleveDto);


    @PUT("/api/eleves/update/{eleveId}")
    Call<Void> update(@Path("eleveId") Long eleveId, @Body EleveDto situation);
}



