package com.secpickup.android_front;

import android.widget.Toast;

import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.AssistanteApi;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadStudent {


    public interface LoadStudentCallback {
        void onStudentListLoaded(List<Eleve> eleveList);
        void onFailedToLoadStudents();
    }

    List<Eleve> eleveList=null;
    public List<Eleve> loadEleves(String username, String type, LoadStudentCallback loadStudentCallback) {

        RetrofitService retrofitService = new RetrofitService();
        AssistanteApi assistanteApi = retrofitService.getRetrofit().create(AssistanteApi.class);
        assistanteApi.SearchStudentByAssitante(username, UserAccountType.valueOf(type))
                .enqueue(new Callback<List<Eleve>>() {


                    @Override
                    public void onResponse(Call<List<Eleve>> call, Response<List<Eleve>> response) {
                        if (response.isSuccessful()) {
                             eleveList = response.body();
                            loadStudentCallback.onStudentListLoaded(eleveList);
                            // a =eleveList.size();
                             System.err.println(eleveList.size());






                        } else {
                            //Toast.makeText(EleveList_Activity.this, "Failed to load eleves", Toast.LENGTH_SHORT).show();
                            System.out.println("Failed to load eleves");
                            loadStudentCallback.onFailedToLoadStudents();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Eleve>> call, Throwable t) {
                        //Toast.makeText(EleveList_Activity.this, "Failed to load eleves", Toast.LENGTH_SHORT).show();
                        System.out.println("Failed to load eleves");
                    }
                });


    return eleveList;
    }
}
