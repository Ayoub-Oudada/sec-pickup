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
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Parent;
import com.secpickup.android_front.modele.Situation;



import java.util.List;
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
        Situation situation = eleve.getSituation();


        if (situation.getId() != null) {
            holder.cne.setText(situation.getSituation()+" ");
            maJboutons.updateButtonColors(holder.btnRecuperer,holder.btnDeposer, holder.btnAbsent,situation.getSituation()+"");


        } else {
            holder.cne.setText("Situation non définie");
        }
    }

    @Override
    public int getItemCount() {
        return eleveList.size();
    }
}