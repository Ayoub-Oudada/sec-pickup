package com.secpickup.android_front.retrofit;

import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AssistanteApi {

    @POST("api/assistante/find")
    Call<List<Eleve>> SearchStudentByAssitante(
            @Query("username") String username,
            @Query("type") UserAccountType type
    );

}
