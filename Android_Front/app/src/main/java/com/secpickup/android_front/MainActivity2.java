package com.secpickup.android_front;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.secpickup.android_front.fragments.ChangerMDPFragment;
import com.secpickup.android_front.fragments.ContacterEcoleFragment;
import com.secpickup.android_front.fragments.DemanderPiecesFragment;
import com.secpickup.android_front.fragments.HomeFragmentAssistante;
import com.secpickup.android_front.fragments.HomeFragmentParent;
import com.secpickup.android_front.fragments.SignalerAnomalieFragment;
import com.secpickup.android_front.fragments.VisualiserTrajetFragment;
import com.secpickup.android_front.retrofit.RetrofitService;

import okhttp3.ResponseBody;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private long downloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            if ("ASSISTANTE".equals(getIntent().getStringExtra("type"))) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragmentAssistante()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragmentParent()).commit();
            }
            navigationView.setCheckedItem(R.id.nav_home);

            if ("ASSISTANTE".equals(getIntent().getStringExtra("type"))) {
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_demander_pieces).setVisible(false);
                menu.findItem(R.id.nav_contacter_ecole).setVisible(false);
                menu.findItem(R.id.distance_bus).setVisible(false);
                menu.findItem(R.id.nav_Signaler_Annomalie).setVisible(true);
            } else {
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.distance_bus).setVisible(true);
                menu.findItem(R.id.nav_Signaler_Annomalie).setVisible(false);
            }
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if ("ASSISTANTE".equals(getIntent().getStringExtra("type"))) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragmentAssistante()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragmentParent()).commit();
                }
                break;

            case R.id.nav_demander_pieces:
                if ("PARENT".equals(getIntent().getStringExtra("type"))) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DemanderPiecesFragment()).commit();
                }
                break;

            case R.id.nav_contacter_ecole:
                if ("PARENT".equals(getIntent().getStringExtra("type"))) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContacterEcoleFragment()).commit();
                }
                break;

//            case R.id.nav_Visualier_Trajet:
//                if ("PARENT".equals(getIntent().getStringExtra("type"))) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VisualiserTrajetFragment()).commit();
//                }
//                break;

            case R.id.nav_Signaler_Annomalie:
                if ("ASSISTANTE".equals(getIntent().getStringExtra("type"))) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SignalerAnomalieFragment()).commit();
                }
                break;

            case R.id.nav_chnager_mdp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChangerMDPFragment()).commit();
                break;

            case R.id.nav_demo:
                startDownload("https://drive.usercontent.google.com/u/0/uc?id=1jmR7hsLLLg1SBSIFj2QhdKNPDXF8w_me&export=download");
                break;


            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                logout();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout() {
        Intent intent = new Intent(MainActivity2.this, activity_login.class);
        startActivity(intent);
        finish();
    }


    private void savePdfFile(ResponseBody body) {
        Toast.makeText(MainActivity2.this, "PDF Downloaded successfully", Toast.LENGTH_SHORT).show();
    }
    private void startDownload(String fileUrl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
        request.setTitle("Demo-Secpickup");
        request.setDescription("Téléchargement en cours");

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Demo-Secpickup.pdf");

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        downloadId = downloadManager.enqueue(request);

        registerReceiver(new DownloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    // BroadcastReceiver pour gérer la fin du téléchargement
    private class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == downloadId) {
            }
        }
    }
}