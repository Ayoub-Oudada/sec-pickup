package com.secpickup.android_front.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.secpickup.android_front.MAJboutons;

import com.secpickup.android_front.R;
import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.Situation;

import java.util.List;

public class Assistantepageradapter extends PagerAdapter {


    private List<Eleve> children;
    private Context context;
    private OnButtonClickListener onButtonClickListener;
    private static final int ITEMS_PER_PAGE = 5;

    private final MAJboutons buttonColorUpdater;

    public Assistantepageradapter(Context context, List<Eleve> children) {
        this.context = context;
        this.children = children;
        this.buttonColorUpdater = new MAJboutons(context);
    }

    @Override
    public int getCount() {
        return (int) Math.ceil((double) children.size() / ITEMS_PER_PAGE);
    }




    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Utilisez un seul View pour la page
        View view = inflater.inflate(R.layout.chargingitemsinviewpager, container, false);

        // Obtenez la ListView dans la page
        ListView listView = view.findViewById(R.id.listView);

        // Affichez plusieurs élèves dans la ListView
        int startIndex = position * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, children.size());
        List<Eleve> sublist = children.subList(startIndex, endIndex);

        // Créez un adaptateur pour la ListView
        ChildListAdapter listAdapter = new ChildListAdapter(context, sublist);

        // Définissez l'adaptateur de la ListView
        listView.setAdapter(listAdapter);

        // Ajoutez le View à ViewPager
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        //this.onButtonClickListener = listener;
    }
    public interface OnButtonClickListener {
        void onButtonClick(int position, String situation);
    }
}
