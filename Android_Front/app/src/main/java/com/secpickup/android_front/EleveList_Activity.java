package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.fragments.ChangerMDPFragment;
import com.secpickup.android_front.fragments.ContacterEcoleFragment;
import com.secpickup.android_front.fragments.DemanderPiecesFragment;
import com.secpickup.android_front.fragments.HomeFragment;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleveList_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private EleveAdapter eleveAdapter;
    private DrawerLayout drawerLayout;


    String username;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }

        recyclerView = findViewById(R.id.eleveList_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        loadEleves();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_demo);
        }

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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_demo :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_demander_pieces:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DemanderPiecesFragment()).commit();
                break;
            case R.id.nav_contacter_ecole:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContacterEcoleFragment()).commit();
                break;
            case R.id.nav_chnager_mdp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChangerMDPFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
