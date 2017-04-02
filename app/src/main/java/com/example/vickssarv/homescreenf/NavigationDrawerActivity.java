package com.example.vickssarv.homescreenf;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.vickssarv.homescreenf.fragment.HomeFragment;
import com.example.vickssarv.homescreenf.fragment.WaveHeight;
import com.example.vickssarv.homescreenf.fragment.WindDirection;


public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String MyString;
    private String TAG = MainActivity.class.getSimpleName();
    protected FrameLayout frameLayout;
    protected Toolbar toolbar;
    protected NavigationView navView;
    protected DrawerLayout drawer;
    final String URL="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        /*HashMap<String, String> params = new HashMap<String, String>();

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
                            String lat =resObj.get("Latitude");
                            String lon = resObj.get("Longitude");
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
        queue.add(req);*/
        /*new GetData().execute();*/
        navView = (NavigationView) findViewById(R.id.nav_view);
        frameLayout = (FrameLayout) findViewById(R.id.nav_generic_frame);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navView.getMenu().getItem(0).setChecked(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), ChatBoxActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadHomeFragment();

        /*String temp_value = GetData.getData();
        TextView b = (TextView) findViewById(R.id.temp);
        b.setText(temp_value);

        MyString = temp_value;*/


    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
           // showAlertDialog();
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);

        if (id == R.id.nav_home) {
          loadHomeFragment();
        }
//        else if (id == R.id.nav_about_us) {
//        } else if (id == R.id.nav_terms) {
//        }
        else if(id == R.id.nav_wind_direction){
            loadWindDirectionFragment();
        }

        else if(id == R.id.nav_wave_height){
            loadWaveHeightFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadWindDirectionFragment(){
        WindDirection windDirection = new WindDirection();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_generic_frame, windDirection);
        ft.addToBackStack("wind_direction");
        ft.commitAllowingStateLoss();
    }

    private void loadWaveHeightFragment(){
        WaveHeight waveheight = new WaveHeight();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_generic_frame, waveheight);
        ft.addToBackStack("wave_height");
        ft.commitAllowingStateLoss();
    }

    private void loadHomeFragment(){
        HomeFragment homeFrgament = new HomeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_generic_frame, homeFrgament);
        ft.addToBackStack("home");
        ft.commitAllowingStateLoss();
    }

    /*private void startHomeActivity() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    private void showAlertDialog(){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.show();

        dialog.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }*/
    /*public String getMyData() {
        return MyString;
    }*/


}

/*class GetData extends AsyncTask<Void, Void, String[]> {

    static String value[]=new String[3];


    @Override
    protected String[] doInBackground(Void... params) {
        HttpHandler sh = new HttpHandler();
        String key = "a";
        String url = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search.json?q=13.08,80.276&apikey=GpB35eaybNZBqLNFSfg49NRe6Kw5wE2k";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                key = jsonObj.getString("Key");
                url = "http://dataservice.accuweather.com/currentconditions/v1/" + key + "?apikey=GpB35eaybNZBqLNFSfg49NRe6Kw5wE2k&details=true";
                Log.e(TAG, "Response from url: " + key);
                String data = sh.makeServiceCall(url);

                data = data.replace("[", "");
                data = data.replace("]", "");
                Log.e(TAG, "Response from url: " + data);
                if (data != null) {
                    jsonObj = new JSONObject(data);
                    JSONObject realtemp = jsonObj.getJSONObject("Temperature");
                    JSONObject metric = realtemp.getJSONObject("Metric");
                    String temp = metric.getString("Value");
                    value[0]=temp;
                    // Log.e("temperature",value[0]);
                    Log.e(TAG, "Response from url: " + temp);
                    JSONObject wind = jsonObj.getJSONObject("Wind");
                    Log.e(TAG, "Response from url: " + wind.toString());
                    JSONObject direction = wind.getJSONObject("Direction");
                    String direc = direction.getString("Degrees");
                    direc = direc + " degrees " + direction.getString("English");
                    value[1] = direc;
                    Log.e(TAG, "Response from url: " + direc);
                    JSONObject speed = wind.getJSONObject("Speed");
                    JSONObject Metric = speed.getJSONObject("Metric");
                    String Speed = Metric.getString("Value");
                    Speed = Speed + " Km/h";
                    value[2] = Speed;
                    Log.e(TAG, "Response from url: " + Speed);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e(TAG, "Response from url: " + key);
        }
        return null;
    }
    protected String onPostExecute(){
        return value[0];
    }

}*/