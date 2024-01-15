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
import java.util.List;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.AssistanteApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements LoadStudent.LoadStudentCallback{

    private static final String TARGET_URL = "http://localhost:8080/api/users";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendDataButton = findViewById(R.id.sendDataButton);
        textView = findViewById(R.id.textViewId);
        /*Intent intent = getIntent();
        // Vérifiez si l'intention a une valeur associée à la clé spécifique
         if (intent.hasExtra("username") & intent.hasExtra("type")) {
             // Récupérez la valeur à partir de l'intention
             String username = intent.getStringExtra("username");
             String type = intent.getStringExtra("type");
*/
             // Maintenant, vous avez la valeur dans la variable valeurRecue
             // Faites ce que vous voulez avec cette valeur
             // Par exemple, affichez-la dans un TextView

        LoadStudent loadStudent= new LoadStudent();
        String username="AssistanteB";
        String type="ASSISTANTE";



        loadStudent.loadEleves(username,type,this);


        /////////////////////////////////////////////////



        /////////////////////////////////////////////////

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
         }

    @Override
    public void onStudentListLoaded(List<Eleve> eleveList) {
        String username="AssistanteB";
        String type="ASSISTANTE";
        textView.setText(username+" "+type+ eleveList.get(0).getNom());

    }

    @Override
    public void onFailedToLoadStudents() {

    }
}