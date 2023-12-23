package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Parent;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;
import com.secpickup.android_front.retrofit.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleveList_Activity extends AppCompatActivity {
    //private RecyclerView recyclerView;
    private RecyclerView  recyclerView;


    String username; // = "parent";
    String type ; //= "PARENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleve_list);

        Intent intent = getIntent();
        if (intent.hasExtra("username") & intent.hasExtra("type")) {

            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");

        }

        recyclerView = findViewById(R.id.eleveList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadEleves();

    }
    private void loadEleves() {
        RetrofitService retrofitService = new RetrofitService();
        EleveApi employeApi = retrofitService.getRetrofit().create(EleveApi.class);
        employeApi.searchParentByUsernameType(username, UserAccountType.valueOf(type))
                .enqueue(new Callback<List<Eleve>>() {
                    @Override
                    public void onResponse(Call<List<Eleve>> call, Response<List<Eleve>> response) {
                        populateListView(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<Eleve>> call, Throwable t) {
                        Toast.makeText(EleveList_Activity.this, "Failed to load eleves", Toast.LENGTH_SHORT).show();

                    }
                });
    }
        private void populateListView(List<Eleve> eleveList ) {
            EleveAdapter eleveAdapter = new EleveAdapter(eleveList);
            recyclerView.setAdapter(eleveAdapter);

    }
}