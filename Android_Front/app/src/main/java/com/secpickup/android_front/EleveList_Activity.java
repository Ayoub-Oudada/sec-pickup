package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleveList_Activity extends AppCompatActivity implements LoadTrajet.LoadTrajetCallback,LoadTrajet.LoadPositionCallback,LoadTrajet.LoadDistanceCallback{

    private RecyclerView recyclerView;
    private EleveAdapter eleveAdapter;

    //////////////loc////////
    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private Handler handler;
    private TextView textView;
    private LocationListener locationListener;


    //////////////////////

    String username;
    String type;
    String username1;
    String username2="AssistanteB";

    Button buttonBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);



        ////////////////////loc///////////////////
        buttonBus = findViewById(R.id.buttonBus);
        textView = findViewById(R.id.textViewId);
        FloatingActionButton startServiceButton = findViewById(R.id.btnStartService);
        handler = new Handler();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LoadTrajet loadTrajet =new LoadTrajet();
        loadTrajet.loadTrajet ("AssistanteB", this);
        loadTrajet.loadPosition ("AssistanteB", this);

        /////////////////////////////

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

        loadTrajet.loadDistance(username,"AssistanteB",this);

        recyclerView = findViewById(R.id.eleveList_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        loadEleves();

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez le service en cliquant sur le bouton
                //startLocationUpdates();
                Toast.makeText(EleveList_Activity.this, "Vous avez cliqué sur Maps", Toast.LENGTH_SHORT).show();
                Intent intent1= new Intent(getApplicationContext(), Visualiser_Trajet.class);
                //intent.putExtra("username", username);
                //intent.putExtra("type", type);
                startActivity(intent1);
                //finish();
            }
        });


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
                        Toast.makeText(EleveList_Activity.this, "Failed to load eleves: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });
    }


    @Override
    public void onTrajetLoaded(List<Positions> trajet) {

        String username="AssistanteB";
        List<LatLng> latLngs = new ArrayList<>();
        LatLng latLng;
        for (int i=0; i<trajet.size();i++){
            latLng=new LatLng(trajet.get(i).getLatitude(),trajet.get(i).getLongitude());
            latLngs.add(latLng);

        }
        //textView.setText(username+" Latitude-Longitude :"+latLngs.toString());
    }


    @Override
    public void onFailedToLoadTrajet() {

    }

    @Override
    public void onPositionLoaded(Positions position) {
        String username="AssistanteB";
        //String type="ASSISTANTE";
        LatLng latLng=new LatLng(position.getLatitude(),position.getLongitude());
        //textView.setText(username+" Latitude-Longitude de LastPoint :"+latLng);
    }

    @Override
    public void onFailedToLoadPosition() {


    }

    @Override
    public void onDistanceLoaded(Double distance) {
        String username2="AssistanteB";
        String username1=username;
        //String type="ASSISTANTE";
        //LatLng latLng=new LatLng(position.getLatitude(),position.getLongitude());

        textView.setText("La distance entre "+username1+" et le Bus est "+distance+"metre(s)");
        buttonBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez le service en cliquant sur le bouton
                //startLocationUpdates();
                Toast.makeText(EleveList_Activity.this,"La distance qui vous sépare du Bus est "+distance+"  mètre(s)", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onFailedToLoadDistance() {

    }
}
