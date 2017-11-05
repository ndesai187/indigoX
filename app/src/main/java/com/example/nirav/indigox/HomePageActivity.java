package com.example.nirav.indigox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.example.nirav.indigox.Fragments.HomeFragment;
import com.example.nirav.indigox.Fragments.UnderConstructionFragment;
import com.example.nirav.indigox.MainPage.AddReadingActivity;
import com.example.nirav.indigox.MainPage.StartScannerActivity;
import com.example.nirav.indigox.MainPage.ViewReadingActivity;
import com.example.nirav.indigox.MainPage.ViewScanCodesActivity;

public class HomePageActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fragmentManager = getSupportFragmentManager();

        setupView();
        //if (savedInstanceState == null) showHome();

    }

    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void showHome() {
        selectDrawerItem(navigationView.getMenu().getItem(0));
        //drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
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

    private void selectDrawerItem(MenuItem menuItem) {
        Class fragmentClass;
        Context context;

        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                context = getApplicationContext();
                CharSequence msg = "switch case executing...";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, msg, duration);
                toast.show();
                fragmentClass = HomeFragment.class;
                break;
            /*case R.id.drawer_favorites:
                fragmentClass = FavoritesFragment.class;
                specialToolbarBehaviour = true;
                break;
            case R.id.drawer_settings:
                fragmentClass = SettingsFragment.class;
                break;
            */
            default:
                CharSequence msg1 = "default code executing...";
                context = getApplicationContext();
                duration = Toast.LENGTH_LONG;
                Toast toast1 = Toast.makeText(context, msg1, duration);
                toast1.show();
                fragmentClass = UnderConstructionFragment.class;
                break;
        }

        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }


        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    public void addSamplerScanner(View v) {
        Button addSamplerButton = (Button) findViewById(R.id.add_sampler);
        addSamplerButton.setClickable(true);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, StartScannerActivity.class);
                startActivity(i);
            }
        });
    }

    public void addReading(View v) {
        Button addReadingButton = (Button) findViewById(R.id.add_reading);
        addReadingButton.setClickable(true);
        addReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, AddReadingActivity.class);
                startActivity(i);
            }
        });
    }

    public void viewSamplerInfo(View v) {
        Button viewSamplerButton = (Button) findViewById(R.id.view_sampler);
        viewSamplerButton.setClickable(true);
        viewSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, ViewScanCodesActivity.class);
                startActivity(i);
            }
        });
    }

    public void viewReadingInfo(View v) {
        Button viewReadingButton = (Button) findViewById(R.id.view_reading);
        viewReadingButton.setClickable(true);
        viewReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, ViewReadingActivity.class);
                startActivity(i);
            }
        });
    }

}

/*

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        selectDrawerItem(item);
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //fragmentClass = HomeFragment.class;
            fragmentClass = PlusOneFragment.class;

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        try {
        Fragment fragment = (Fragment) fragmentClass.newInstance();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
 */