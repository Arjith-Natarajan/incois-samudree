package com.example.vickssarv.homescreenf;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import android.Manifest;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.example.vickssarv.homescreenf.R.id.spinner1;
import static com.example.vickssarv.homescreenf.R.id.spinner2;

public class MainActivity extends AppCompatActivity {
    Spinner dropdown, dropdown1;
    final String URL = "http://139.59.72.134:8000/Samudree/submit";

    ArrayAdapter<String> adapter1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    String txt_name,txt_num;
    String lang,coast,otp;
    LocationManager locationManager;
    LocationListener locationListener;
    Location locationg;
    Boolean isGPSEnabled, isNetworkEnabled, canGetLocation;
    double latitude, longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
        dropdown = (Spinner) findViewById(spinner1);

        String[] items = new String[]{"English", "Tamil", "Hindi", "Malayalam", "Marathi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) ;
        dropdown.setAdapter(adapter);

        dropdown1 = (Spinner) findViewById(spinner2);
        String[] items1 = new String[]{"Select Coast", "Veerampatinam", "Cuddalore", "Kumarapettai", "Chitharaipettai", "Murthipudukuppam", "Nallavadu", "Solainagar North"};

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1) {
            @Override
            public boolean isEnabled(int position1) {
                if (position1 == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
/*
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
            Log.d("location permission ne", "location permission needed");

        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //lat.setText(String.valueOf(location.getLatitude()));
                //lon.setText(String.valueOf(location.getLongitude()));
                double x = location.getLatitude();
                double y = location.getLongitude();
                locationg = location;


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
        locationg = getLocation();
        if(locationg!=null) {
            Log.d("latitude", String.valueOf(locationg.getLatitude()) + "/" + String.valueOf(locationg.getLongitude()));
*/

            dropdown1.setAdapter(adapter1);
        Button yourButton = (Button) findViewById(R.id.button);
        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.editText);
                EditText number = (EditText) findViewById(R.id.editText2);
                txt_name = name.getText().toString();
                txt_num = number.getText().toString();
                lang=dropdown.getSelectedItem().toString();
                coast=dropdown1.getSelectedItem().toString();


                HashMap<String, String> params = new HashMap<String, String>();
                params.put("name",txt_name);
                params.put("mobile_number",txt_num);
                params.put("preferred_language",lang);
                params.put("preferred_coast",coast);
                params.put("latitude","8.90");
                params.put("longitude","90");
                JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                   // VolleyLog.v("Response:%n %s", response.toString(4));

                                    Gson gson=new Gson();
                                    Type type = new TypeToken<Map<String, String>>(){}.getType();
                                    Map<String, String> resObj = gson.fromJson(response.toString(), type);
                                    otp=resObj.get("OTP");
                                    Intent intent = new Intent(MainActivity.this,otp_check.class);
                                    intent.putExtra("OTP", otp);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });
                queue.add(req);


//                new SendDeviceDetails.execute("139.59.72.134:8000/samudree/submit",json);

                if (txt_name.equals("") || txt_num.equals("") || dropdown1.getSelectedItemPosition() == 0)
                { Context context = getApplicationContext();
                    Toast.makeText(context, "Blank Field(s)",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    final Intent intent = new Intent(MainActivity.this, otp_check.class);
                    intent.putExtra("TextBox", name.getText().toString());
                    intent.putExtra("Number", number.getText().toString());
                    intent.putExtra("OTP", otp);
                    System.out.println(
                            "From  Intent main "+otp);
                    startActivity(intent);

                }
            }
        });



    }
    /*
        @Override
        public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
            switch (requestCode) {
                case 1: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }

                        locationg = getLocation();
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

    public Location getLocation() {

        try {
            Context mContext = getBaseContext();
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(SOSActivity.this,
                                Manifest.permission.ACCESS_FINE_LOCATION)) {
                        } else {
                            ActivityCompat.requestPermissions(SOSActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                        Log.d("location permission ne", "location permission needed");


                    } else {
                        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            2,
                            1, locationListener);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        locationg = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (locationg != null) {
                            latitude = locationg.getLatitude();
                            longitude = locationg.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (locationg == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                2,
                                1, locationListener);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            locationg = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (locationg != null) {
                                latitude = locationg.getLatitude();
                                longitude = locationg.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return locationg;
    }
*/
}





