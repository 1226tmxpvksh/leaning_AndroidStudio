package com.example.assignment05_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout purple,blue,yellow,black;
    int height,width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        purple=(LinearLayout) findViewById(R.id.Purple);
        blue=(LinearLayout) findViewById(R.id.Blue);
        yellow=(LinearLayout) findViewById(R.id.Yellow);
        black=(LinearLayout) findViewById(R.id.Black);

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = purple.getWidth();
                height = purple.getHeight();
                Toast.makeText(getApplicationContext(), "레이아웃의 폭 : " + width + ", 높이 : " + height+" 입니다", Toast.LENGTH_SHORT).show();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = blue.getWidth();
                height = blue.getHeight();
                Toast.makeText(getApplicationContext(), "레이아웃의 폭 : " + width + ", 높이 : " + height+" 입니다", Toast.LENGTH_SHORT).show();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = yellow.getWidth();
                height = yellow.getHeight();
                Toast.makeText(getApplicationContext(), "레이아웃의 폭 : " + width + ", 높이 : " + height+" 입니다", Toast.LENGTH_SHORT).show();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = black.getWidth();
                height = black.getHeight();
                Toast.makeText(getApplicationContext(), "레이아웃의 폭 : " + width + ", 높이 : " + height+" 입니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}