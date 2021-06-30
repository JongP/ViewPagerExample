package com.example.viewpagerexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdaptor extends FragmentStateAdapter {


    public ViewPagerAdaptor(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return FragMonday.newInstance();
            case 1:
                return FragTuesday.newInstance();
            case 2:
                return  FragWensday.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
