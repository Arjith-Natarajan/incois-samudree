package com.example.vickssarv.homescreenf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FishingActivity extends AppCompatActivity {
    RelativeLayout l;
    public int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);
        ImageButton closer = (ImageButton) findViewById(R.id.closer);
        ImageButton far = (ImageButton) findViewById(R.id.far);
        Button Reset=(Button)findViewById(R.id.button2);
        l = (RelativeLayout)findViewById(R.id.parent_rel);
        final ImageView image = (ImageView) findViewById(R.id.my_boat);
        final ImageView enemy = (ImageView) findViewById(R.id.my_enemy);
        final ImageView pfz_image = (ImageView) findViewById(R.id.PFZ);

        closer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).leftMargin += 25;
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin +=15;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).leftMargin += 10;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).topMargin -=10;
                check(image,pfz_image,enemy,l);
                ++count;
                image.requestLayout();

            }
        });
        closer.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin -= 25;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).leftMargin += 10;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).topMargin -=10;
                ++count;
                image.requestLayout();
                check(image,pfz_image,enemy,l);
                return true;
            }
        });

        far.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).leftMargin += 25;
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin -=15;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).leftMargin += 25;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).topMargin -=20;
                ++count;
                check(image,pfz_image,enemy,l);
                image.requestLayout();
                enemy.requestLayout();

            }
        });

        far.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin += 25;
                check(image,pfz_image,enemy,l);
                ++count;
                image.requestLayout();
                return true;
            }
        });



        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin =350;
                ((ViewGroup.MarginLayoutParams)image.getLayoutParams()).leftMargin =0;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).topMargin =620;
                ((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).leftMargin =0;
                image.requestLayout();
                enemy.requestLayout();

            }
        });

    }
    void check( ImageView image,ImageView pfz,ImageView enemy ,RelativeLayout l) {
        boolean win=false;
        if(Math.abs(((ViewGroup.MarginLayoutParams)image.getLayoutParams()).leftMargin-((ViewGroup.MarginLayoutParams)pfz.getLayoutParams()).leftMargin ) <25 && Math.abs(((ViewGroup.MarginLayoutParams)image.getLayoutParams()).topMargin-((ViewGroup.MarginLayoutParams)pfz.getLayoutParams()).topMargin ) <25) {
            Toast.makeText(FishingActivity.this, "You won!", Toast.LENGTH_SHORT).show();
            win=true;
        }
        if(win==false && (Math.abs(((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).leftMargin-((ViewGroup.MarginLayoutParams)pfz.getLayoutParams()).leftMargin ) <25 ||Math.abs(((ViewGroup.MarginLayoutParams)enemy.getLayoutParams()).topMargin-((ViewGroup.MarginLayoutParams)pfz.getLayoutParams()).topMargin ) <25))

            Toast.makeText(FishingActivity.this, "You won! Count"+count+"  ", Toast.LENGTH_SHORT).show();
    }


}
