package com.example.nirav.indigox.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class GPSLocationActivity extends Activity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    double readLatitude;
    double readLongitude;
    double readElevation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
            }
            return;
        } else {
            configureButton();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureButton();
                    return;
                }
        }
    }

    private void configureButton() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                readLatitude = location.getLatitude();
                readLongitude = location.getLongitude();
                readElevation = location.getAltitude();

                Context context = getApplicationContext();
                CharSequence msg = "scanned result : " + readLongitude ;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, msg, duration);
                toast.show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("latitudeResult",readLatitude);
                returnIntent.putExtra("longitudeResult",readLongitude);
                returnIntent.putExtra("altitudeResult",readElevation);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
    }


    @Override
    public void onResume (){
        super.onResume();
        configureButton();
        locationManager.removeUpdates(locationListener);
        finish();
    }

    @Override
    public void onPause() {
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(locationListener);
        super.onDestroy();
    }
}
