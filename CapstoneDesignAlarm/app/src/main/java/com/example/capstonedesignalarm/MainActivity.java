package com.example.capstonedesignalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button yes,no,alarm;
    ImageButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yes=(Button) findViewById(R.id.Yes);
        no =(Button) findViewById(R.id.No);
        alarm=(Button)findViewById(R.id.Alarm)

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 시작합니다",Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 시작하지 않습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}