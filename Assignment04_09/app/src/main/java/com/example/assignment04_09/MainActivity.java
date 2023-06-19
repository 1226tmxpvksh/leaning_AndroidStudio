package com.example.assignment04_09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView img;
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        btn = (Button)findViewById(R.id.Btn);
        img = (ImageView)findViewById(R.id.Img) ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click +=10;
                img.setRotation(click);
            }
        });


    }
}