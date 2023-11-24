package com.secpickup.android_front;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TARGET_URL = "http://192.168.11.107:8080/api/users";

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
            String result = "";
            HttpURLConnection urlConnection = null;
            try {
                // Your server URL
                URL url = new URL("http://192.168.11.107:8080/api/users");

                // Establish connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                // Read the response
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
                System.out.println(result);

                // Close InputStream and disconnect
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

    }
}
