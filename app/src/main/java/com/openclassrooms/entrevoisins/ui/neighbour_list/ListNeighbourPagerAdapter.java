package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @Override
        public Fragment getItem(int position) {
        switch (position){
            case 0 : return NeighbourFragment.newInstance();
            case 1 : return FavoritesFragment.newInstance();
            default: return null;
        }
    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {
        return 2;
    }
}