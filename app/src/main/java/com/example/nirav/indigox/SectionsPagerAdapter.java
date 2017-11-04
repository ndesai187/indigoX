package com.example.nirav.indigox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.nirav.indigox.Fragments.AddProfileFragment;
import com.example.nirav.indigox.Fragments.AddSamplerReadingsFragment;
import com.example.nirav.indigox.Fragments.GeoWeatherFragment;
import com.example.nirav.indigox.Fragments.LinkSamplerFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public SectionsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return AddReadingActivity.PlaceholderFragment.newInstance(position + 1);
        switch (position) {
            case 0:
                AddProfileFragment profileTab = new AddProfileFragment();
                return profileTab;
            case 1:
                GeoWeatherFragment geoWeatherTab = new GeoWeatherFragment();
                return geoWeatherTab;
            case 2:
                LinkSamplerFragment samplerTab = new LinkSamplerFragment();
                return samplerTab;
            case 3:
                AddSamplerReadingsFragment readingTab = new AddSamplerReadingsFragment();
                return readingTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return mNumOfTabs;
    }

    /*
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
    */
}
