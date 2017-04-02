package com.example.vickssarv.homescreenf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InitialActivity extends AppCompatActivity {
    ImageButton PFZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        PFZ=(ImageButton)findViewById(R.id.b07);
        final TextView text=(TextView)findViewById(R.id.text);

        PFZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Yes!PFZ successfully located");
                AlertDialog alertDialog = new AlertDialog.Builder(InitialActivity.this).create();
                alertDialog.setTitle("Beware!");
                alertDialog.setMessage("Are you ready for the next level?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent =new Intent(InitialActivity.this,FishingActivity.class);
                                startActivity(intent);


                            }});

                alertDialog.show();

            }
        });


    }
}
