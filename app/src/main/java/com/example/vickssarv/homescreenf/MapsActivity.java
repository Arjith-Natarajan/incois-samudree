package com.example.vickssarv.homescreenf;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    LocationManager locationManager;
    LocationListener locationListener;
    Polyline line;
    private TextView lat, lon;
    private Marker marker;

    private GoogleMap mMap;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    TextView port,dist;
    LatLng fish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        double latitude = Double.parseDouble(intent.getStringExtra("Latitude"));
        double longitude = Double.parseDouble(intent.getStringExtra("Longitude"));
        double distance  = Double.parseDouble(intent.getStringExtra("distance"));
        //Log.d("supcoolLAT",String.valueOf(latitude));
 //       Log.d("surcoolLON")
        String cname = intent.getStringExtra("cname");
        port = (TextView) findViewById(R.id.port);
        dist = (TextView) findViewById(R.id.dist);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fish = new LatLng(latitude,longitude);
        port.setText(cname);
        dist.setText(String.valueOf(distance));
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //lat.setText(String.valueOf(location.getLatitude()));
                //lon.setText(String.valueOf(location.getLongitude()));
                double x = location.getLatitude();
                double y = location.getLongitude();
                LatLng curr = new LatLng(x, y);

                try {

                    line.remove();
                }catch(NullPointerException e)
                {
                    e.printStackTrace();
                    Log.d("line not removed"," sss");
                }


                line = mMap.addPolyline(new PolylineOptions()
                        .add(curr, fish)
                        .width(5)
                        .color(Color.RED));
                double results = distance(curr.latitude, curr.longitude, fish.latitude, fish.longitude);
                try {
                    marker.remove();
                }catch(NullPointerException e)
                {
                    e.printStackTrace();
                }
                marker = mMap.addMarker(new MarkerOptions().position(fish).title("FISH").snippet(String.format("%.2f", results) + "km"));
                Log.d("lat:", String.valueOf(fish.latitude));
                Log.d("long:", String.valueOf(fish.longitude));
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            Log.d("location permission ne", "location permission needed");
            return;

        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat= (Double) getIntent().getExtras().get("lat");
        Double lon= (Double) getIntent().getExtras().get("lon");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Alert"));

        int zoomLevel = 16; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
    }
}

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        double latitude, longitude;
        // Add a marker in Sydney and move the camera
       // sydney = new LatLng(12.750850, 80.199241);
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            Log.d("location permission ne", "location permission needed");
            return;
        } else {
            mMap.setMyLocationEnabled(true);
        }

        /*line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(10, 20), sydney)
                .width(5)
                .color(Color.RED));*/
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } else {
            Log.e("Location not rec:", "ss");
            latitude = 12.715082;
            longitude = 80.199241;
        }

        //Log.e("oooooo:", String.valueOf(latitude) + " " + String.valueOf(longitude));
        try {
            line.remove();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latitude, longitude), fish)
                .width(5)
                .color(Color.RED));
        double results;
        results = distance(latitude, longitude, fish.latitude, fish.longitude);
        //Log.d("distance(in km)", String.valueOf(results));
        marker = mMap.addMarker(new MarkerOptions().position(fish).title("fish").snippet(String.format("%.2f", results) + "km"));
        //mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fish));
    }

    //function to calculate distance

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
