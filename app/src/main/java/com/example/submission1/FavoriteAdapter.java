package com.example.submission1;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FavoriteAdapter extends FragmentPagerAdapter {

    public FavoriteAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new FavoriteMovieFragment();
        }
        return new FavoriteTVShowFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Movies";
        }
        return "TV Show";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
