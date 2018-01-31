package com.example.nirav.indigox.MainPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nirav.indigox.R;
import com.example.nirav.indigox.Utils.GPSLocationActivity;
import com.example.nirav.indigox.Utils.StartScannerActivity;

public class AddReadingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    int requestCodeScan = 7;
    int requestCodeLocation = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.add_profile_fragment));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.geo_weather_fragment));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.link_sampler_fragment));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.add_reading_data_fragment));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void activateScanner(View v) {
        Button addSamplerButton = (Button) findViewById(R.id.trigger_scanner_button);
        addSamplerButton.setClickable(true);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddReadingActivity.this, StartScannerActivity.class);
                //startActivity(i);
                startActivityForResult(i, requestCodeScan);
            }
        });
    }

    public void getGPSLocation(View v) {
        Button addSamplerButton = (Button) findViewById(R.id.boat_location_button);
        addSamplerButton.setClickable(true);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddReadingActivity.this, GPSLocationActivity.class);
                //startActivity(i);
                startActivityForResult(i, requestCodeLocation);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String resultBarText = "Dummy Scan ID";
        Double resultLatitude = 1.0;
        Double resultLongitude = 2.0;
        Double resultElevation = 0.0;

        if (requestCode == requestCodeScan) {

            if(resultCode == Activity.RESULT_OK){
                resultBarText=data.getStringExtra("barCode");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write code if there's no result
            }

            EditText editText = (EditText)findViewById(R.id.sampler_id);
            editText.setText(resultBarText, TextView.BufferType.EDITABLE);

            Context context = getApplicationContext();
            CharSequence msg = "scanned result : " + resultBarText ;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, msg, duration);
            toast.show();

        } else if (requestCode == requestCodeLocation){

            if(resultCode == Activity.RESULT_OK){
                resultLatitude = data.getDoubleExtra("latitudeResult",1.0);
                resultLongitude = data.getDoubleExtra("longitudeResult",2.0);
                resultElevation = data.getDoubleExtra("altitudeResult",0.0);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write code if there's no result
            }

            EditText editText = (EditText)findViewById(R.id.geo_latitude);
            editText.setText(Double.toString(resultLatitude), TextView.BufferType.EDITABLE);

            editText = (EditText)findViewById(R.id.geo_longitude);
            editText.setText(Double.toString(resultLongitude), TextView.BufferType.EDITABLE);

            editText = (EditText)findViewById(R.id.geo_elevation);
            editText.setText(Double.toString(resultElevation), TextView.BufferType.EDITABLE);

            Context context = getApplicationContext();
            CharSequence msg = "GPS Location gathered..." ;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, msg, duration);
            toast.show();

        }


    }//onActivityResult

    /**
     * A placeholder fragment containing a simple view.


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_reading, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    // moved to seperate class SectionsPagerAdapter
}
