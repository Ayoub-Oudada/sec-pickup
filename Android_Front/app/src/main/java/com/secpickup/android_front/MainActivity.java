package com.secpickup.android_front;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TARGET_URL = "http://192.168.11.105:8000/endpoint";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendDataButton = findViewById(R.id.sendDataButton);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérez vos données à envoyer
                String dataToSend = "Hello from Android App";
                Toast.makeText(MainActivity.this, dataToSend, Toast.LENGTH_SHORT).show();
                // Exécutez la tâche asynchrone pour envoyer les données
                new SendDataAsyncTask().execute(dataToSend);
            }
        });
    }

    private class SendDataAsyncTask extends AsyncTask<String, Void, Void> {

        @Nullable
        @Override
        protected Void doInBackground(String... params) {
            try {
                // Créez l'URL de destination
                URL url = new URL(TARGET_URL);

                // Ouvrez une connexion HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Configurez la connexion pour une requête POST
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Obtenez les données à envoyer
                String dataToSend = params[0];

                // Écrivez les données dans le flux de sortie de la connexion
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = dataToSend.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                // Obtenez le code de réponse
                int responseCode = connection.getResponseCode();

                // Affichez le code de réponse dans les logs (pour le débogage)
                Log.d("SendDataAsyncTask", "Response Code: " + responseCode);

                // Fermez la connexion
                connection.disconnect();

            } catch (Exception e) {
                // Affichez les erreurs dans les logs (pour le débogage)
                Log.e("SendDataAsyncTask", "Error sending data", e);
                e.printStackTrace();
            }

            return null;
        }
    }
}
