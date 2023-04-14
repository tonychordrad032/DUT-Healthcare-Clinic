package com.codesurfers.healthcare;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesurfers.healthcare.fragments.HomeFragrament;
import com.codesurfers.healthcare.fragments.NotificationFragment;
import com.codesurfers.healthcare.fragments.ProfileFragment;
import com.codesurfers.healthcare.fragments.SettingsFragment;
import com.codesurfers.healthcare.model.Clinic;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity {
    private IClinicAPI ClinicAPI;

    ArrayList<Clinic> arrayList = new ArrayList<>();
    Adapter.RecyclerViewClick listener;
    RecyclerView rv_1;
    String URL = "http://192.168.8.119:9092/dut_healthcare_clinic/api/clinic";
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

    private void getClinics() {
        Call<List<Clinic>> call = ClinicAPI.getClinics();

        call.enqueue(new Callback<List<Clinic>>() {
            @Override
            public void onResponse(Call<List<Clinic>> call, Response<List<Clinic>> response) {

                if (response.code() == 200) {
                    List<Clinic> tempList = response.body();
                    for (int i = 0; i < tempList.size(); i++) {
                        arrayList.add(new Clinic(tempList.get(1).getClinicName(), tempList.get(i).getClinicDescription(), tempList.get(i).getClinicCampus(), tempList.get(i).getLatitude(), tempList.get(i).getLongitude()));
                    }

                    System.out.println("SUCCESSFULLY");
                    System.out.println(arrayList.size());

                }else {
                    System.out.println("Error Occurred");
                }

            }
            @Override
            public void onFailure(Call<List<Clinic>> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }
}