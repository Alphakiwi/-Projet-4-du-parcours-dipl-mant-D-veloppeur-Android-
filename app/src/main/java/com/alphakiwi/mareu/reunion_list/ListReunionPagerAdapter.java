package com.alphakiwi.mareu.reunion_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListReunionPagerAdapter extends FragmentPagerAdapter {

    public ListReunionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {


            return ReunionFragment.newInstance();

    }


    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }
}



