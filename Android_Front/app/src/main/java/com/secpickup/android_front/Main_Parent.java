package com.secpickup.android_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.secpickup.android_front.fragments.Fragment_1_MainParent;
import com.secpickup.android_front.fragments.Fragment_2_MainParent;
import com.secpickup.android_front.fragments.Fragment_3_MainParent;

public class Main_Parent extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);


        tabLayout.setupWithViewPager(viewPager);

        Adapter_main_parent adapterMainParent = new Adapter_main_parent(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapterMainParent.addFragment(new Fragment_1_MainParent(), "my children's");
        adapterMainParent.addFragment(new Fragment_2_MainParent(), "Trajet");
        adapterMainParent.addFragment(new Fragment_3_MainParent(), "Settings");
        viewPager.setAdapter(adapterMainParent);

    }
}