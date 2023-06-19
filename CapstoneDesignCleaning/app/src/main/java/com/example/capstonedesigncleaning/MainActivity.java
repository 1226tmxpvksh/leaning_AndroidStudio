package com.example.capstonedesigncleaning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button stopcleaning;
    Chronometer chronotime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopcleaning = (Button) findViewById(R.id.StopCleaning);
        chronotime = (Chronometer)findViewById(R.id.ChronoTime);

        chronotime.start();

        chronotime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                int hours = (int) (elapsedMillis / 3600000);
                int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
                int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                chronometer.setText(time);
            }
        });
        chronotime.setBase(SystemClock.elapsedRealtime());
        chronotime.start();

        stopcleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 중지합니다",Toast.LENGTH_SHORT).show();
                chronotime.stop();
            }
        });
    }
}