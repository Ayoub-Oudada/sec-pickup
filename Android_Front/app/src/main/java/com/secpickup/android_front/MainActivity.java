package com.secpickup.android_front;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.AssistanteApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements LoadTrajet.LoadTrajetCallback,LoadTrajet.LoadPositionCallback,LoadTrajet.LoadDistanceCallback{

    private static final String TARGET_URL = "http://localhost:8080/api/users";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendDataButton = findViewById(R.id.sendDataButton);
        textView = findViewById(R.id.textViewId);


         //LoadStudent loadStudent= new LoadStudent();
        LoadTrajet loadTrajet =new LoadTrajet();
        //String username="AssistanteB";
        String username="Alpha";
        String type="ASSISTANTE";
        String username1="Alpha";
        String username2="parent1";



        // loadStudent.loadEleves(username,type,this);

        //loadTrajet.loadPosition (username, this);
        loadTrajet.loadTrajet (username, this);
        loadTrajet.loadPosition (username, this);
        loadTrajet.loadDistance (username1,username2, this);





             sendDataButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     String dataToSend = "Hello from Android App";
                     Toast.makeText(MainActivity.this, dataToSend, Toast.LENGTH_SHORT).show();


                 }
             });
         }

    @Override
    public void onTrajetLoaded(List<Positions> trajet) {
        String username="Alpha";
        //String type="ASSISTANTE";
         List<LatLng> latLngs = new ArrayList<>();
        LatLng latLng;
        for (int i=0; i<trajet.size();i++){
        latLng=new LatLng(trajet.get(i).getLatitude(),trajet.get(i).getLongitude());
         latLngs.add(latLng);
        }
       // textView.setText(username+" Latitude-Longitude :"+latLngs.toString());




    }

    @Override
    public void onFailedToLoadTrajet() {

    }

    @Override
    public void onPositionLoaded(Positions position) {
        String username="Alpha";
        //String type="ASSISTANTE";
        LatLng latLng=new LatLng(position.getLatitude(),position.getLongitude());
        //textView.setText(username+" Latitude-Longitude de LastPoint :"+latLng);
    }

    @Override
    public void onFailedToLoadPosition() {

    }

    @Override
    public void onDistanceLoaded(Double distance) {
        String username1="Alpha";
        String username2="parent1";
        //String type="ASSISTANTE";
        //LatLng latLng=new LatLng(position.getLatitude(),position.getLongitude());
        //textView.setText("La distance entre "+username1+" et "+username2 +" :"+distance+"metre(s)");
        if(distance<20){

            textView.setText("La distance entre "+username1+" et "+username2 +" :"+distance+"metre(s)  ALERTE ROUGE");
        }else{
            textView.setText("La distance entre "+username1+" et "+username2 +" :"+distance+"metre(s)  ALERTE VERTE");
        }
    }

    @Override
    public void onFailedToLoadDistance() {

    }
}