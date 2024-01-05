package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class toolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Activer la flèche de retour
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Définir le gestionnaire de clic sur la flèche de retour
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
