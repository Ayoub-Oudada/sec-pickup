package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleveList_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EleveAdapter eleveAdapter;

    String username;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        MaterialToolbar toolbar = findViewById(R.id.toolBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EleveList_Activity.this, "Vous avez cliqué sur le menu de navigation", Toast.LENGTH_SHORT).show();

            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menuSearch) {
                    Toast.makeText(EleveList_Activity.this, "Vous avez cliqué sur le menu recherche", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menuSettings) {
                    Toast.makeText(EleveList_Activity.this, "Vous avez cliqué sur le menu paramètre", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }

        recyclerView = findViewById(R.id.eleveList_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        loadEleves();
    }

    private void loadEleves() {
        RetrofitService retrofitService = new RetrofitService();
        EleveApi eleveApi = retrofitService.getRetrofit().create(EleveApi.class);
        eleveApi.searchParentByUsernameType(username, UserAccountType.valueOf(type))
                .enqueue(new Callback<List<Eleve>>() {
                    @Override
                    public void onResponse(Call<List<Eleve>> call, Response<List<Eleve>> response) {
                        if (response.isSuccessful()) {
                            List<Eleve> eleveList = response.body();
                            eleveAdapter = new EleveAdapter(eleveList);
                            recyclerView.setAdapter(eleveAdapter);
                        } else {
                            Toast.makeText(EleveList_Activity.this, "Failed to load eleves", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Eleve>> call, Throwable t) {
                        Toast.makeText(EleveList_Activity.this, "Failed to load eleves", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
