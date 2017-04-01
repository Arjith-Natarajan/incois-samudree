package com.example.vickssarv.homescreenf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.InputStream;

import static com.example.vickssarv.homescreenf.R.id.spinner1;
import static com.example.vickssarv.homescreenf.R.id.spinner2;

public class MainActivity extends AppCompatActivity {
    Spinner dropdown, dropdown1;

    ArrayAdapter<String> adapter1, adapter2, adapter3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    // Disable the first item from Spinner
                    // First item will be use for hint
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


                String txt_name = name.getText().toString();
                String txt_num = number.getText().toString();


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
                    startActivity(intent);
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

