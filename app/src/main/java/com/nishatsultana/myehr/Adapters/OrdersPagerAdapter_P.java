package com.nishatsultana.myehr.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nishatsultana.myehr.Patient.MyDoctorsFragment_P;
import com.nishatsultana.myehr.Patient.MyHealthTrackFragment_P;
import com.nishatsultana.myehr.Patient.PrescriptionsFragment_p;
import com.nishatsultana.myehr.Patient.ReportsFragment_p;


public class OrdersPagerAdapter_P extends FragmentStateAdapter {
    public OrdersPagerAdapter_P(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MyDoctorsFragment_P();
            case 1:
                return new MyHealthTrackFragment_P();
            case 2:
                return new PrescriptionsFragment_p();
            case 3:
                return new ReportsFragment_p();
            default:
                return  new MyDoctorsFragment_P();
        }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

