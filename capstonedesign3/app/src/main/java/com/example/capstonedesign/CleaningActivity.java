package com.example.capstonedesign;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CleaningActivity extends AppCompatActivity {
    Button cleaning_stopCleaning;
    Chronometer cleaning_chronotime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cleaning);

        cleaning_stopCleaning = (Button) findViewById(R.id.Cleaning_StopCleaning);
        cleaning_chronotime = (Chronometer)findViewById(R.id.Cleaning_ChronoTime);

        cleaning_chronotime.start();

        cleaning_chronotime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
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

        cleaning_chronotime.setBase(SystemClock.elapsedRealtime());
        cleaning_chronotime.start();


        cleaning_stopCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 중지합니다",Toast.LENGTH_SHORT).show();
                cleaning_chronotime.stop();
                Intent intent = new Intent(CleaningActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
