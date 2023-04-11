package com.codesurfers.healthcare;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codesurfers.healthcare.fragments.tabbar_fragments.HistoryFragment;
import com.codesurfers.healthcare.fragments.tabbar_fragments.UpcomingFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new HistoryFragment();
            default:
                return new UpcomingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
