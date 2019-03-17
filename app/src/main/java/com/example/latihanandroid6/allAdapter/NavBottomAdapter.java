package com.example.latihanandroid6.allAdapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.latihanandroid6.Halaman.Halaman1;
import com.example.latihanandroid6.Halaman.Halaman2;
import com.example.latihanandroid6.Halaman.Halaman3;

public class NavBottomAdapter extends FragmentPagerAdapter {

    final int RG = 3;
    private Fragment currentFragment;

    public NavBottomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: {
                currentFragment = Halaman1.newInstance(0);
            }
            break;
            case 1: {
                currentFragment = Halaman2.newInstance(1);
            }
            break;
            case 2: {
                currentFragment = Halaman3.newInstance(2);
            }
            break;
            default: {
                currentFragment = Halaman1.newInstance(0);
            }
            break;
        }
        return currentFragment;
    }

    @Override
    public int getCount() {
        return RG;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
