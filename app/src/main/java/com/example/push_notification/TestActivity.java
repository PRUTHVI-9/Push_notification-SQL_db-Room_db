package com.example.push_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class TestActivity extends AppCompatActivity {
    public NonSwipeableViewPager slider;
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        slider = findViewById(R.id.slider);
        tab = findViewById(R.id.tab);
        slider.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        slider.setAdapter(adapter);
        slider.setOffscreenPageLimit(3);

        tab.setupWithViewPager(slider);
        for (int i = 0; i < tab.getTabCount(); i++) {
            switch (i) {
                case 0:
                    tab.getTabAt(i).setIcon(R.drawable.ic_baseline_home_24);
                    break;
                case 1:
                    tab.getTabAt(i).setIcon(R.drawable.ic_baseline_message_24);
                    break;
                case 2:
                    tab.getTabAt(i).setIcon(R.drawable.ic_baseline_contacts_24);
                    break;
            }
        }
    }
}