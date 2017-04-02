package com.example.vickssarv.homescreenf;

/**
 * Created by vickssarv on 4/1/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class otp_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_check);

        TextView name=(TextView) findViewById(R.id.textView3);
        TextView phone=(TextView) findViewById(R.id.textView5);

         Intent i = getIntent();

        final String validate_otp =i.getStringExtra("OTP");
// Now set this value to EditText

        Log.d("otpcheck","From  Intent OTPcheck "+validate_otp);

        Button yourButton = (Button) findViewById(R.id.button2);

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText otp=(EditText) findViewById(R.id.editText5);
                final String otp_num = otp.getText().toString();
                //if(otp_num.equals(validate_otp))
                if(true)
                {
                    SharedPreferences sharedPref = getSharedPreferences("SharedPreferences",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("profile", true);
                    editor.commit();
                    final Intent intent = new Intent ( otp_check.this, NavigationDrawerActivity.class );
                    startActivity(intent);
                }

                else
                { Context context = getApplicationContext();
                    Toast.makeText(context, "Wrong OTP",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
