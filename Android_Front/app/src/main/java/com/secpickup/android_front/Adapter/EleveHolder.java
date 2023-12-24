package com.secpickup.android_front.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.secpickup.android_front.EleveList_Activity;
import com.secpickup.android_front.MAJboutons;
import com.secpickup.android_front.R;

public class EleveHolder extends RecyclerView.ViewHolder {

    TextView name, prenom, cne;
    Button btnDeposer,btnRecuperer,btnAbsent;


    public EleveHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.eleveListItem_name);
        prenom = itemView.findViewById(R.id.eleveListItem_prenom);
        cne = itemView.findViewById(R.id.eleveListItem_cne);
        btnDeposer = itemView.findViewById(R.id.buttonDeposer);
        btnRecuperer = itemView.findViewById(R.id.buttonRecuperer);
        btnAbsent = itemView.findViewById(R.id.buttonAbsent);

    }
}
