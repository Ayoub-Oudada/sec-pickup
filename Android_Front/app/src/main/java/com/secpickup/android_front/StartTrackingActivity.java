package com.secpickup.android_front;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistantebus_mainactivity);

        // Assumez que vous avez un bouton avec l'ID "btnStartService" dans votre layout
        Button startServiceButton = findViewById(R.id.btnStartService);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez le service en cliquant sur le bouton
                Intent serviceIntent = new Intent(StartTrackingActivity.this, LocationTrackingService.class);
                startService(serviceIntent);
            }
        });
    }
}
