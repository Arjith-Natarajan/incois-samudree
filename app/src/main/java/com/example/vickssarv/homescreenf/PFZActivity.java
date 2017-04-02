package com.example.vickssarv.homescreenf;


import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PFZActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Location locationg;
    Button but_pfz;
    Boolean isGPSEnabled, isNetworkEnabled, canGetLocation;
    double latitude, longitude;
    final String URL = "http://139.59.72.134:8000/Samudree/pfz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final RequestQueue queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfz);
        but_pfz = (Button) findViewById(R.id.but_pfz);

        //locationg = getLocation();
        but_pfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<String, String>();
                double latitude = 12.7517467;
                double longitude = 80.195919;
                params.put("latitude",String.valueOf(12.7517467));
                params.put("longitude",String.valueOf(80.195919));
                System.out.println("Outside JSONObject");
                JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    System.out.println("Inside try");
                                    VolleyLog.v("Response:%n %s", response.toString());
                                    //Response
                                    Gson gson=new Gson();
                                    Type type = new TypeToken<Map<String, String>>(){}.getType();
                                    Map<String, String> resObj = gson.fromJson(response.toString(), type);
                                    String lat =resObj.get("latitude");
                                    String lon = resObj.get("longitude");
                                    String distance=resObj.get("distance_to_coast");
                                    String cname=resObj.get("coast_name");
                                    System.out.println(lat);
                                    Intent intent = new Intent(PFZActivity.this,MapsActivity.class);
                                    intent.putExtra("Latitude",lat);
                                    intent.putExtra("Longitude",lon);
                                    intent.putExtra("distance",distance);
                                    intent.putExtra("cname",cname);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });
                queue.add(req);


            }
        });

    }

}
