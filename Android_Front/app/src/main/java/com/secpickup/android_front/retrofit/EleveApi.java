package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Parent;
import com.secpickup.android_front.modele.User;
import com.secpickup.android_front.modele.UserAccountType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EleveApi {

    @POST("api/parent/find")
    Call<List<Eleve>> searchParentByUsernameType(
            @Query("username") String username,
            @Query("type") UserAccountType type
    );

}
