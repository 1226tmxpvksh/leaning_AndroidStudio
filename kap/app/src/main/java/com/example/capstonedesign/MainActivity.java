package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    Button clean_start,clean_reservation,clean_alarm;
    ImageButton clean_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clean_start = (Button) findViewById(R.id.Clean_start);
        clean_reservation = (Button) findViewById(R.id.Clean_reservation);
        clean_setting = (ImageButton) findViewById(R.id.Clean_setting);
        clean_alarm=(Button)findViewById(R.id.Clean_alarm) ;

        clean_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CleaningActivity.class);
                startActivity(intent);
            }
        });

        clean_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        clean_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        clean_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });


    }
}









