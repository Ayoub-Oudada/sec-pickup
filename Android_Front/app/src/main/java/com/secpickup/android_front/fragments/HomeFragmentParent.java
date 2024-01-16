package com.secpickup.android_front.fragments;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.secpickup.android_front.Adapter.EleveAdapter;
import com.secpickup.android_front.EleveList_Activity;
import com.secpickup.android_front.LoadTrajet;
import com.secpickup.android_front.Visualiser_Trajet;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.fragment.app.Fragment;
import com.secpickup.android_front.R;



public class HomeFragmentParent extends Fragment implements LoadTrajet.LoadTrajetCallback,LoadTrajet.LoadPositionCallback,LoadTrajet.LoadDistanceCallback {

    private RecyclerView recyclerView;
    private EleveAdapter eleveAdapter;

    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private Handler handler;
    private TextView textView;
    private LocationListener locationListener;

    String username;
    String type;

    String username1;
    String username2="AssistanteB";
    Button buttonBus;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize location and other necessary components here
        handler = new Handler();
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        LoadTrajet loadTrajet = new LoadTrajet();
        loadTrajet.loadTrajet("AssistanteB", this);
        loadTrajet.loadPosition("AssistanteB", this);

        Intent intent = requireActivity().getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.eleveList_recyclerView);
        textView = view.findViewById(R.id.textViewId);
        buttonBus = view.findViewById(R.id.buttonBus1);


        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        loadEleves();

        FloatingActionButton startServiceButton = view.findViewById(R.id.btnStartService);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Vous avez cliqué sur Maps", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(requireContext(), Visualiser_Trajet.class);
                startActivity(intent1);
            }
        });

        return view;
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
                            Toast.makeText(getActivity(), "Failed to load eleves", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Eleve>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed to load eleves: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onFailedToLoadTrajet() {
        // Handle failure to load trajectory data
    }
    @Override
    public void onFailedToLoadPosition() {


    }

    @Override
    public void onPositionLoaded(Positions position) {
        String username="AssistanteB";
        //String type="ASSISTANTE";
        LatLng latLng=new LatLng(position.getLatitude(),position.getLongitude());
        //textView.setText(username+" Latitude-Longitude de LastPoint :"+latLng);
    }


    @Override
    public void onDistanceLoaded(Double distance) {
//        String username2 = "AssistanteB";
//        String username1 = username;
//
//        textView.setText("La distance entre " + username1 + " et le Bus est " + distance + " mètre(s)");
//
//        buttonBus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Log to check if the button click event is triggered
//
//                // Display a toast to check if the toast is being shown
//                Toast.makeText(requireContext(), "Vous avez cliqué sur Maps", Toast.LENGTH_LONG).show();
//            }
//        });
        buttonBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Vous avez cliqué sur Maps", Toast.LENGTH_SHORT).show();
                //Intent intent1 = new Intent(requireContext(), Visualiser_Trajet.class);
                //startActivity(intent1);
            }
        });
    }



    @Override
    public void onFailedToLoadDistance() {

    }
}
