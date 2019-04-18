package com.example.fruitsstockapp.adapters;

import com.example.fruitsstockapp.ui.FruitsInCartFragment;
import com.example.fruitsstockapp.ui.FruitsInStockFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_TABS = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FruitsInStockFragment();

            case 1:
                return new FruitsInCartFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "In Stock";

            case 1:
                return "In Cart";

            default:
                return null;
        }

    }
}
