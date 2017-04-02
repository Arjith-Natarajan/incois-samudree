package com.example.vickssarv.homescreenf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONObject;

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
                params.put("occupation","You rock!");
                params.put("latitude","12.7517467");
                params.put("longitude","80.195919");

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
                   // intent.putExtra("latitude",lat)
                    System.out.println("From  Intent main "+otp);
                    startActivity(intent);

                }
            }
        });
    }}








