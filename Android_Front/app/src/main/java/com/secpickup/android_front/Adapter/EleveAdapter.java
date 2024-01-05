package com.secpickup.android_front.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.secpickup.android_front.EleveList_Activity;
import com.secpickup.android_front.MAJboutons;
import com.secpickup.android_front.R;
import com.secpickup.android_front.activity_login;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.EleveDto;
import com.secpickup.android_front.modele.Parent;
import com.secpickup.android_front.modele.Situation;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.EleveApi;
import com.secpickup.android_front.retrofit.RetrofitService;
//import com.secpickup.android_front.modele.Situations;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleveAdapter extends RecyclerView.Adapter<EleveHolder>{

    private List<Eleve> eleveList;

    public EleveAdapter(List<Eleve> eleveList) {
        this.eleveList = eleveList;
    }

    MAJboutons maJboutons;
    @NonNull
    @Override
    public EleveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_eleve_item, parent, false);
        maJboutons = new MAJboutons(view.getContext());
        return new EleveHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull EleveHolder holder, int position) {
        Eleve eleve = eleveList.get(position);

        holder.name.setText(eleve.getNom());
        holder.prenom.setText(eleve.getPrenom());
        String situation = String.valueOf(eleve.getSituation());

        if (situation != null) {
            holder.cne.setText(situation+"  Id:"+eleve.getId());
            maJboutons.updateButtonColors(holder.btnRecuperer,holder.btnDeposer, holder.btnAbsent,situation);
     ///////////////////
          //Long id = (long)eleve.getId();
            // Add OnClickListener to your buttons here
            holder.btnDeposer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // Handle button click for "Deposer"

                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    EleveApi eleveApi = retrofitServiceAbs.getRetrofit().create(EleveApi.class);

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

                    Toast.makeText(v.getContext(), "Votre enfant est depose :) "+eleve.getId(), Toast.LENGTH_SHORT).show();
                    maJboutons.updateButtonColors(holder.btnRecuperer,holder.btnDeposer, holder.btnAbsent,"DEPOSER");

                }
            });

            holder.btnRecuperer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click for "Recuperer"
                    RetrofitService retrofitServiceAbs = new RetrofitService();
                    EleveApi eleveApi = retrofitServiceAbs.getRetrofit().create(EleveApi.class);

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
                    Toast.makeText(v.getContext(), "Votre enfant est Recupere :) "+eleve.getId(), Toast.LENGTH_SHORT).show();
                    maJboutons.updateButtonColors(holder.btnRecuperer,holder.btnDeposer, holder.btnAbsent,"RECUPPERER");

                }
            });

            holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click for "Absent"

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
                    maJboutons.updateButtonColors(holder.btnRecuperer,holder.btnDeposer, holder.btnAbsent,"ABSENT");
                }
            });

     ///////////////////

        } else {
            holder.cne.setText("Situation non définie");
        }
    }

    @Override
    public int getItemCount() {
        return eleveList.size();
    }
}