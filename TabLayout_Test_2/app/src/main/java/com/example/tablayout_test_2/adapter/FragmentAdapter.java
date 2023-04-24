package com.example.tablayout_test_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tablayout_test_2.fragment.FragmentDanhSach;
import com.example.tablayout_test_2.fragment.FragmentThongTin;
import com.example.tablayout_test_2.fragment.FragmentTimKiem;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private int numPage = 3;

    public FragmentAdapter(@NonNull FragmentManager fm, int numPage) {
        super(fm,numPage);
        this.numPage = numPage;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDanhSach();
            case 1: return new FragmentThongTin();
            case 2: return new FragmentTimKiem();
            default: return new FragmentDanhSach();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
