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

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TARGET_URL = "http://192.168.1.40:8080/api/users";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendDataButton = findViewById(R.id.sendDataButton);
        textView = findViewById(R.id.textViewId);
        Intent intent = getIntent();
        // Vérifiez si l'intention a une valeur associée à la clé spécifique
         if (intent.hasExtra("username") & intent.hasExtra("type")) {
             // Récupérez la valeur à partir de l'intention
             String username = intent.getStringExtra("username");
             String type = intent.getStringExtra("type");

             // Maintenant, vous avez la valeur dans la variable valeurRecue
             // Faites ce que vous voulez avec cette valeur
             // Par exemple, affichez-la dans un TextView
             textView.setText(username+" "+type);
             // }
             sendDataButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     // Récupérez vos données à envoyer
                     String dataToSend = "Hello from Android App";
                     Toast.makeText(MainActivity.this, dataToSend, Toast.LENGTH_SHORT).show();


                     // Exécutez la tâche asynchrone pour envoyer les données
                     // new SendDataAsyncTask().execute(dataToSend);
                 }
             });
         }}}