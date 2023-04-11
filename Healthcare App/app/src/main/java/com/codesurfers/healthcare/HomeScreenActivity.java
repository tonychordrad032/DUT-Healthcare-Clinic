package com.codesurfers.healthcare;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.codesurfers.healthcare.fragments.HomeFragrament;
import com.codesurfers.healthcare.fragments.NotificationFragment;
import com.codesurfers.healthcare.fragments.ProfileFragment;
import com.codesurfers.healthcare.fragments.SettingsFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeScreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragrament homeFragrament = new HomeFragrament();
    SettingsFragment settingsFragment = new SettingsFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setTitle("DUT Healthcare Clinic");
        //setContentView(R.layout.fragment_home_fragrament);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragrament).commit();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notifications);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(2);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragrament).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;
                    case R.id.notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}