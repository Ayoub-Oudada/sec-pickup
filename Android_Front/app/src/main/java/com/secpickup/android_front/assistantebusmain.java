package com.secpickup.android_front;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.secpickup.android_front.Adapter.Assistantepageradapter;
import com.secpickup.android_front.modele.Eleve;

import java.util.ArrayList;
import java.util.List;

public class assistantebusmain extends AppCompatActivity implements LoadStudent.LoadStudentCallback {

    private List<Eleve> childrenList;
    private Assistantepageradapter pagerAdapter;
    String username;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistantebus_mainactivity);



        childrenList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("type")) {
            username = intent.getStringExtra("username");
            type = intent.getStringExtra("type");
        }



        LoadStudent loadStudent = new LoadStudent();
        loadStudent.loadEleves(username,type,this);
        pagerAdapter = new Assistantepageradapter(this, childrenList);
        pagerAdapter.setOnButtonClickListener(new Assistantepageradapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position, String situation) {

            }
        });

        ViewPager viewPager = findViewById(R.id.eleveList_recyclerView);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onStudentListLoaded(List<Eleve> eleveList) {
        childrenList.clear();
        for (Eleve eleve : eleveList) {
            if (eleve != null) {
                childrenList.add(eleve);
            }
        }        pagerAdapter.notifyDataSetChanged();


    }

    @Override
    public void onFailedToLoadStudents() {

    }
}
