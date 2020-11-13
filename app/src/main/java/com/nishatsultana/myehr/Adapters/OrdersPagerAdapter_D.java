package com.nishatsultana.myehr.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nishatsultana.myehr.Doctor.MyChambersFragment;
import com.nishatsultana.myehr.Doctor.MyPatientsFragment;
import com.nishatsultana.myehr.Doctor.TodaysAppointmentsFragment;


public class OrdersPagerAdapter_D extends FragmentStateAdapter {
    public OrdersPagerAdapter_D(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MyChambersFragment();
            case 1:
                return new TodaysAppointmentsFragment();
            case 2:
                return new MyPatientsFragment();
            default:
                return  new MyChambersFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

