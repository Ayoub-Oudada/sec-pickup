package com.secpickup.android_front.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.secpickup.android_front.MAJboutons;
import com.secpickup.android_front.R;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.EleveDto;
import com.secpickup.android_front.modele.Situation;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildListAdapter extends ArrayAdapter<Eleve> {

    private Context context;
    private List<Eleve> elevesList;

    private MAJboutons maJboutons;


    public ChildListAdapter(Context context, List<Eleve> elevesList) {
        super(context, 0, elevesList);
        this.context = context;
        this.elevesList = elevesList;
        this.maJboutons = new MAJboutons(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Code pour créer la vue d'un élève dans la ListView
        // Utilisez la convertView pour améliorer les performances


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_child, parent, false);
        }

        Eleve eleve = getItem(position);

        // Obtenez les éléments de la vue
        TextView nomTextView = convertView.findViewById(R.id.nomTextView);
        Button btnRecuperer = convertView.findViewById(R.id.buttonRecuperer);
        Button btnDeposer = convertView.findViewById(R.id.buttonDeposer);
        Button btnAbsent = convertView.findViewById(R.id.buttonAbsent);

        // Remplissez les éléments avec les données de l'élève
        if (eleve != null) {
            nomTextView.setText(eleve.getNom());

            // Vous devrez ajuster cela en fonction de la structure de votre modèle Eleve
            String situation = eleve.getSituation();
            // ...


            // Assurez-vous d'ajuster les boutons en fonction de la situation
            maJboutons.updateButtonColors(btnRecuperer,btnDeposer,btnAbsent,situation);

            // ...

            // Ajoutez des écouteurs de clic pour les boutons
            btnRecuperer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitService retrofitServiceRec = new RetrofitService();
                    EleveApi eleveApi = retrofitServiceRec.getRetrofit().create(EleveApi.class);

                    eleveApi.update(eleve.getId(),new EleveDto("RECUPPERER")).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(v.getContext(), "onResponse Test ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(v.getContext(), "onFailure Test "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //varTest = eleveApi.searchParentByUsernameType("parent", UserAccountType.valueOf("PARENT"));
                    Toast.makeText(v.getContext(), "Votre enfant est recupere :( "+eleve.getId(), Toast.LENGTH_SHORT).show();
                    maJboutons.updateButtonColors(btnRecuperer,btnDeposer,btnAbsent,"RECUPPERER");

                }
            });

            // btn absent
            btnAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    EleveApi eleveApi = retrofitServiceAbs.getRetrofit().create(EleveApi.class);

                    eleveApi.update(eleve.getId(),new EleveDto("ABSENT")).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(v.getContext(), "onResponse Test ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(v.getContext(), "onFailure Test "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //varTest = eleveApi.searchParentByUsernameType("parent", UserAccountType.valueOf("PARENT"));
                    Toast.makeText(v.getContext(), "Votre enfant est absent :( "+eleve.getId(), Toast.LENGTH_SHORT).show();
                    maJboutons.updateButtonColors(btnRecuperer,btnDeposer,btnAbsent,"ABSENT");

                }
            });


            //btn deposer
            btnDeposer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitService retrofitServiceDep = new RetrofitService();
                    EleveApi eleveApi = retrofitServiceDep.getRetrofit().create(EleveApi.class);

                    eleveApi.update(eleve.getId(),new EleveDto("DEPOSER")).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(v.getContext(), "onResponse Test ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(v.getContext(), "onFailure Test "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //varTest = eleveApi.searchParentByUsernameType("parent", UserAccountType.valueOf("PARENT"));
                    Toast.makeText(v.getContext(), "Votre enfant est deposé :( "+eleve.getId(), Toast.LENGTH_SHORT).show();
                    maJboutons.updateButtonColors(btnRecuperer,btnDeposer,btnAbsent,"DEPOSER");

                }
            });

        }

        return convertView;
    }
}
