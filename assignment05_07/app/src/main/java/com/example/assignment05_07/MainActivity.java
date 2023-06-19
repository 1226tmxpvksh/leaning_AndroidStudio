package com.example.assignment05_07;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);

        LinearLayout baseLayout1 = new LinearLayout(this);
        baseLayout1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout baseLayout2 = new LinearLayout(this);
        baseLayout2.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout baseLayout3 = new LinearLayout(this);
        baseLayout3.setOrientation(LinearLayout.VERTICAL);

        LinearLayout red = new LinearLayout(this);
        red.setBackgroundColor(Color.RED);

        LinearLayout yellow = new LinearLayout(this);
        yellow.setBackgroundColor(Color.YELLOW);

        LinearLayout black = new LinearLayout(this);
        black.setBackgroundColor(Color.BLACK);

        LinearLayout blue = new LinearLayout(this);
        blue.setBackgroundColor(Color.BLUE);

        baseLayout1.addView(baseLayout2, params);
        baseLayout1.addView(blue, params);
        baseLayout2.addView(red, params);
        baseLayout2.addView(baseLayout3, params);
        baseLayout3.addView(yellow, params);
        baseLayout3.addView(black, params);

        setContentView(baseLayout1,params);


    }
}